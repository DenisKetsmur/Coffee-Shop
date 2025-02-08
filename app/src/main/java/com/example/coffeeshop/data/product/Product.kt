package com.example.coffeeshop.data.product

data class Product(
    val id:Int = 1,
    val name: String = "name",
    val category: String = "category",
    val description: String? = null,
    val price: String? = null,
    val unit:String? = "unit",
    val quantity: Double = 0.0,
    val image: Int? = null,
    val type: Type = Type.RAW,
)

