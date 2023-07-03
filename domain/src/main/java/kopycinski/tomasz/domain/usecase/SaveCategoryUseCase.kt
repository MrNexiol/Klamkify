package kopycinski.tomasz.domain.usecase

import kopycinski.tomasz.data.local.entity.Category
import kopycinski.tomasz.data.local.repository.CategoryRepository
import javax.inject.Inject

class SaveCategoryUseCase @Inject constructor(
    private val categoryRepository: CategoryRepository
) {
    suspend operator fun invoke(name: String, categoryId: Long) {
        if (categoryId == -1L) {
            categoryRepository.insert(
                Category(name)
            )
        } else {
            categoryRepository.update(
                Category(name, archived = false, categoryId)
            )
        }
    }
}