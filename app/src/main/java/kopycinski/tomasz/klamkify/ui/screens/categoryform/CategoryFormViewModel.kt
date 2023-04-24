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
    var categoryName = mutableStateOf("")
        private set

    fun update(name: String) {
        categoryName.value = name
    }

    fun save(onSuccess: () -> Unit) = viewModelScope.launch {
        repository.insert(
            Category(categoryName.value)
        )
        onSuccess()
    }
}