package kopycinski.tomasz.klamkify.ui.screens.sessions

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kopycinski.tomasz.klamkify.data.repository.SessionRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SessionsViewModel @Inject constructor(
    private val sessionRepository: SessionRepository
): ViewModel() {
    var totalTime = mutableStateOf(0)

    fun refreshData(categoryId: Long) = viewModelScope.launch {
        totalTime.value = sessionRepository.getTotalTime(categoryId)
    }
}