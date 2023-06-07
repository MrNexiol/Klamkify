package kopycinski.tomasz.domain.usecase

import kopycinski.tomasz.data.local.repository.ActivityRepository
import kopycinski.tomasz.domain.model.toModel
import javax.inject.Inject

class GetActivityListUseCase @Inject constructor(
    private val repository: ActivityRepository
) {
    suspend operator fun invoke() = repository.getAll().map { it.toModel() }
}