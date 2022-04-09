package com.example.carangaapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.carangaapp.data.dao.CarDAO
import com.example.carangaapp.data.models.CarModel

@Database(
    entities = [CarModel::class],
    version = 1
)
abstract class CarDatabase : RoomDatabase() {
    abstract val dao : CarDAO
}