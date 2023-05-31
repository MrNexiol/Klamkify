package kopycinski.tomasz.domain.usecase

import kopycinski.tomasz.data.local.repository.SessionRepository
import kopycinski.tomasz.domain.model.toModel
import javax.inject.Inject

class GetSessionListUseCase @Inject constructor(
    private val repository: SessionRepository
) {
    suspend operator fun invoke(categoryId: Long) =
        repository.getAllById(categoryId).map { it.toModel() }
}