package com.example.coffeeshop.data.supplier

import androidx.compose.ui.graphics.painter.Painter
import com.example.coffeeshop.data.product.Product

data class Supplier(
    val id:Int,
    val nameCompany: String,
    val email:String,
    val phoneNumber: String,
    val products: List<Product>,
    val photo: Painter?,
){
    fun getCategoryList(supplier: Supplier): String {
        return supplier.products
            .map { it.category }
            .distinct()
            .joinToString(", ")
    }
}