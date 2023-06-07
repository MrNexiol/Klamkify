package kopycinski.tomasz.domain.usecase

import kopycinski.tomasz.data.local.repository.ActivityRepository
import kopycinski.tomasz.domain.model.toModel
import javax.inject.Inject

class GetActivityUseCase @Inject constructor(
    private val activityRepository: ActivityRepository
) {
    suspend operator fun invoke(id: Long) =
        activityRepository.getById(id).toModel()
}