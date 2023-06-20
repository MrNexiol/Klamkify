package kopycinski.tomasz.domain.usecase

import kopycinski.tomasz.data.local.entity.Category
import kopycinski.tomasz.data.local.repository.CategoryRepository
import javax.inject.Inject

class CreateCategoryUseCase @Inject constructor(
    private val categoryRepository: CategoryRepository
) {
    suspend operator fun invoke() = categoryRepository.insert(
        Category("Eluwina")
    )
}