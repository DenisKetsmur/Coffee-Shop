package com.example.coffeeshop.data.productAndGoods

data class Product(
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
