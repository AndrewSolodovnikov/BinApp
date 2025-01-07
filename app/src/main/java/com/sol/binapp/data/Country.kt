package com.sol.binapp.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

@Parcelize
@Serializable
@Entity
data class Country(
    @PrimaryKey(autoGenerate = true) @Transient val id: Int = 0,
    val numeric: String,
    val alpha2: String,
    @SerialName("name") val countryName: String,
    val emoji: String,
    val currency: String,
    val latitude: Int,
    val longitude: Int
) : Parcelable
