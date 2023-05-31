package kopycinski.tomasz.domain.usecase

import kopycinski.tomasz.data.local.entity.Session
import kopycinski.tomasz.data.local.repository.SessionRepository
import java.time.LocalDate
import javax.inject.Inject

class SaveSessionUseCase @Inject constructor(
    private val repository: SessionRepository
) {
    suspend operator fun invoke(timeInSeconds: Long, categoryId: Long) = repository.insert(
        Session(
            timeInSeconds,
            categoryId,
            LocalDate.now()
        )
    )
}