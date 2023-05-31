package kopycinski.tomasz.data.local.repository

import kopycinski.tomasz.data.local.dao.CategoryDao
import kopycinski.tomasz.data.local.entity.Category
import javax.inject.Inject

class CategoryRepository @Inject constructor(
    private val categoryDao: CategoryDao
) {
    suspend fun insert(category: Category) = categoryDao.insert(category)

    suspend fun update(category: Category) = categoryDao.update(category)

    suspend fun getUnarchived() = categoryDao.getAllUnarchived()

    suspend fun getById(id: Long) = categoryDao.getById(id)

    suspend fun archive(id: Long) = categoryDao.archive(id)
}