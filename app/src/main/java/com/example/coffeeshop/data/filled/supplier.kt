package com.example.coffeeshop.data.filled

import com.example.coffeeshop.data.product.RawMaterial
import com.example.coffeeshop.data.supplier.Order
import com.example.coffeeshop.data.supplier.Supplier

val sampleSupplier = Supplier(
    nameCompany = "MilkProd",
    phoneNumber = "+380 98 123 456",
    email = "ivan@milkprod.com",
    orders = listOf(
        Order(
            "23.07.2024", listOf(
                RawMaterial(name = "Сир", quantity = 10f),
                RawMaterial(name = "Молоко", quantity = 10f),
                RawMaterial(name = "Вершки", quantity = 10f)
            ), 260.0
        ),
        Order(
            "23.07.2024", listOf(
                RawMaterial(name = "Сир", quantity = 10f),
                RawMaterial(name = "Молоко", quantity = 10f),
                RawMaterial(name = "Вершки", quantity = 10f)
            ), 260.0
        ),
        Order(
            "23.07.2024", listOf(
                RawMaterial(name = "Сир", quantity = 10f),
                RawMaterial(name = "Молоко", quantity = 10f),
                RawMaterial(name = "Вершки", quantity = 10f)
            ), 260.0
        ),
    ),
)