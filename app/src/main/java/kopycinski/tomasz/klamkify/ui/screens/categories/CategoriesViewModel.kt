package kopycinski.tomasz.klamkify.ui.screens.categories

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kopycinski.tomasz.klamkify.data.entity.Category
import kopycinski.tomasz.klamkify.data.repository.CategoryRepository
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class CategoriesViewModel @Inject constructor(
    private val categoryRepository: CategoryRepository
) : ViewModel() {
    var categoryList = mutableStateOf<List<Category>>(listOf())
        private set

    init {
        runBlocking {
            categoryRepository.insert(Category("Testowa"))
            categoryList.value = categoryRepository.getAll()
        }
    }
}