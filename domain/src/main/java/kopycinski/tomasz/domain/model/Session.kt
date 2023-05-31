package kopycinski.tomasz.domain.model

import kopycinski.tomasz.data.local.entity.Session as Entity
import java.time.LocalDate

data class Session(
    val timeInSeconds: Long,
    val categoryId: Long,
    val date: LocalDate,
    val sessionId: Long
)

fun Entity.toModel(): Session =
    Session(
        timeInSeconds = this.timeInSeconds,
        categoryId = this.categoryId,
        date = this.date,
        sessionId = this.sessionId
    )