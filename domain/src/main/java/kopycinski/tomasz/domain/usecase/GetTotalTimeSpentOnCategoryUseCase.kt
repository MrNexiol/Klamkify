package kopycinski.tomasz.domain.usecase

import kopycinski.tomasz.data.local.repository.SessionRepository
import javax.inject.Inject

class GetTotalTimeSpentOnCategoryUseCase @Inject constructor(
    private val sessionRepository: SessionRepository
) {
    suspend operator fun invoke(categoryId: Long): Long =
        sessionRepository.getTotalTime(categoryId)
}