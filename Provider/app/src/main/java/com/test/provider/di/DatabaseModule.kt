package com.test.provider.di

import android.content.Context
import androidx.room.Room
import com.test.provider.db.AppDatabase
import com.test.provider.db.repo.DBRepoImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "MyDataBase"
        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    @Singleton
    fun provideDBRepo(appDatabase: AppDatabase): DBRepoImpl = DBRepoImpl(appDatabase = appDatabase)
}