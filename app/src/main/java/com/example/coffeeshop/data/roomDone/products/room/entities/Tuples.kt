package com.example.coffeeshop.data.roomDone.products.room.entities

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey

data class ProductUpdateTuple(
    val id: Long,
    val name: String,
    @ColumnInfo(name = "category_id") val categoryId: Int?,
    val quantity: Double,
    @ColumnInfo(name = "supplier_id")val supplierId: Int,
    val price: Double,
    @ColumnInfo(name = "unit_id")val unitId: Int?,
    val description: String = ""
)
