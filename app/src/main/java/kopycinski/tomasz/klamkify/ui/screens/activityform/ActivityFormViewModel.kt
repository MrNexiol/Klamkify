package kopycinski.tomasz.klamkify.ui.screens.activityform

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kopycinski.tomasz.domain.usecase.CreateActivityUseCase
import kopycinski.tomasz.domain.usecase.GetActivityUseCase
import kopycinski.tomasz.domain.usecase.UpdateActivityUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ActivityFormViewModel @Inject constructor(
    private val getActivityUseCase: GetActivityUseCase,
    private val createActivityUseCase: CreateActivityUseCase,
    private val updateActivityUseCase: UpdateActivityUseCase
): ViewModel() {
    var activityId = mutableStateOf(-1L)
        private set
    var activityName = mutableStateOf("")
        private set

    fun update(name: String) {
        activityName.value = name
    }

    fun save(onSuccess: () -> Unit) = viewModelScope.launch {
        if (activityId.value == -1L) {
            createActivityUseCase(activityName.value)
        } else {
            updateActivityUseCase(activityId.value, activityName.value)
        }
        onSuccess()
    }

    fun getActivity(id: Long) = viewModelScope.launch {
        getActivityUseCase(id).also {
            activityId.value = it.activityId
            activityName.value = it.name
        }
    }
}