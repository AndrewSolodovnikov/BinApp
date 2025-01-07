package com.sol.binapp.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

@Parcelize
@Serializable
@Entity(
    foreignKeys = [
        ForeignKey(
            entity = BinNumber::class,
            parentColumns = ["numberId"],
            childColumns = ["binId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class BIN(
    @PrimaryKey(autoGenerate = true) @Transient val binId: Int = 0,
    val number: BinNumber,
    val scheme: String,
    val type: String,
    val brand: String,
    val country: Country,
    val bank: Bank
) : Parcelable
