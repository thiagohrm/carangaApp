package com.example.carangaapp.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [CarModel::class],
    version = 1
)
abstract class CarDatabase : RoomDatabase() {
    abstract val dao : CarDAO
}