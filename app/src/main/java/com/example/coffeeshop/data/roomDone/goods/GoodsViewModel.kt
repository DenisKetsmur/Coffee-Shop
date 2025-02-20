package com.example.coffeeshop.data.roomDone.goods

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coffeeshop.data.roomDone.goods.entities.Good
import com.example.coffeeshop.data.roomDone.goods.room.GoodsRepository
import com.example.coffeeshop.data.roomDone.goods.room.entities.GoodUpdateTuple
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class GoodsViewModel(
    private val goodsRepository: GoodsRepository,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : ViewModel() {

    private val _goods = MutableStateFlow<List<Good>>(emptyList())
    val goods: StateFlow<List<Good>> = _goods.asStateFlow()

    private val _errorState = MutableStateFlow<String?>(null)
    val errorState: StateFlow<String?> = _errorState.asStateFlow()

    init {
        fetchGoods()
    }

    private fun fetchGoods() {
        viewModelScope.launch(ioDispatcher) {
            try {
                goodsRepository.getAllGoods()
                    .collect { goodsList ->
                        _goods.value = goodsList
                    }
            } catch (e: Exception) {
                _errorState.value = "Помилка завантаження товарів: ${e.localizedMessage}"
            }
        }
    }

    fun addGood(good: Good) {
        viewModelScope.launch(ioDispatcher) {
            try {
                if (good.price < 0) throw IllegalArgumentException("Ціна не може бути від’ємною")
                if (good.quantity < 0) throw IllegalArgumentException("Кількість не може бути від’ємною")

                goodsRepository.addGood(good)
                fetchGoods()
            } catch (e: Exception) {
                _errorState.value = "Помилка додавання товару: ${e.localizedMessage}"
            }
        }
    }

    fun updateGood(good: Good) {
        viewModelScope.launch(ioDispatcher) {
            try {
                if (good.price < 0) throw IllegalArgumentException("Ціна не може бути від’ємною")

                val updatedGood = GoodUpdateTuple(
                    id = good.id,
                    name = good.name,
                    categoryId = good.categoryId,
                    description = good.description,
                    quantity = good.quantity,
                    price = good.price,
                    unitId = good.unitId
                )

                goodsRepository.updateGood(updatedGood)
                fetchGoods()
            } catch (e: Exception) {
                _errorState.value = "Помилка оновлення товару: ${e.localizedMessage}"
            }
        }
    }

    fun deleteGood(good: Good) {
        viewModelScope.launch(ioDispatcher) {
            try {
                goodsRepository.deleteGood(good)
                fetchGoods()
            } catch (e: Exception) {
                _errorState.value = "Помилка видалення товару: ${e.localizedMessage}"
            }
        }
    }

    fun clearError() {
        _errorState.value = null
    }
}
