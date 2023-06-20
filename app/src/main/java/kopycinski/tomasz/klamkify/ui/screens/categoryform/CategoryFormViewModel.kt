package kopycinski.tomasz.klamkify.ui.screens.categoryform

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kopycinski.tomasz.domain.usecase.CreateCategoryUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryFormViewModel @Inject constructor(
    private val createCategoryUseCase: CreateCategoryUseCase
) : ViewModel() {
    fun createCategory(title: String) = viewModelScope.launch {
        createCategoryUseCase(title)
    }
}