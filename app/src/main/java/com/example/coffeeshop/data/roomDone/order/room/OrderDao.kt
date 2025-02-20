package com.example.coffeeshop.data.roomDone.order.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Embedded
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Relation
import androidx.room.Transaction
import androidx.room.Update
import com.example.coffeeshop.data.roomDone.order.room.entities.OrderDbEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface OrderDao {

    @Query("SELECT * FROM orders")
    fun getAllOrders(): Flow<List<OrderDbEntity>>

    // Для реляційної моделі
    @Transaction
    @Query("SELECT * FROM orders WHERE client_id = :clientId")
    fun getOrdersWithItemsByClient(clientId: Int): Flow<List<OrderWithItems>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrderReturnId(order: OrderDbEntity): Long

    @Update
    suspend fun updateOrder(order: OrderDbEntity)

    @Delete
    suspend fun deleteOrder(order: OrderDbEntity)
}