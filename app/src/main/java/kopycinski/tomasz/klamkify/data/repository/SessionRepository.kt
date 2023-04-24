package kopycinski.tomasz.klamkify.data.repository

import kopycinski.tomasz.klamkify.data.dao.SessionDao
import kopycinski.tomasz.klamkify.data.entity.Session
import javax.inject.Inject

class SessionRepository @Inject constructor(
    private val sessionDao: SessionDao
) {
    suspend fun insert(session: Session) = sessionDao.insert(session)
}