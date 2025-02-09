package com.example.coffeeshop.data.supplier

import com.example.coffeeshop.R
import com.example.coffeeshop.data.product.RawMaterial

data class Supplier(
    val id: Int = generateId(),
    val nameCompany: String,
    val email: String,
    val phoneNumber: String,
    val orders: List<Order>,
    val imageUri: Int = R.mipmap.face_photo
) {
    companion object {
        private var idCounter = 0
        private fun generateId(): Int {
            return ++idCounter
        }
    }

    fun getSupplierRawMaterials(): String {
        return orders
            .flatMap { it.items }
            .map { it.name }
            .joinToString(", ")
    }
}

data class Order(
    val date: String,
    val items: List<RawMaterial>,
    val total: Double
)