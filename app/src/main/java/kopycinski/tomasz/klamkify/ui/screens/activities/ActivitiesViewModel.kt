package kopycinski.tomasz.klamkify.ui.screens.activities

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kopycinski.tomasz.domain.model.Category
import kopycinski.tomasz.domain.usecase.GetCategoriesUseCase
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

data class ActivityUIState(
    val categories: List<Category> = emptyList()
)

@HiltViewModel
class ActivitiesViewModel @Inject constructor(
    private val getCategoriesUseCase: GetCategoriesUseCase
) : ViewModel() {
    private val _uiState = mutableStateOf(ActivityUIState())
    val uiState: State<ActivityUIState> = _uiState

    init {
        getCategories()
    }

    private fun getCategories() {
        getCategoriesUseCase()
            .onEach {
                _uiState.value = ActivityUIState(it)
            }
            .launchIn(viewModelScope)
    }
}