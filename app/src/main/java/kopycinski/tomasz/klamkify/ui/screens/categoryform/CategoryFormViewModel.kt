package kopycinski.tomasz.klamkify.ui.screens.categoryform

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kopycinski.tomasz.domain.usecase.SaveCategoryUseCase
import kopycinski.tomasz.domain.usecase.GetCategoryUseCase
import kopycinski.tomasz.klamkify.ui.navigation.DestinationArgs.CATEGORY_ID_ARG
import kotlinx.coroutines.launch
import javax.inject.Inject

data class CategoryFormUIState(
    val categoryName: String = ""
)

@HiltViewModel
class CategoryFormViewModel @Inject constructor(
    private val saveCategoryUseCase: SaveCategoryUseCase,
    private val getCategoryUseCase: GetCategoryUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val categoryId: Long? = savedStateHandle[CATEGORY_ID_ARG]

    private var _uiState = mutableStateOf(CategoryFormUIState())
    val uiState: State<CategoryFormUIState> = _uiState

    init {
        getCategory()
    }

    private fun getCategory() {
        categoryId?.let {
            viewModelScope.launch {
                val category = getCategoryUseCase(it)
                _uiState.value = uiState.value.copy(categoryName = category.name)
            }
        }
    }

    fun setCategoryName(name: String) {
        _uiState.value = _uiState.value.copy(categoryName = name)
    }

    fun saveCategory() = viewModelScope.launch {
        saveCategoryUseCase(_uiState.value.categoryName, categoryId)
    }
}