package com.example.carangaapp.mainscreen.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [CarEntity::class],
    version = 1
)
abstract class CarDatabase : RoomDatabase() {
    abstract val dao : CarDAO
}