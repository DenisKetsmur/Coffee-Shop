package com.example.coffeeshop.data.supplier

import com.example.coffeeshop.R
import com.example.coffeeshop.data.HasId
import com.example.coffeeshop.data.ItemViewModel
import com.example.coffeeshop.data.RepositoryImpl
import com.example.coffeeshop.data.filled.getRandomDate
import com.example.coffeeshop.data.productAndGoods.Product
import com.example.coffeeshop.data.productAndGoods.productsList

val ordersList = listOf(
    Order(
        date = getRandomDate(),
        items = productsList,
        total = 2442.0,
    ),
    Order(
        date = getRandomDate(),
        items = productsList,
        total = 2342.0,
    ),
    Order(
        date = getRandomDate(),
        items = productsList,
        total = 122343.0,
    ),
    Order(
        date = getRandomDate(),
        items = productsList,
        total = 2342.0,
    ),
    Order(
        date = getRandomDate(),
        items = productsList,
        total = 234.0,
    )
)

data class Supplier(
    override val id: Int = generateId(),
    val nameCompany: String = "",
    val email: String = "",
    val phoneNumber: String = "",
    val products:List<Product> = listOf(),
    val orders: List<Order<Product>> = listOf(),
    val imageUri: Int = R.mipmap.face_photo
) : HasId{
    companion object {
        private var idCounter = 0
        private fun generateId(): Int {
            return ++idCounter
        }
    }

    fun getSupplierRawMaterials(): String {
        return orders
            .flatMap { it.items }.joinToString(", ") { it.name }
    }
}

data class Order<T>(
    val date: Long,
    val items: List<T>,
    val total: Double
)



val suppliers = listOf(
    Supplier(nameCompany = "Кава Експрес", email = "coffee@express.com", phoneNumber = "+380671234567", products = productsList, orders = ordersList),
    Supplier(nameCompany = "Зерно Плюс", email = "zerno@plus.com", phoneNumber = "+380672345678", products = productsList, orders = ordersList),
    Supplier(nameCompany = "Арома Кава", email = "aroma@coffee.com", phoneNumber = "+380673456789", products = productsList, orders = ordersList),
    Supplier(nameCompany = "Чорна Перлина", email = "blackpearl@coffee.com", phoneNumber = "+380674567890", products = productsList, orders = ordersList),
    Supplier(nameCompany = "Свіже Зерно", email = "fresh@zerno.com", phoneNumber = "+380675678901", products = productsList, orders = ordersList),
    Supplier(nameCompany = "Кавова Фабрика", email = "factory@coffee.com", phoneNumber = "+380676789012", products = productsList, orders = ordersList),
    Supplier(nameCompany = "Кавові Традиції", email = "tradition@coffee.com", phoneNumber = "+380677890123", products = productsList, orders = ordersList),
    Supplier(nameCompany = "Баріста Груп", email = "barista@group.com", phoneNumber = "+380678901234", products = productsList, orders = ordersList),
    Supplier(nameCompany = "Еко Кава", email = "eco@coffee.com", phoneNumber = "+380679012345", products = productsList, orders = ordersList),
    Supplier(nameCompany = "Золотий Зернятко", email = "gold@zernyatko.com", phoneNumber = "+380671112233", products = productsList, orders = ordersList)
)

class SupplierViewModel : ItemViewModel<Supplier>(
    RepositoryImpl(suppliers)
)


