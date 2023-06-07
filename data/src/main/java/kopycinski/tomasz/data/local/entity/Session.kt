package kopycinski.tomasz.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity(
    foreignKeys = [
        ForeignKey(
            entity = Activity::class,
            parentColumns = arrayOf("activityId"),
            childColumns = arrayOf("activityId"),
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index("activityId")]
)
data class Session(
    val timeInSeconds: Long,
    val activityId: Long,
    @ColumnInfo(defaultValue = "2023-04-26") val date: LocalDate,
    @PrimaryKey(autoGenerate = true) val sessionId: Long = 0
)