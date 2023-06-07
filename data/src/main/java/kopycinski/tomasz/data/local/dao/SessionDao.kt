package kopycinski.tomasz.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kopycinski.tomasz.data.local.entity.Session

@Dao
interface SessionDao {
    @Insert
    suspend fun insert(session: Session)

    @Query("SELECT * FROM session WHERE activityId = :id ORDER BY sessionId DESC")
    suspend fun getAllByActivityId(id: Long): List<Session>
}