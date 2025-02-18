package com.example.coffeeshop.data.filled

import com.example.coffeeshop.data.productAndGoods.Product

val productCategories = listOf(
    "Кавові зерна",
    "Молоко",
    "Чайне листя",
    "Цукор та підсолоджувачі",
    "Борошно",
    "Яйця",
    "Фрукти",
    "Шоколад",
    "Горіхи",
    "Вершки"
)

val unitList = listOf("кг", "мл", "л", "г", "десятки", "шт")

var productsList = listOf(
    Product(
        name = "Кава арабіка",
        category = productCategories[0], // Кавові зерна
        unit = unitList[1], // мл
        quantity = 10f
    ),
    Product(
        name = "Кава робуста",
        category = productCategories[0], // Кавові зерна
        unit = unitList[0], // кг
        quantity = 8f
    ),
    Product(
        name = "Молоко коров’яче",
        category = productCategories[1], // Молоко
        unit = unitList[2], // л
        quantity = 25f
    ),
    Product(
        name = "Зелений чай листовий",
        category = productCategories[2], // Чайне листя
        unit = unitList[0], // кг
        quantity = 2f
    ),
    Product(
        name = "Чорний чай листовий",
        category = productCategories[2], // Чайне листя
        unit = unitList[0], // кг
        quantity = 3f
    ),
    Product(
        name = "Цукор тростниковий",
        category = productCategories[3], // Цукор та підсолоджувачі
        unit = unitList[0], // кг
        quantity = 7f
    ),
    Product(
        name = "Борошно пшеничне",
        category = productCategories[4], // Борошно
        unit = unitList[0], // кг
        quantity = 15f
    ),
    Product(
        name = "Яйця курячі",
        category = productCategories[5], // Яйця
        unit = unitList[4], // десятки
        quantity = 3f
    ),
    Product(
        name = "Яблука",
        category = productCategories[6], // Фрукти
        unit = unitList[0], // кг
        quantity = 20f
    ),
    Product(
        name = "Темний шоколад",
        category = productCategories[7], // Шоколад
        unit = unitList[0], // кг
        quantity = 5f
    )
)