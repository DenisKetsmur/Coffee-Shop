package com.example.coffeeshop.data.roomDone.order.room.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.coffeeshop.data.roomDone.goods.room.entities.GoodBdEntity
import com.example.coffeeshop.data.roomDone.products.room.entities.ProductDbEntity

@Entity(
    tableName = "order_items",
    foreignKeys = [
        ForeignKey(
            entity = OrderDbEntity::class,
            parentColumns = ["id"],
            childColumns = ["order_id"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = GoodBdEntity::class, // Припускаємо, що така сутність існує
            parentColumns = ["id"],
            childColumns = ["goods_id"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index("order_id"), Index("goods_id")]
)
data class OrderItemDbEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    @ColumnInfo(name = "goods_id") val goodsId: Int,
    val quantity: Int,
    @ColumnInfo(name = "unit_price") val unitPrice: Double,
    @ColumnInfo(name = "order_id") val orderId: Int
)