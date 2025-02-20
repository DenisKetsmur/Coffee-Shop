package com.example.coffeeshop.data.roomDone.order

import androidx.lifecycle.ViewModel
import com.example.coffeeshop.data.roomDone.order.room.OrderDao
import com.example.coffeeshop.data.roomDone.order.room.OrderItemDao
import com.example.coffeeshop.data.roomDone.order.room.OrderWithItems
import com.example.coffeeshop.data.roomDone.order.room.entities.OrderDbEntity
import com.example.coffeeshop.data.roomDone.order.room.entities.OrderItemDbEntity
import kotlinx.coroutines.flow.Flow

class OrderCrudViewModel(
    private val orderDao: OrderDao,
    private val orderItemDao: OrderItemDao
) : ViewModel() {

    // Створюємо замовлення та його елементи
    suspend fun addOrderWithItems(
        clientId: Int,
        orderDate: Long,
        items: List<Triple<Int, Int, Double>> // (goodsId, quantity, unitPrice)
    ) {
        // 1. Створюємо OrderEntity
        val order = OrderDbEntity(clientId = clientId, orderDate = orderDate)
        // Вставляємо
        val newOrderId = orderDao.insertOrderReturnId(order)

        // 2. Додаємо OrderItemEntity
        items.forEach { (goodsId, quantity, unitPrice) ->
            val item = OrderItemDbEntity(
                goodsId = goodsId,
                quantity = quantity,
                unitPrice = unitPrice,
                orderId = newOrderId.toInt()
            )
            orderItemDao.insertOrderItem(item)
        }
    }

    // Отримати замовлення з елементами для конкретного клієнта
    fun getOrdersWithItemsByClient(clientId: Int): Flow<List<OrderWithItems>> {
        return orderDao.getOrdersWithItemsByClient(clientId)
    }
}

