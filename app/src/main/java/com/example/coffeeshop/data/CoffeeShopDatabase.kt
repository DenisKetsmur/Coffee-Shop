package com.example.coffeeshop.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.coffeeshop.data.roomDone.clients.room.ClientDao
import com.example.coffeeshop.data.roomDone.clients.room.entities.ClientDbEntity
import com.example.coffeeshop.data.roomDone.goods.room.GoodDao
import com.example.coffeeshop.data.roomDone.goods.room.entities.GoodBdEntity

@Database(
    version = 1,
    entities = [
        ClientDbEntity::class,
        GoodBdEntity::class,
    ]
)
abstract class CoffeeShopDatabase : RoomDatabase() {

    abstract fun getAccountsDao(): ClientDao

    abstract fun getGoodsDao(): GoodDao
}