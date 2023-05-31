package kopycinski.tomasz.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity(foreignKeys = [
    ForeignKey(
        entity = Category::class,
        parentColumns = arrayOf("categoryId"),
        childColumns = arrayOf("categoryId"),
        onDelete = ForeignKey.CASCADE
    )
])
data class Session(
    val timeInSeconds: Long,
    val categoryId: Long,
    @ColumnInfo(defaultValue = "2023-04-26") val date: LocalDate,
    @PrimaryKey(autoGenerate = true) val sessionId: Long = 0
)