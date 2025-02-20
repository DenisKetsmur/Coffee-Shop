package com.example.coffeeshop.data.roomDone.order.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.coffeeshop.data.roomDone.order.room.entities.OrderItemDbEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface OrderItemDao {

    @Query("SELECT * FROM order_items")
    fun getAllOrderItems(): Flow<List<OrderItemDbEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrderItem(item: OrderItemDbEntity)

    @Update
    suspend fun updateOrderItem(item: OrderItemDbEntity)

    @Delete
    suspend fun deleteOrderItem(item: OrderItemDbEntity)
}