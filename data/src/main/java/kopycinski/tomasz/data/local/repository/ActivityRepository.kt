package kopycinski.tomasz.data.local.repository

import kopycinski.tomasz.data.local.dao.ActivityDao
import kopycinski.tomasz.data.local.entity.Activity
import javax.inject.Inject

class ActivityRepository @Inject constructor(
    private val activityDao: ActivityDao
) {
    suspend fun insert(activity: Activity) = activityDao.insert(activity)

    suspend fun update(activity: Activity) = activityDao.update(activity)

    suspend fun getAll() = activityDao.getAll()

    suspend fun getById(id: Long) = activityDao.getById(id)

    suspend fun archive(id: Long) = activityDao.archive(id)
}