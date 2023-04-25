package kopycinski.tomasz.klamkify.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kopycinski.tomasz.klamkify.data.entity.Category

@Dao
interface CategoryDao {
    @Insert
    suspend fun insert(category: Category)

    @Update
    suspend fun update(category: Category)

    @Query("SELECT * FROM category WHERE archived = 0")
    suspend fun getAllUnarchived(): List<Category>

    @Query("SELECT * FROM category WHERE categoryId = :id")
    suspend fun getById(id: Long): Category
}