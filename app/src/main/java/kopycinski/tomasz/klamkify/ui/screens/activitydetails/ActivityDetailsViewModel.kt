package kopycinski.tomasz.klamkify.ui.screens.activitydetails

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kopycinski.tomasz.domain.model.Session
import kopycinski.tomasz.domain.usecase.ArchiveActivityUseCase
import kopycinski.tomasz.domain.usecase.GetActivityUseCase
import kopycinski.tomasz.domain.usecase.GetSessionListUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ActivityDetailsViewModel @Inject constructor(
    private val getActivityUseCase: GetActivityUseCase,
    private val archiveActivityUseCase: ArchiveActivityUseCase,
    private val getSessionListUseCase: GetSessionListUseCase
): ViewModel() {
    private var id = -1L
    var activityName = mutableStateOf("")
        private set
    var totalTime = mutableStateOf(0L)
        private set
    var sessionsList = mutableStateOf<List<Session>>(listOf())
        private set

    fun refreshData(activityId: Long) = viewModelScope.launch {
        getActivityUseCase(activityId).also {
            id = it.activityId
            activityName.value = it.name
        }
        getSessionListUseCase(activityId).also { sessions ->
            sessionsList.value = sessions
            totalTime.value = sessions.sumOf { it.timeInSeconds }
        }
    }

    fun archiveActivity() = viewModelScope.launch {
        archiveActivityUseCase(id)
    }
}