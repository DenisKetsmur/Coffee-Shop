package com.example.coffeeshop.data.filled

import com.example.coffeeshop.data.product.Product
import com.example.coffeeshop.data.product.RawMaterial

val rawMaterialCategories = listOf(
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

val finishedProductCategories = listOf(
    "Кава",
    "Чай",
    "Десерти",
    "Випічка",
    "Сендвічі",
    "Смузі",
    "Фреші",
    "Коктейлі"
)

val unitList = listOf("кг", "мл", "л", "г")

val products = listOf(
    Product(
        name = "Еспресо",
        category = finishedProductCategories[0], // Кава
        unit = "мл",
        quantity = 50f,
    ),
    Product(
        name = "Капучино",
        category = finishedProductCategories[0], // Кава
        unit = "мл",
        quantity = 250f
    ),
    Product(
        name = "Зелений чай",
        category = finishedProductCategories[1], // Чай
        unit = "мл",
        quantity = 300f
    ),
    Product(
        name = "Чорний чай",
        category = finishedProductCategories[1], // Чай
        unit = "мл",
        quantity = 300f
    ),
    Product(
        name = "Чізкейк",
        category = finishedProductCategories[2], // Десерти
        unit = "шт",
        quantity = 1f
    ),
    Product(
        name = "Круасан",
        category = finishedProductCategories[3], // Випічка
        unit = "шт",
        quantity = 1f
    ),
    Product(
        name = "Сендвіч з лососем",
        category = finishedProductCategories[4], // Сендвічі
        unit = "шт",
        quantity = 1f
    ),
    Product(
        name = "Смузі манго",
        category = finishedProductCategories[5], // Смузі
        unit = "мл",
        quantity = 300f
    ),
    Product(
        name = "Апельсиновий фреш",
        category = finishedProductCategories[6], // Фреші
        unit = "мл",
        quantity = 300f
    ),
    Product(
        name = "Молочний коктейль ванільний",
        category = finishedProductCategories[7], // Коктейлі
        unit = "мл",
        quantity = 300f
    ),
)

val rawMaterial = listOf(
    RawMaterial(
        name = "Кава арабіка",
        category = rawMaterialCategories[0], // Кавові зерна
        unit = "кг",
        quantity = 10f
    ),
    RawMaterial(
        name = "Кава робуста",
        category = rawMaterialCategories[0], // Кавові зерна
        unit = "кг",
        quantity = 8f
    ),
    RawMaterial(
        name = "Молоко коров’яче",
        category = rawMaterialCategories[1], // Молоко
        unit = "л",
        quantity = 25f
    ),
    RawMaterial(
        name = "Зелений чай листовий",
        category = rawMaterialCategories[2], // Чайне листя
        unit = "кг",
        quantity = 2f
    ),
    RawMaterial(
        name = "Чорний чай листовий",
        category = rawMaterialCategories[2], // Чайне листя
        unit = "кг",
        quantity = 3f
    ),
    RawMaterial(
        name = "Цукор тростниковий",
        category = rawMaterialCategories[3], // Цукор та підсолоджувачі
        unit = "кг",
        quantity = 7f
    ),
    RawMaterial(
        name = "Борошно пшеничне",
        category = rawMaterialCategories[4], // Борошно
        unit = "кг",
        quantity = 15f
    ),
    RawMaterial(
        name = "Яйця курячі",
        category = rawMaterialCategories[5], // Яйця
        unit = "десятки",
        quantity = 3f
    ),
    RawMaterial(
        name = "Яблука",
        category = rawMaterialCategories[6], // Фрукти
        unit = "кг",
        quantity = 20f
    ),
    RawMaterial(
        name = "Темний шоколад",
        category = rawMaterialCategories[7], // Шоколад
        unit = "кг",
        quantity = 5f
    )
)
