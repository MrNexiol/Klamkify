package kopycinski.tomasz.domain.usecase

import kopycinski.tomasz.data.local.entity.Activity
import kopycinski.tomasz.data.local.repository.ActivityRepository
import javax.inject.Inject

class SaveActivityUseCase @Inject constructor(
    private val repository: ActivityRepository
) {
    suspend operator fun invoke(name: String, categoryId: Long, activityId: Long) {
        if (activityId == -1L) {
            repository.insert(
                Activity(name, categoryId, false)
            )
        } else {
            repository.update(
                Activity(name, categoryId, false, activityId)
            )
        }
    }
}