package kopycinski.tomasz.klamkify.ui.screens.categorylist

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kopycinski.tomasz.domain.model.Category
import kopycinski.tomasz.domain.usecase.GetCategoryListUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryListViewModel @Inject constructor(
    private val getCategoryListUseCase: GetCategoryListUseCase
) : ViewModel() {
    var categoryList = mutableStateOf<List<Category>>(listOf())
        private set

    fun update() = viewModelScope.launch {
        categoryList.value = getCategoryListUseCase()
    }
}