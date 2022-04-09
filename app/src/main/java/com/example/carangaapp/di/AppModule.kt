package com.example.carangaapp.di

import android.app.Application
import androidx.room.Room
import com.example.carangaapp.data.CarMakesListApi
import com.example.carangaapp.data.database.CarDatabase
import com.example.carangaapp.data.repository.CarMakeListRepo
import com.example.carangaapp.data.repository.CarMakeListRepoImpl
import com.example.carangaapp.data.repository.CarRepoImpl
import com.example.carangaapp.utils.DispatcherUtils
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

private const val CAR_MAKES_LIST_BASE_URL = "https://vpic.nhtsa.dot.gov/api/vehicles/"

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
    fun provideCarRepository(db : CarDatabase) : CarRepoImpl {
        return CarRepoImpl(db.dao)
    }

    @Provides
    @Singleton
    fun provideCarMakesListApi() : CarMakesListApi = Retrofit.Builder()
        .baseUrl(CAR_MAKES_LIST_BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(CarMakesListApi::class.java)

    @Provides
    @Singleton
    fun provideCarMakesListRepository(api : CarMakesListApi) : CarMakeListRepo = CarMakeListRepoImpl(api)

    @Provides
    @Singleton
    fun provideDispatchers() : DispatcherUtils = object : DispatcherUtils{
        override val main: CoroutineDispatcher
            get() = Dispatchers.Main
        override val io: CoroutineDispatcher
            get() = Dispatchers.IO
        override val default: CoroutineDispatcher
            get() = Dispatchers.Default
        override val unconfined: CoroutineDispatcher
            get() = Dispatchers.Unconfined

    }
}