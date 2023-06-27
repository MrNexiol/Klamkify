package kopycinski.tomasz.klamkify.ui.screens.activities

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kopycinski.tomasz.domain.model.Activity
import kopycinski.tomasz.domain.model.Category
import kopycinski.tomasz.domain.usecase.GetCategoriesWithActivitiesUseCase
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

data class ActivityUIState(
    val categories: Map<Category, List<Activity>> = mapOf()
)

@HiltViewModel
class ActivitiesViewModel @Inject constructor(
    private val getCategoriesWithActivitiesUseCase: GetCategoriesWithActivitiesUseCase
) : ViewModel() {
    private val _uiState = mutableStateOf(ActivityUIState())
    val uiState: State<ActivityUIState> = _uiState

    init {
        getCategories()
    }

    private fun getCategories() {
        getCategoriesWithActivitiesUseCase()
            .onEach {
                _uiState.value = ActivityUIState(it)
            }
            .launchIn(viewModelScope)
    }
}