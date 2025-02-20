package com.example.coffeeshop.data.productAndGoods

import com.example.coffeeshop.data.HasId
import com.example.coffeeshop.data.ItemViewModel
import com.example.coffeeshop.data.RepositoryImpl
import com.example.coffeeshop.data.filled.unitList

var productsList = listOf(
    Product(
        name = "Кава арабіка",
        categoryId = 0, // Кавові зерна
        unitId = 1, // мл
        quantity = 10.0,
        description = "sdfsdfs",
        price = 2312.34,
        supplierId = 1
    ),
    Product(
        name = "Кава робуста",
        categoryId = 0, // Кавові зерна
        unitId = 0, // кг
        quantity = 8.0,
        description = "sdfsdfs",
        price = 2312.34,
        supplierId = 3
    ),
    Product(
        name = "Молоко коров’яче",
        categoryId = 1, // Молоко
        unitId = 0, // л
        quantity = 25.0,
        description = "sdfsdfs",
        price = 2312.34,
        supplierId = 2
    ),
    Product(
        name = "Зелений чай листовий",
        categoryId = 2, // Чайне листя
        unitId = 0, // кг
        quantity = 2.0,
        description = "sdfsdfs",
        price = 2312.34,
        supplierId = 1
    ),
    Product(
        name = "Чорний чай листовий",
        categoryId = 2, // Чайне листя
        unitId = 0, // кг
        quantity = 3.0,
        description = "sdfsdfs",
        price = 2312.34,
        supplierId = 1
    ),
    Product(
        name = "Цукор тростниковий",
        categoryId = 3, // Цукор та підсолоджувачі
        unitId = 0, // кг
        quantity = 7.0,
        description = "sdfsdfs",
        price = 2312.34,
        supplierId = 1
    ),
    Product(
        name = "Борошно пшеничне",
        categoryId = 4, // Борошно
        unitId = 0, // кг
        quantity = 15.0,
        description = "sdfsdfs",
        price = 2312.34,
        supplierId = 1
    ),
    Product(
        name = "Яйця курячі",
        categoryId = 5, // Яйця
        unitId = 0, // десятки
        quantity = 3.0,
        description = "sdfsdfs",
        price = 2312.34,
        supplierId = 2
    ),
    Product(
        name = "Яблука",
        categoryId = 6, // Фрукти
        unitId = 0, // кг
        quantity = 20.0,
        description = "sdfsdfs",
        price = 2312.34,
        supplierId = 1
    ),
    Product(
        name = "Темний шоколад",
        categoryId = 1, // Шоколад
        unitId = 0, // кг
        quantity = 5.0,
        description = "sdfsdfs",
        price = 2312.34,
        supplierId = 1
    )
)

data class Product(
    override val id:Long = generateId(),
    val name: String,
    val categoryId: Int?,
    val description: String,
    val quantity: Double,
    val supplierId: Int?,
    val price: Double,
    val unitId: Int?,
):HasId{
    companion object{
        private var idCounter = 0L
        private fun generateId(): Long {
            return ++idCounter
        }
    }
}

class ProductViewModel : ItemViewModel<Product>(
    RepositoryImpl(productsList)
)

