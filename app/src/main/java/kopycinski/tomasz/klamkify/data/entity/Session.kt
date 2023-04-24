package kopycinski.tomasz.klamkify.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Session(
    val timeInSeconds: Int,
    val categoryId: Long,
    @PrimaryKey(autoGenerate = true) val sessionId: Long = 0
)
