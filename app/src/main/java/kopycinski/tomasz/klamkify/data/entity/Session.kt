package kopycinski.tomasz.klamkify.data.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(foreignKeys = [
    ForeignKey(
        entity = Category::class,
        parentColumns = arrayOf("categoryId"),
        childColumns = arrayOf("categoryId"),
        onDelete = ForeignKey.CASCADE
    )
])
data class Session(
    val timeInSeconds: Int,
    val categoryId: Long,
    @PrimaryKey(autoGenerate = true) val sessionId: Long = 0
)
