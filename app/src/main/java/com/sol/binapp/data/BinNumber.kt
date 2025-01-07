package com.sol.binapp.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
@Entity
data class BinNumber(
    @PrimaryKey(autoGenerate = true) val numberId: Int = 0,
    val number: String = ""
) : Parcelable
