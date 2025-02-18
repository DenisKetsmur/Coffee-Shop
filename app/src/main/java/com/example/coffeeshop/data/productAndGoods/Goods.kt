package com.example.coffeeshop.data.productAndGoods

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.coffeeshop.R
import com.example.coffeeshop.data.filled.goodsCategories
import com.example.coffeeshop.data.filled.unitList
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

val goods = listOf(
    Goods(
        name = "Еспресо",
        category = goodsCategories[0], // Кава
        unit = unitList[1],
        quantity = 50f,
    ),
    Goods(
        name = "Капучино",
        category = goodsCategories[0], // Кава
        unit = unitList[1],
        quantity = 250f
    ),
    Goods(
        name = "Зелений чай",
        category = goodsCategories[1], // Чай
        unit = unitList[1],
        quantity = 300f
    ),
    Goods(
        name = "Чорний чай",
        category = goodsCategories[1], // Чай
        unit = unitList[1],
        quantity = 300f,
    ),
    Goods(
        name = "Чізкейк",
        category = goodsCategories[2], // Десерти
        unit = unitList[5],
        quantity = 1f
    ),
    Goods(
        name = "Круасан",
        category = goodsCategories[3], // Випічка
        unit = unitList[5],
        quantity = 1f
    ),
    Goods(
        name = "Сендвіч з лососем",
        category = goodsCategories[4], // Сендвічі
        unit = unitList[5],
        quantity = 1f
    ),
    Goods(
        name = "Смузі манго",
        category = goodsCategories[5], // Смузі
        unit = unitList[1],
        quantity = 300f
    ),
    Goods(
        name = "Апельсиновий фреш",
        category = goodsCategories[6], // Фреші
        unit = unitList[1],
        quantity = 300f
    ),
    Goods(
        name = "Молочний коктейль ванільний",
        category = goodsCategories[7], // Коктейлі
        unit = unitList[1],
        quantity = 300f
    ),
)




data class Goods(
    val id:Int = generateId(),
    val name: String = "",
    val category: String = "",
    val description: String = "aleugpauiregh ;ih;g iah" +
            " skjgh;kjdfhg;kjdsfhg;kjsdfh;ksldfg s" +
            "s ldghlskdfhg'klhf;gilshflkghsdflkg",
    val quantity: Float? = 0f,
    val price: Double = 0.0,
    val unit:String = "",
    val image: Int = R.mipmap.face_photo,
){
    companion object{
        private var idCounter = 0
        private fun generateId(): Int {
            return ++idCounter
        }
    }
}


interface GoodsRepository {
    fun getGoods():StateFlow<List<Goods>>

    fun getOneGoods(id: Int):Goods?

    fun editGoods(goods: Goods)

    fun addGoods(goods: Goods)


    companion object{
        fun get(): GoodsRepository = GoodsRepositoryImpl
    }
}

object GoodsRepositoryImpl : GoodsRepository {

    private val _goods = MutableStateFlow(goods)

    override fun getGoods(): StateFlow<List<Goods>> = _goods

    override fun getOneGoods(id: Int): Goods? = _goods.value.find { it.id == id }

    override fun editGoods(goods: Goods) {
        _goods.value = _goods.value.map { if (it.id == goods.id) goods else it }
    }

    override fun addGoods(goods: Goods) {
        _goods.value += goods
    }
}

class GoodsViewModel : ViewModel() {
    private val repository: GoodsRepository = GoodsRepository.get()

    val goods: StateFlow<List<Goods>> = repository.getGoods()

    fun editGoods(goods: Goods) {
        viewModelScope.launch {
            repository.editGoods(goods)
        }
    }

    fun addGoods(goods: Goods){
        viewModelScope.launch {
            repository.addGoods(goods)
        }
    }
}

