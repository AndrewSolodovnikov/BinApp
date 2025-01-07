package com.sol.binapp.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction

@Dao
interface BinDao {
    @Transaction
    @Query("SELECT * FROM bin")
    suspend fun getAllBinsWithNumbers(): List<BinWithNumber>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBin(bin: BIN)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBinNumber(binNumber: BinNumber)

    @Query("SELECT * FROM binnumber WHERE number = :binNumber")
    suspend fun getBinByNumber(binNumber: String): BinNumber?
}
