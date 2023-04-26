package kopycinski.tomasz.klamkify.ui.screens.categorydetails

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kopycinski.tomasz.klamkify.data.entity.Category
import kopycinski.tomasz.klamkify.data.entity.Session
import kopycinski.tomasz.klamkify.data.repository.CategoryRepository
import kopycinski.tomasz.klamkify.data.repository.SessionRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryDetailsViewModel @Inject constructor(
    private val categoryRepository: CategoryRepository,
    private val sessionRepository: SessionRepository
): ViewModel() {
    var category = mutableStateOf(Category(""))
        private set
    var totalTime = mutableStateOf(0)
        private set
    var sessionsList = mutableStateOf<List<Session>>(listOf())
        private set

    fun refreshData(categoryId: Long) = viewModelScope.launch {
        category.value = categoryRepository.getById(categoryId)
        totalTime.value = sessionRepository.getTotalTime(categoryId)
        sessionsList.value = sessionRepository.getAllById(categoryId)
    }

    fun deleteCategory() = viewModelScope.launch {
        categoryRepository.update(category.value.copy(archived = true))
    }
}