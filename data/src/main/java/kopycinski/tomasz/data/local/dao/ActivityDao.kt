package kopycinski.tomasz.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kopycinski.tomasz.data.local.entity.Activity

@Dao
interface ActivityDao {
    @Insert
    suspend fun insert(activity: Activity)

    @Update
    suspend fun update(activity: Activity)

    @Query("UPDATE activity SET archived = 1 WHERE activityId =:id")
    suspend fun archive(id: Long)

    @Query("SELECT * FROM activity WHERE archived = 0")
    suspend fun getAll(): List<Activity>

    @Query("SELECT * FROM activity WHERE activityId = :id")
    suspend fun getById(id: Long): Activity
}