package kopycinski.tomasz.domain.usecase

import kopycinski.tomasz.data.local.entity.Activity
import kopycinski.tomasz.data.local.repository.ActivityRepository
import javax.inject.Inject

class UpdateActivityUseCase @Inject constructor(
    private val repository: ActivityRepository
) {
    suspend operator fun invoke(id: Long, name: String) = repository.update(
        Activity(
            name = name,
            archived = false,
            activityId = id,
            parentCategoryId = 0L
        )
    )
}