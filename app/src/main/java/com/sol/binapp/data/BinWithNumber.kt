package com.sol.binapp.data

import androidx.room.Embedded
import androidx.room.Relation

data class BinWithNumber(
    @Embedded val bin: BIN,
    @Relation(
        parentColumn = "binId",
        entityColumn = "numberId"
    )
    val number: BinNumber
)
