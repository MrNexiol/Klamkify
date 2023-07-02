package kopycinski.tomasz.domain.usecase

import kopycinski.tomasz.data.local.repository.CategoryRepository
import kopycinski.tomasz.domain.model.Category
import kopycinski.tomasz.domain.model.toModel
import javax.inject.Inject

class GetCategoryUseCase @Inject constructor(
    private val categoryRepository: CategoryRepository
) {
    suspend operator fun invoke(categoryId: Long): Category {
        return categoryRepository.getById(categoryId).toModel()
    }
}