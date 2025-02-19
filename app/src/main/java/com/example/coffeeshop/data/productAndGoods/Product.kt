package com.example.coffeeshop.data.productAndGoods

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coffeeshop.data.HasId
import com.example.coffeeshop.data.ItemViewModel
import com.example.coffeeshop.data.RepositoryImpl
import com.example.coffeeshop.data.filled.productCategories
import com.example.coffeeshop.data.filled.unitList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

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

data class Product(
    override val id:Int = generateId(),
    val name: String ="",
    val category: String = "",
    val description: String = "",
    val quantity: Float = 0f,
    val price: Double = 0.0,
    val unit: String = ""
):HasId{
    companion object{
        private var idCounter = 0
        private fun generateId(): Int {
            return ++idCounter
        }
    }
}



class ProductViewModel : ItemViewModel<Product>(
    RepositoryImpl(productsList)
)

