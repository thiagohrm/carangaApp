package com.example.carangaapp.di

import android.app.Application
import androidx.room.Room
import com.example.carangaapp.data.CarDatabase
import com.example.carangaapp.data.CarRepoImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideCarDatabase(app: Application) : CarDatabase {
        return Room.databaseBuilder(
            app,
            CarDatabase::class.java,
            "car"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideCarRepository(db : CarDatabase) : CarRepoImpl{
        return CarRepoImpl(db.dao)
    }
}