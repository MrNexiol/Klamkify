package kopycinski.tomasz.domain.usecase

import kopycinski.tomasz.data.local.entity.Activity
import kopycinski.tomasz.data.local.repository.ActivityRepository
import javax.inject.Inject

class CreateActivityUseCase @Inject constructor(
    private val repository: ActivityRepository
) {
    suspend operator fun invoke(name: String, categoryId: Long) = repository.insert(
        Activity(name, categoryId, false)
    )
}