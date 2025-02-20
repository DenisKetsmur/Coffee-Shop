package com.example.coffeeshop.data.roomDone.order.room

import androidx.room.Embedded
import androidx.room.Relation
import com.example.coffeeshop.data.roomDone.order.room.entities.OrderDbEntity
import com.example.coffeeshop.data.roomDone.order.room.entities.OrderItemDbEntity

data class OrderWithItems(
    @Embedded val order: OrderDbEntity,

    @Relation(
        parentColumn = "id",
        entityColumn = "order_id"
    )
    val items: List<OrderItemDbEntity>
)