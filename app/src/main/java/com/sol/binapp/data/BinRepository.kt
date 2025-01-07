package com.sol.binapp.data

import android.util.Log
import com.sol.binapp.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import java.io.IOException
import javax.inject.Inject

class BinRepository @Inject constructor(
    private val binDao: BinDao,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    private val service: BinApiService
) {

    suspend fun getBinFromCache(): List<BinWithNumber> {
        return withContext(ioDispatcher) {
            binDao.getAllBinsWithNumbers()
        }
    }

    private suspend fun getBinSync(binNumber: String): BIN? {
        return withContext(ioDispatcher) {
            try {
                val response = service.getBin(binNumber).execute()
                if (response.isSuccessful) {
                    response.body()
                } else {
                    Log.e("!!!error", "Response BIN ${response.isSuccessful}")
                    Log.e("!!!error", "Response body ${response.body()}")
                    null
                }

            } catch (e: IOException) {
                Log.e("!!!error", "Ошибка сети ${e.message}")
                null
            } catch (e: Exception) {
                Log.e("!!!error", "Неизвестная ошибка ${e.message}")
                e.printStackTrace()
                null
            }
        }
    }

    suspend fun getBin(binNumberArgs: String): BIN? {
        return withContext(ioDispatcher) {
            val cachedBinNumber = binDao.getBinByNumber(binNumberArgs)
            if (cachedBinNumber != null) {
                val binsWithNumbers = binDao.getAllBinsWithNumbers()
                binsWithNumbers.find { it.number.number == binNumberArgs }?.bin
            } else {
                val binFromNetwork = getBinSync(binNumberArgs)
                if (binFromNetwork != null) {
                    insertBinCache(binFromNetwork, binNumberArgs)
                }
                binFromNetwork
            }
        }
    }

    private suspend fun insertBinCache(bin: BIN?, binNumber: String) {
        if (bin != null) {
            withContext(ioDispatcher) {
                binDao.insertBinNumber(BinNumber(number = binNumber))
                binDao.insertBin(bin)
            }
        }
    }
}
