package kopycinski.tomasz.domain.usecase

import kopycinski.tomasz.data.local.repository.CategoryRepository
import kopycinski.tomasz.domain.model.toModel
import javax.inject.Inject

class GetUnarchivedCategoryListUseCase @Inject constructor(
    private val repository: CategoryRepository
) {
    suspend operator fun invoke() = repository.getUnarchived().map { it.toModel() }
}