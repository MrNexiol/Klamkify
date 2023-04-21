package kopycinski.tomasz.klamkify.data

import androidx.room.Database
import kopycinski.tomasz.klamkify.data.dao.CategoryDao
import kopycinski.tomasz.klamkify.data.entity.Category

@Database(entities = [Category::class], version = 1)
abstract class AppDatabase {
    abstract fun categoryDao(): CategoryDao
}