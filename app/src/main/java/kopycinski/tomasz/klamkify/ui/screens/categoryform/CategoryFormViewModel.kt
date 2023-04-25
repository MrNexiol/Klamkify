package kopycinski.tomasz.klamkify.ui.screens.categoryform

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kopycinski.tomasz.klamkify.data.entity.Category
import kopycinski.tomasz.klamkify.data.repository.CategoryRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryFormViewModel @Inject constructor(
    private val repository: CategoryRepository
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
            repository.insert(Category(categoryName.value))
        } else {
            repository.update(Category(categoryName.value, archived = false, categoryId.value))
        }
        onSuccess()
    }

    fun getCategory(id: Long) = viewModelScope.launch {
        repository.getById(id).also {
            categoryId.value = it.categoryId
            categoryName.value = it.name
        }
    }
}