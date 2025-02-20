package com.example.coffeeshop.data.roomDone.goods

import com.example.coffeeshop.data.roomDone.goods.entities.Good
import com.example.coffeeshop.data.roomDone.goods.room.GoodDao
import com.example.coffeeshop.data.roomDone.goods.room.GoodsRepository
import com.example.coffeeshop.data.roomDone.goods.room.entities.GoodBdEntity
import com.example.coffeeshop.data.roomDone.goods.room.entities.GoodUpdateTuple
import com.example.coffeeshop.wrapSQLiteException
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class RoomGoodsRepository(
    private val goodsDao: GoodDao,
    private val ioDispatcher: CoroutineDispatcher
): GoodsRepository {
    override fun getAllGoods(): Flow<List<Good>> =
        goodsDao.getAllGoods().map { list -> list.map { it.toGood() }
        }

    override suspend fun getGoodById(id: Int): Good? {
        return goodsDao.getGoodById(id)?.toGood()
    }

    override suspend fun addGood(good: Good) = wrapSQLiteException(ioDispatcher) {
        if (good.price < 0) throw IllegalArgumentException("Ціна не може бути від’ємною")
        if (good.quantity < 0) throw IllegalArgumentException("Кількість не може бути від’ємною")

        goodsDao.insert(GoodBdEntity.fromGood(good))
    }
    override suspend fun updateGood(goodUpdateTuple: GoodUpdateTuple) = wrapSQLiteException(ioDispatcher) {
        if (goodUpdateTuple.price < 0) throw IllegalArgumentException("Ціна не може бути від’ємною")
        goodsDao.updateGood(goodUpdateTuple)
    }

    override suspend fun deleteGood(good: Good?) = wrapSQLiteException(ioDispatcher) {
        if (good == null) throw IllegalArgumentException("Неможливо видалити: об'єкт good є null")

        goodsDao.deleteGood(GoodBdEntity.fromGood(good))
    }
}
