package kopycinski.tomasz.klamkify.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kopycinski.tomasz.klamkify.data.entity.Session

@Dao
interface SessionDao {
    @Insert
    suspend fun insert(session: Session)

    @Query("SELECT COALESCE(SUM(timeInSeconds), 0) FROM session WHERE categoryId = :id")
    suspend fun getSumByCategoryId(id: Long): Int

    @Query("SELECT * FROM session WHERE categoryId = :id ORDER BY sessionId DESC")
    suspend fun getAllByCategoryId(id: Long): List<Session>
}