package kopycinski.tomasz.klamkify.ui.screens.activitytimer

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kopycinski.tomasz.domain.usecase.GetActivityUseCase
import kopycinski.tomasz.klamkify.ui.navigation.DestinationArgs.ACTIVITY_ID_ARG
import kotlinx.coroutines.launch
import javax.inject.Inject

data class ActivityTimerUIState(
    val activityId: Long,
    val activityName: String = ""
)

@HiltViewModel
class ActivityTimerViewModel @Inject constructor(
    private val getActivityUseCase: GetActivityUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val activityId: Long = savedStateHandle[ACTIVITY_ID_ARG]!!

    private var _uiState = mutableStateOf(ActivityTimerUIState(activityId))
    val uiState: State<ActivityTimerUIState> = _uiState

    init {
        getActivity()
    }

    private fun getActivity() = viewModelScope.launch {
        _uiState.value = _uiState.value.copy(activityName = getActivityUseCase(activityId).name)
    }
}