package kopycinski.tomasz.klamkify.ui.screens.categorylist

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kopycinski.tomasz.domain.model.Activity
import kopycinski.tomasz.domain.usecase.GetActivityListUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ActivityListViewModel @Inject constructor(
    private val getActivityListUseCase: GetActivityListUseCase
) : ViewModel() {
    var activityList = mutableStateOf<List<Activity>>(listOf())
        private set

    fun update() = viewModelScope.launch {
        activityList.value = getActivityListUseCase()
    }
}