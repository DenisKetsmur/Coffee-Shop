package com.example.coffeeshop.data.room.productAndGoods.order

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.PrimaryKey
import androidx.room.Query
import com.example.coffeeshop.data.goods.room.Good

@Entity(
    tableName = "order_items",
    foreignKeys = [
        ForeignKey(
            entity = Order::class,
            parentColumns = ["id"],
            childColumns = ["orderId"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = Good::class,
            parentColumns = ["id"],
            childColumns = ["goodsId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index("orderId"), Index("goodsId")]
)
data class OrderItem(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val goodsId: Int,
    val quantity: Int,
    val unitPrice: Double,
    val orderId: Int
)

@Dao
interface OrderItemDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(orderItem: OrderItem)

    @Query("SELECT * FROM order_items WHERE id = :id")
    suspend fun getOrderItemById(id: Int): OrderItem?

    @Query("SELECT * FROM order_items WHERE orderId = :orderId")
    suspend fun getItemsForOrder(orderId: Int): List<OrderItem>

    @Delete
    suspend fun deleteOrderItem(orderItem: OrderItem)
}