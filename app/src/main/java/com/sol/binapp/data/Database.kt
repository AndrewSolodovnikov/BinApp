package com.sol.binapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(
    entities = [Bank::class, BIN::class, Country::class, BinNumber::class],
    version = 1,
    exportSchema = false
)

@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun binDao(): BinDao
}
