package com.example.coffeeshop.data.roomDone.goods.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.coffeeshop.data.roomDone.goods.room.entities.GoodBdEntity
import com.example.coffeeshop.data.roomDone.goods.room.entities.GoodUpdateTuple
import kotlinx.coroutines.flow.Flow

@Dao
interface GoodDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(good: GoodBdEntity)

    @Query("SELECT * FROM goods WHERE id = :id")
    suspend fun getGoodById(id: Int): GoodBdEntity?

    @Query("SELECT * FROM goods")
    fun getAllGoods(): Flow<List<GoodBdEntity>>

    @Delete
    suspend fun deleteGood(good: GoodBdEntity)

    @Update(entity = GoodBdEntity::class)
    suspend fun updateGood(goodUpdateTuple: GoodUpdateTuple)

}
