package kopycinski.tomasz.klamkify.ui.screens.categoryform

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kopycinski.tomasz.domain.usecase.CreateCategoryUseCase
import kopycinski.tomasz.domain.usecase.GetCategoryUseCase
import kopycinski.tomasz.domain.usecase.UpdateCategoryUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryFormViewModel @Inject constructor(
    private val getCategoryUseCase: GetCategoryUseCase,
    private val createCategoryUseCase: CreateCategoryUseCase,
    private val updateCategoryUseCase: UpdateCategoryUseCase
): ViewModel() {
    var categoryId = mutableStateOf(-1L)
        private set
    var categoryName = mutableStateOf("")
        private set

    fun update(name: String) {
        categoryName.value = name
    }

    fun save(onSuccess: () -> Unit) = viewModelScope.launch {
        if (categoryId.value == -1L) {
            createCategoryUseCase(categoryName.value)
        } else {
            updateCategoryUseCase(categoryId.value, categoryName.value)
        }
        onSuccess()
    }

    fun getCategory(id: Long) = viewModelScope.launch {
        getCategoryUseCase(id).also {
            categoryId.value = it.categoryId
            categoryName.value = it.name
        }
    }
}