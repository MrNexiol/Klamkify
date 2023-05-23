package kopycinski.tomasz.klamkify.ui.screens.categorylist

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kopycinski.tomasz.klamkify.data.entity.Category
import kopycinski.tomasz.klamkify.data.entity.Session
import kopycinski.tomasz.klamkify.data.repository.CategoryRepository
import kopycinski.tomasz.klamkify.data.repository.SessionRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class CategoryListViewModel @Inject constructor(
    private val categoryRepository: CategoryRepository,
    private val sessionRepository: SessionRepository
) : ViewModel() {
    private val currentDate = LocalDate.now()
    private var timerJob: Job? = null
    var categoryList = mutableStateOf<List<Category>>(listOf())
        private set
    var activeCategoryID = mutableStateOf(-1)
        private set
    var isRunning = mutableStateOf(false)
        private set
    var currentTime = mutableStateOf(0)
        private set

    private var startTime: Long = 0

    fun update() = viewModelScope.launch {
        categoryList.value = categoryRepository.getUnarchived()
    }

    fun onStart(index: Int) {
        activeCategoryID.value = index
        isRunning.value = true
        startTimer()
    }

    fun onStop(categoryId: Long) {
        activeCategoryID.value = -1
        isRunning.value = false
        stopTimer()
        viewModelScope.launch {
            sessionRepository.insert(
                Session(
                    timeInSeconds = currentTime.value,
                    categoryId = categoryId,
                    currentDate
                )
            )
            currentTime.value = 0
        }
    }

    private fun startTimer() {
        startTime = System.currentTimeMillis()
        timerJob = viewModelScope.launch {
            while (isRunning.value) {
                delay(1000)
                currentTime.value = ((System.currentTimeMillis() - startTime) / 1000).toInt()
            }
        }
    }

    private fun stopTimer() {
        timerJob?.cancel()
        timerJob = null
    }
}