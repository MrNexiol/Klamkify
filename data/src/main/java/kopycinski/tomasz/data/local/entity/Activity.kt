package kopycinski.tomasz.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Activity(
    val name: String,
    val archived: Boolean = false,
    @PrimaryKey(autoGenerate = true) val activityId: Long = 0
)