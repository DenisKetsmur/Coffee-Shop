package com.example.coffeeshop.data.product

import com.example.coffeeshop.R

data class Product(
    val id:Int = generateId(),
    val name: String = "",
    val category: String = "",
    val description: String = "aleugpauiregh ;ih;g iah" +
            " skjgh;kjdfhg;kjdsfhg;kjsdfh;ksldfg s" +
            "s ldghlskdfhg'klhf;gilshflkghsdflkg",
    val quantity: Float? = 0f,
    val price: Double = 0.0,
    val unit:String = "",
    val image: Int = R.mipmap.face_photo,
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