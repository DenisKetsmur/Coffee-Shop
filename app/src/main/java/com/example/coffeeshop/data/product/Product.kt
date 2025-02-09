package com.example.coffeeshop.data.product

import com.example.coffeeshop.R

data class Product(
    val id:Int = generateId(),
    val name: String = "",
    val category: String = "",
    val description: String = "",
    val price: Double = 0.0,
    val unit:String = "",
    val quantity: Float? = 0f,
    val image: Int? = R.drawable.cat_close_mouth,
){
    companion object{
        private var idCounter = 0
        private fun generateId(): Int {
            return ++idCounter
        }
    }
}

data class RawMaterial(
    val id:Int = generateId(),
    val name: String ="",
    val category: String = "",
    val description: String = "",
    val quantity: Float = 0f,
    val price: Double = 0.0,
    val unit: String = ""
){
    companion object{
        private var idCounter = 0
        private fun generateId(): Int {
            return ++idCounter
        }
    }
}