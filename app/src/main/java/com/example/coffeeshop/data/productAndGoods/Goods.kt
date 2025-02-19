package com.example.coffeeshop.data.productAndGoods

import com.example.coffeeshop.R
import com.example.coffeeshop.data.HasId
import com.example.coffeeshop.data.ItemViewModel
import com.example.coffeeshop.data.RepositoryImpl
import com.example.coffeeshop.data.filled.goodsCategories
import com.example.coffeeshop.data.filled.unitList

val goodsList = listOf(
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
    override val id:Int = generateId(),
    val name: String = "",
    val category: String = "",
    val description: String = "aleugpauiregh ;ih;g iah" +
            " skjgh;kjdfhg;kjdsfhg;kjsdfh;ksldfg s" +
            "s ldghlskdfhg'klhf;gilshflkghsdflkg",
    val quantity: Float? = 0f,
    val price: Double = 0.0,
    val unit:String = "",
    val image: Int = R.mipmap.face_photo,
) : HasId {
    companion object{
        private var idCounter = 0
        private fun generateId(): Int {
            return ++idCounter
        }
    }
}

class GoodsViewModel : ItemViewModel<Goods>(
    RepositoryImpl(goodsList)
)

