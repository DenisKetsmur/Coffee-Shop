package com.example.coffeeshop.data.roomDone.goods.entities

import com.example.coffeeshop.R
import com.example.coffeeshop.data.HasId
import com.example.coffeeshop.data.ItemViewModel
import com.example.coffeeshop.data.RepositoryImpl
import com.example.coffeeshop.data.filled.goodsCategories
import com.example.coffeeshop.data.filled.unitList
import com.example.coffeeshop.data.roomDone.goods.room.entities.GoodBdEntity


data class Good(
    val id: Long,
    val name: String,
    val categoryId: Int?,
    val description: String = "",
    val quantity: Double = 0.0,
    val price: Double = 0.0,
    val unitId:Int?,
    val image: Int = R.mipmap.face_photo,
)

