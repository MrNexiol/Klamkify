package kopycinski.tomasz.klamkify.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kopycinski.tomasz.klamkify.data.entity.Session

@Dao
interface SessionDao {
    @Insert
    suspend fun insert(session: Session)

    @Query("SELECT SUM(timeInSeconds) FROM session WHERE categoryId = :id")
    suspend fun getSumByCategoryId(id: Long): Int
}