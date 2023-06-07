package kopycinski.tomasz.data.local.repository

import kopycinski.tomasz.data.local.dao.SessionDao
import kopycinski.tomasz.data.local.entity.Session
import javax.inject.Inject

class SessionRepository @Inject constructor(
    private val sessionDao: SessionDao
) {
    suspend fun insert(session: Session) = sessionDao.insert(session)

    suspend fun getAllById(categoryId: Long) = sessionDao.getAllByActivityId(categoryId)
}