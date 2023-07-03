package kopycinski.tomasz.klamkify.ui.screens.activitydetails

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kopycinski.tomasz.domain.model.Session
import kopycinski.tomasz.domain.usecase.ArchiveActivityUseCase
import kopycinski.tomasz.domain.usecase.GetActivityUseCase
import kopycinski.tomasz.domain.usecase.GetSessionListUseCase
import kopycinski.tomasz.klamkify.ui.navigation.DestinationArgs.ACTIVITY_ID_ARG
import kotlinx.coroutines.launch
import javax.inject.Inject

data class ActivityDetailsUIState(
    val activityName: String = "",
    val totalTime: Long = 0L,
    val sessionList: List<Session> = listOf(),
    val isDialogShowing: Boolean = false
)

@HiltViewModel
class ActivityDetailsViewModel @Inject constructor(
    private val getActivityUseCase: GetActivityUseCase,
    private val archiveActivityUseCase: ArchiveActivityUseCase,
    private val getSessionListUseCase: GetSessionListUseCase,
    savedStateHandle: SavedStateHandle
): ViewModel() {
    private val activityId: Long = savedStateHandle[ACTIVITY_ID_ARG]!!

    private var _uiState = mutableStateOf(ActivityDetailsUIState())
    val uiState: State<ActivityDetailsUIState> = _uiState

    init {
        refreshData()
    }

    private fun refreshData() = viewModelScope.launch {
        getActivityUseCase(activityId).also {
            _uiState.value = _uiState.value.copy(activityName = it.name)
        }
        getSessionListUseCase(activityId).also { sessions ->
            _uiState.value = _uiState.value.copy(
                totalTime = sessions.sumOf { it.timeInSeconds },
                sessionList = sessions
            )
        }
    }

    fun showDialog() {
        _uiState.value = _uiState.value.copy(isDialogShowing = true)
    }

    fun dismissDialog() {
        _uiState.value = _uiState.value.copy(isDialogShowing = false)
    }

    fun archiveActivity() = viewModelScope.launch {
        archiveActivityUseCase(activityId)
    }
}