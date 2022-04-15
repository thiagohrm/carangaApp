package com.example.carangaapp.di

import android.app.Application
import androidx.room.Room
import com.example.carangaapp.caraddscreen.data.remote.CarMakesListApi
import com.example.carangaapp.caraddscreen.data.remote.CarModelsListApi
import com.example.carangaapp.caraddscreen.data.repository.CarMakeListRepositoryImpl
import com.example.carangaapp.caraddscreen.data.repository.CarModelListRepositoryImpl
import com.example.carangaapp.caraddscreen.domain.repository.CarMakeListRepository
import com.example.carangaapp.caraddscreen.domain.repository.CarModelListRepository
import com.example.carangaapp.fueltracking.data.local.FuelTrackingDatabase
import com.example.carangaapp.mainscreen.data.local.CarDatabase
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
private const val CAR_MODELS_LIST_BASE_URL = "https://vpic.nhtsa.dot.gov/api/vehicles/getmodelsformake/"

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
    fun provideFuelTrackingDatabase(app: Application) : FuelTrackingDatabase{
        return Room.databaseBuilder(
            app,
            FuelTrackingDatabase::class.java,
            "fueltracking"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

//    @Provides
//    @Singleton
//    fun provideCarRepository(db : CarDatabase) : CarRepositoryImpl {
//        return CarRepositoryImpl(db.dao)
//    }

    @Provides
    @Singleton
    fun provideCarMakesListApi() : CarMakesListApi = Retrofit.Builder()
        .baseUrl(CAR_MAKES_LIST_BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(CarMakesListApi::class.java)

    @Provides
    @Singleton
    fun provideCarMakesListRepository(api : CarMakesListApi) : CarMakeListRepository = CarMakeListRepositoryImpl(api)

    @Provides
    @Singleton
    fun provideCarModelListApi() : CarModelsListApi = Retrofit.Builder()
        .baseUrl(CAR_MODELS_LIST_BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(CarModelsListApi::class.java)

    @Provides
    @Singleton
    fun provideCarModelsListRepository(api : CarModelsListApi) : CarModelListRepository = CarModelListRepositoryImpl(api)

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