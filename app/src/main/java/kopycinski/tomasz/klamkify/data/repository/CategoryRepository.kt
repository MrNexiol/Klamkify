package kopycinski.tomasz.klamkify.data.repository

import kopycinski.tomasz.klamkify.data.dao.CategoryDao
import kopycinski.tomasz.klamkify.data.entity.Category
import javax.inject.Inject

class CategoryRepository @Inject constructor(
    private val categoryDao: CategoryDao
) {
    suspend fun insert(category: Category) = categoryDao.insert(category)

    suspend fun update(category: Category) = categoryDao.update(category)

    suspend fun getUnarchived() = categoryDao.getAllUnarchived()

    suspend fun getById(id: Long) = categoryDao.getById(id)
}