package com.sol.binapp.data

import androidx.room.TypeConverter
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class Converters {
    @TypeConverter
    fun fromBinNumber(value: BinNumber): String {
        return Json.encodeToString(value)
    }

    @TypeConverter
    fun toBinNumber(value: String): BinNumber {
        return Json.decodeFromString(value)
    }

    @TypeConverter
    fun fromBank(value: Bank): String {
        return Json.encodeToString(value)
    }

    @TypeConverter
    fun toBank(value: String): Bank {
        return Json.decodeFromString(value)
    }

    @TypeConverter
    fun fromCountry(value: Country): String {
        return Json.encodeToString(value)
    }

    @TypeConverter
    fun toCountry(value: String): Country {
        return Json.decodeFromString(value)
    }
}
