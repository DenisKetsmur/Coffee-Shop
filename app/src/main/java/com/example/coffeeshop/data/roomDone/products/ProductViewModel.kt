package com.example.coffeeshop.data.roomDone.products

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coffeeshop.data.roomDone.goods.entities.Good
import com.example.coffeeshop.data.roomDone.goods.room.entities.GoodUpdateTuple
import com.example.coffeeshop.data.roomDone.products.entities.Product
import com.example.coffeeshop.data.roomDone.products.room.ProductRepository
import com.example.coffeeshop.data.roomDone.products.room.entities.ProductUpdateTuple
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class ProductViewModel(
    private val productRepository: RoomProductRepository,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : ViewModel() {

    private val _product = MutableStateFlow<List<Product>>(emptyList())
    val goods: StateFlow<List<Product>> = _product.asStateFlow()

    private val _errorState = MutableStateFlow<String?>(null)
    val errorState: StateFlow<String?> = _errorState.asStateFlow()

    init {
        fetchProduct()
    }

    private fun fetchProduct() {
        viewModelScope.launch(ioDispatcher) {
            try {
                productRepository.getAllProduct()
                    .collect { productList ->
                        _product.value = productList
                    }
            } catch (e: Exception) {
                _errorState.value = "Помилка завантаження продуктів: ${e.localizedMessage}"
            }
        }
    }

    fun addProduct(product: Product) {
        viewModelScope.launch(ioDispatcher) {
            try {
                if (product.price < 0) throw IllegalArgumentException("Ціна не може бути від’ємною")
                if (product.quantity < 0) throw IllegalArgumentException("Кількість не може бути від’ємною")

                productRepository.addProduct(product)
                fetchProduct()
            } catch (e: Exception) {
                _errorState.value = "Помилка додавання товару: ${e.localizedMessage}"
            }
        }
    }

    fun updateGood(product: Product) {
        viewModelScope.launch(ioDispatcher) {
            try {
                if (product.price < 0) throw IllegalArgumentException("Ціна не може бути від’ємною")

                val updatedProduct = ProductUpdateTuple(
                    id = product.id,
                    name = product.name,
                    categoryId = product.categoryId,
                    description = product.description,
                    quantity = product.quantity,
                    supplierId = product.supplierId,
                    price = product.price,
                    unitId = product.unitId
                )

                productRepository.updateProduct(updatedProduct)
                fetchProduct()
            } catch (e: Exception) {
                _errorState.value = "Помилка оновлення товару: ${e.localizedMessage}"
            }
        }
    }

    fun deleteProduct(product: Product) {
        viewModelScope.launch(ioDispatcher) {
            try {
                productRepository.deleteProduct(product)
                fetchProduct()
            } catch (e: Exception) {
                _errorState.value = "Помилка видалення товару: ${e.localizedMessage}"
            }
        }
    }

    fun clearError() {
        _errorState.value = null
    }
}
