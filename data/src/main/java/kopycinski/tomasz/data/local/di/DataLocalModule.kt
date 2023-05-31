package kopycinski.tomasz.data.local.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kopycinski.tomasz.data.local.AppDatabase
import kopycinski.tomasz.data.local.dao.CategoryDao
import kopycinski.tomasz.data.local.dao.SessionDao
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataLocalModule {
    @Provides
    fun provideSessionDao(appDatabase: AppDatabase): SessionDao =
        appDatabase.sessionDao()

    @Provides
    fun provideCategoryDao(appDatabase: AppDatabase): CategoryDao =
        appDatabase.categoryDao()

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "klamkify-database"
        ).build()
}