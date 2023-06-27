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
            childColumns = arrayOf("parentCategoryId")
        )
    ],
    indices = [Index("parentCategoryId")]
)
data class Activity(
    val name: String,
    val parentCategoryId: Long,
    val archived: Boolean = false,
    @PrimaryKey(autoGenerate = true) val activityId: Long = 0
)