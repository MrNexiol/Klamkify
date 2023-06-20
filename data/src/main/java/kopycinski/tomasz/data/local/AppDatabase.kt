package kopycinski.tomasz.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import kopycinski.tomasz.data.local.dao.ActivityDao
import kopycinski.tomasz.data.local.dao.CategoryDao
import kopycinski.tomasz.data.local.dao.SessionDao
import kopycinski.tomasz.data.local.entity.Activity
import kopycinski.tomasz.data.local.entity.Category
import kopycinski.tomasz.data.local.entity.Session

@Database(
    entities = [
        Activity::class,
        Category::class,
        Session::class
    ],
    version = 1,
    exportSchema = true
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun activityDao(): ActivityDao
    abstract fun categoryDao(): CategoryDao
    abstract fun sessionDao(): SessionDao
}