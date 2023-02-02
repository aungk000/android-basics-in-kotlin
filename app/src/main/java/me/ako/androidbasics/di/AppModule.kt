package me.ako.androidbasics.di

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import me.ako.androidbasics.data.datasource.AppDatabase
import me.ako.androidbasics.data.controller.DataRepositoryImpl
import me.ako.androidbasics.domain.controller.DataRepository
import me.ako.androidbasics.presentation.util.Utils
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideAppDatabase(app: Application): AppDatabase {
        return Room.databaseBuilder(
            app,
            AppDatabase::class.java,
            "app_db"
        ).build()
    }

    @Singleton
    @Provides
    fun provideDataRepository(db: AppDatabase): DataRepository {
        return DataRepositoryImpl(db.dao)
    }

    @Singleton
    @Provides
    fun getUtils() : Utils = Utils()
}