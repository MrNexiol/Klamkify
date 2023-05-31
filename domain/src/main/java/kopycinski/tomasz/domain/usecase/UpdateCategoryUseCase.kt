package kopycinski.tomasz.domain.usecase

import kopycinski.tomasz.data.local.entity.Category
import kopycinski.tomasz.data.local.repository.CategoryRepository
import javax.inject.Inject

class UpdateCategoryUseCase @Inject constructor(
    private val repository: CategoryRepository
) {
    suspend operator fun invoke(id: Long, name: String) = repository.update(
        Category(
            name = name,
            archived = false,
            categoryId = id
        )
    )
}