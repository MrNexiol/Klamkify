package kopycinski.tomasz.klamkify.data

import androidx.room.Database
import androidx.room.RoomDatabase
import kopycinski.tomasz.klamkify.data.dao.CategoryDao
import kopycinski.tomasz.klamkify.data.dao.SessionDao
import kopycinski.tomasz.klamkify.data.entity.Category
import kopycinski.tomasz.klamkify.data.entity.Session

@Database(
    entities = [Category::class, Session::class],
    version = 1,
    exportSchema = true
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun categoryDao(): CategoryDao
    abstract fun sessionDao(): SessionDao
}