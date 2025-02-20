package com.example.coffeeshop.data.roomDone.goods.room.entities

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey

data class GoodUpdateTuple(
    val id: Long,
    val name: String,
    @ColumnInfo(name = "category_id") val categoryId: Int?,
    @ColumnInfo(name = "unit_id") val unitId: Int?,
    val quantity: Double,
    val price: Double,
    val description: String,
)
