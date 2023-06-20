package kopycinski.tomasz.data.local.repository

import kopycinski.tomasz.data.local.dao.CategoryDao
import kopycinski.tomasz.data.local.entity.Category
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CategoryRepository @Inject constructor(
    private val categoryDao: CategoryDao
) {
    suspend fun insert(category: Category) = categoryDao.insert(category)

    fun getCategories(): Flow<List<Category>> {
        return categoryDao.getAll()
    }
}