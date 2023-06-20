package kopycinski.tomasz.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Category(
    val name: String,
    val archived: Boolean = false,
    @PrimaryKey(autoGenerate = true) val categoryId: Long = 0
)
