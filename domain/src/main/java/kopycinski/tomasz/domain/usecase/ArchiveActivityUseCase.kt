package kopycinski.tomasz.domain.usecase

import kopycinski.tomasz.data.local.repository.ActivityRepository
import javax.inject.Inject

class ArchiveActivityUseCase @Inject constructor(
    private val repository: ActivityRepository
) {
    suspend operator fun invoke(id: Long) = repository.archive(id)
}