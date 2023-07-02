package kopycinski.tomasz.data.local.repository

import kopycinski.tomasz.data.local.dao.CategoryDao
import kopycinski.tomasz.data.local.entity.Activity
import kopycinski.tomasz.data.local.entity.Category
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CategoryRepository @Inject constructor(
    private val categoryDao: CategoryDao
) {
    suspend fun insert(category: Category) = categoryDao.insert(category)

    suspend fun update(category: Category) = categoryDao.update(category)

    suspend fun getById(id: Long) = categoryDao.getById(id)

    fun getCategories(): Flow<List<Category>> {
        return categoryDao.getAll()
    }

    fun getCategoriesWithActivities(): Flow<Map<Category, List<Activity>>> {
        return categoryDao.getAllWithActivities()
    }
}