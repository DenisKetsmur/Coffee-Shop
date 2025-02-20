package com.example.coffeeshop.data.roomDone.products.entities

data class Product(
    val id:Long,
    val name: String,
    val categoryId: Int?,
    val description: String,
    val quantity: Double,
    val supplierId: Int,
    val price: Double,
    val unitId: Int?,
)

