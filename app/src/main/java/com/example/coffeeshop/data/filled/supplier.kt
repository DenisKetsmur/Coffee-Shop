package com.example.coffeeshop.data.filled

import com.example.coffeeshop.data.productAndGoods.Product
import com.example.coffeeshop.data.productAndGoods.productsList
import com.example.coffeeshop.data.supplier.Order
import com.example.coffeeshop.data.supplier.Supplier

val sampleSupplier = Supplier(
    nameCompany = "MilkProd",
    phoneNumber = "+380 98 123 456",
    email = "ivan@milkprod.com",

    products = productsList,
    orders = listOf(
        Order(
            getRandomDate(), listOf(
                Product(name = "Сир", quantity = 10f),
                Product(name = "Молоко", quantity = 10f),
                Product(name = "Вершки", quantity = 10f)
            ), 260.0
        ),
        Order(
            getRandomDate(), listOf(
                Product(name = "Сир", quantity = 10f),
                Product(name = "Молоко", quantity = 10f),
                Product(name = "Вершки", quantity = 10f)
            ), 260.0
        ),
        Order(
            getRandomDate(), listOf(
                Product(name = "Сир", quantity = 10f),
                Product(name = "Молоко", quantity = 10f),
                Product(name = "Вершки", quantity = 10f)
            ), 260.0
        ),
    ),
)