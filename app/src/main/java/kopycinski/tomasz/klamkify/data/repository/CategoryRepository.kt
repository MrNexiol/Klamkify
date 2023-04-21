package kopycinski.tomasz.klamkify.data.repository

import kopycinski.tomasz.klamkify.data.dao.CategoryDao
import kopycinski.tomasz.klamkify.data.entity.Category
import javax.inject.Inject

class CategoryRepository @Inject constructor(
    private val categoryDao: CategoryDao
) {
    suspend fun insert(category: Category) = categoryDao.insert(category)

    suspend fun getAll() = categoryDao.getAll()
}