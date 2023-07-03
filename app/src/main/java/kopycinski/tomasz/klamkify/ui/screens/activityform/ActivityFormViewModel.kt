package kopycinski.tomasz.klamkify.ui.screens.activityform

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kopycinski.tomasz.domain.model.Category
import kopycinski.tomasz.domain.usecase.SaveActivityUseCase
import kopycinski.tomasz.domain.usecase.GetActivityUseCase
import kopycinski.tomasz.domain.usecase.GetCategoriesUseCase
import kopycinski.tomasz.klamkify.ui.navigation.DestinationArgs.ACTIVITY_ID_ARG
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

data class ActivityFormUIState(
    val activityName: String = "",
    val categoryList: List<Category> = listOf(),
    val chosenCategoryId: Long = -1,
    val chosenCategoryName: String = ""
)

@HiltViewModel
class ActivityFormViewModel @Inject constructor(
    private val saveActivityUseCase: SaveActivityUseCase,
    private val getCategoriesUseCase: GetCategoriesUseCase,
    private val getActivityUseCase: GetActivityUseCase,
    savedStateHandle: SavedStateHandle
): ViewModel() {
    private val activityId: Long = savedStateHandle[ACTIVITY_ID_ARG]!!

    private var _uiState = mutableStateOf(ActivityFormUIState())
    val uiState: State<ActivityFormUIState> = _uiState

    init {
        getCategories()
        getActivity()
    }

    private fun getCategories() {
        getCategoriesUseCase().onEach {
            _uiState.value = _uiState.value.copy(categoryList = it)
        }.launchIn(viewModelScope)
    }

    private fun getActivity() {
        if (activityId != -1L) {
            viewModelScope.launch {
                val activity = getActivityUseCase(activityId)
                val category = _uiState.value.categoryList.singleOrNull {
                    it.categoryId == activity.parentCategoryId
                }
                _uiState.value = _uiState.value.copy(
                    activityName = activity.name,
                    chosenCategoryId = activity.parentCategoryId,
                    chosenCategoryName = category?.name ?: ""
                )
            }
        }
    }

    fun setActivityName(name: String) {
        _uiState.value = _uiState.value.copy(activityName = name)
    }

    fun setCurrentCategoryId(id: Long) {
        _uiState.value = _uiState.value.copy(chosenCategoryId = id)
    }

    fun setCurrentCategoryName(name: String) {
        _uiState.value = _uiState.value.copy(chosenCategoryName = name)
    }

    fun saveActivity() = viewModelScope.launch {
        saveActivityUseCase(_uiState.value.activityName, _uiState.value.chosenCategoryId, activityId)
    }
}