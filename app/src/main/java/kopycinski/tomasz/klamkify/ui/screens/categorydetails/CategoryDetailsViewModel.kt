package kopycinski.tomasz.klamkify.ui.screens.categorydetails

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kopycinski.tomasz.domain.model.Session
import kopycinski.tomasz.domain.usecase.ArchiveCategoryUseCase
import kopycinski.tomasz.domain.usecase.GetCategoryUseCase
import kopycinski.tomasz.domain.usecase.GetSessionListUseCase
import kopycinski.tomasz.domain.usecase.GetTotalTimeSpentOnCategoryUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryDetailsViewModel @Inject constructor(
    private val getCategoryUseCase: GetCategoryUseCase,
    private val archiveCategoryUseCase: ArchiveCategoryUseCase,
    private val getTotalTimeSpentOnCategoryUseCase: GetTotalTimeSpentOnCategoryUseCase,
    private val getSessionListUseCase: GetSessionListUseCase
): ViewModel() {
    private var id = -1L
    var categoryName = mutableStateOf("")
        private set
    var totalTime = mutableStateOf(0L)
        private set
    var sessionsList = mutableStateOf<List<Session>>(listOf())
        private set

    fun refreshData(categoryId: Long) = viewModelScope.launch {
        getCategoryUseCase(categoryId).apply {
            id = this.categoryId
            categoryName.value = this.name
        }
        totalTime.value = getTotalTimeSpentOnCategoryUseCase(categoryId)
        sessionsList.value = getSessionListUseCase(categoryId)
    }

    fun deleteCategory() = viewModelScope.launch {
        archiveCategoryUseCase(id)
    }
}