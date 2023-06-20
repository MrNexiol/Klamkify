package kopycinski.tomasz.data.local.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    foreignKeys = [
        ForeignKey(
            entity = Category::class,
            parentColumns = arrayOf("categoryId"),
            childColumns = arrayOf("categoryId")
        )
    ],
    indices = [Index("categoryId")]
)
data class Activity(
    val name: String,
    val archived: Boolean = false,
    val categoryId: Long,
    @PrimaryKey(autoGenerate = true) val activityId: Long = 0
)