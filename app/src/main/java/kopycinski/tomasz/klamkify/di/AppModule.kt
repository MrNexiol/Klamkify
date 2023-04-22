package kopycinski.tomasz.klamkify.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kopycinski.tomasz.klamkify.data.AppDatabase
import kopycinski.tomasz.klamkify.data.dao.CategoryDao

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    fun provideCategoryDao(appDatabase: AppDatabase): CategoryDao =
        appDatabase.categoryDao()

    @Provides
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "klamkify-database"
        ).build()
}