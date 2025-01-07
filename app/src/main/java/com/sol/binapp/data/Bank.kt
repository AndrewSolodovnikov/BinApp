package com.sol.binapp.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

@Parcelize
@Serializable
@Entity
data class Bank(
    @PrimaryKey(autoGenerate = true) @Transient val id: Int = 0,
    val name: String
) : Parcelable
