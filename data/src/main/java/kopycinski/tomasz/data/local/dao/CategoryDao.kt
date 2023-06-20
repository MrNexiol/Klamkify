package kopycinski.tomasz.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kopycinski.tomasz.data.local.entity.Category
import kotlinx.coroutines.flow.Flow

@Dao
interface CategoryDao {
    @Insert
    suspend fun insert(category: Category)

    @Update
    suspend fun update(category: Category)

    @Query("UPDATE category SET archived = 1 WHERE categoryId =:id")
    suspend fun archive(id: Long)

    @Query("SELECT * FROM category WHERE archived = 0")
    fun getAll(): Flow<List<Category>>

    @Query("SELECT * FROM category WHERE categoryId=:id")
    suspend fun getById(id: Long): Category
}