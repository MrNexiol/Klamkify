package kopycinski.tomasz.domain.usecase

import kopycinski.tomasz.data.local.repository.CategoryRepository
import javax.inject.Inject

class ArchiveCategoryUseCase @Inject constructor(
    private val repository: CategoryRepository
) {
    suspend operator fun invoke(id: Long) = repository.archive(id)
}