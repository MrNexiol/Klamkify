package kopycinski.tomasz.klamkify.ui.screens.activityform

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kopycinski.tomasz.domain.model.Category
import kopycinski.tomasz.domain.usecase.CreateActivityUseCase
import kopycinski.tomasz.domain.usecase.GetCategoriesUseCase
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

data class ActivityFormUIState(
    val categoryList: List<Category> = listOf(),
    val chosenCategoryId: Long = -1
)

@HiltViewModel
class ActivityFormViewModel @Inject constructor(
    private val createActivityUseCase: CreateActivityUseCase,
    getCategoriesUseCase: GetCategoriesUseCase
): ViewModel() {
    private var _uiState = mutableStateOf(ActivityFormUIState())
    val uiState: State<ActivityFormUIState> = _uiState

    init {
        getCategoriesUseCase().onEach {
            _uiState.value = _uiState.value.copy(categoryList = it)
        }.launchIn(viewModelScope)
    }

    fun setCurrentCategoryId(id: Long) {
        _uiState.value = _uiState.value.copy(chosenCategoryId = id)
    }

    fun save(name: String) = viewModelScope.launch {
        createActivityUseCase(name, _uiState.value.chosenCategoryId)
    }
}