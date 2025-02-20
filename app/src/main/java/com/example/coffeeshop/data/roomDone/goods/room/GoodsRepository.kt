package com.example.coffeeshop.data.roomDone.goods.room

import com.example.coffeeshop.data.roomDone.goods.entities.Good
import com.example.coffeeshop.data.roomDone.goods.room.entities.GoodUpdateTuple
import kotlinx.coroutines.flow.Flow

interface GoodsRepository {

    fun getAllGoods(): Flow<List<Good>>

    suspend fun getGoodById(id: Int): Good?

    suspend fun addGood(good: Good)

    suspend fun updateGood(goodUpdateTuple: GoodUpdateTuple)

    suspend fun deleteGood(good: Good?)
}
