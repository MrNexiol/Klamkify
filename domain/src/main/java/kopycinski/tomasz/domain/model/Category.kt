package kopycinski.tomasz.domain.model

import kopycinski.tomasz.data.local.entity.Category as CategoryEntity

data class Category(
    val name: String,
    val archived: Boolean,
    val categoryId: Long
)

fun CategoryEntity.toModel(): Category =
    Category(
        name = this.name,
        archived = this.archived,
        categoryId = this.categoryId
    )
