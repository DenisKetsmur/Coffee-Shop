package com.example.coffeeshop.data.roomDone.purchases

import androidx.lifecycle.ViewModel
import com.example.coffeeshop.data.roomDone.purchases.room.entities.PurchaseDbEntity
import com.example.coffeeshop.data.roomDone.purchases.room.PurchaseDao
import com.example.coffeeshop.data.roomDone.purchases.room.PurchaseItemDao
import com.example.coffeeshop.data.roomDone.purchases.room.PurchaseWithItems
import com.example.coffeeshop.data.roomDone.purchases.room.entities.PurchaseItemEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class PurchaseCrudViewModel(
    private val purchaseDao: PurchaseDao,
    private val itemDao: PurchaseItemDao
) : ViewModel() {

    suspend fun addPurchaseWithItems(
        supplierId: Int,
        purchaseDate: Long,
        items: List<Triple<Int, Double, Double>> // (productId, quantity)
    ) {
        // 1. Створюємо PurchaseEntity
        val purchase = PurchaseDbEntity(supplierId = supplierId, purchaseDate = purchaseDate)
        // Вставляємо у базу
        val newPurchaseId = withContext(Dispatchers.IO) {
            purchaseDao.insertPurchaseReturnId(purchase)
        }


        // 2. Додаємо PurchaseItemEntity
        // (productId, quantity)
        items.forEach { (productId, quantity, unitPrice) ->
            val item = PurchaseItemEntity(
                purchaseId = newPurchaseId.toInt(),
                productId = productId,
                quantity = quantity,
                unitPrice = unitPrice
            )
            itemDao.insertPurchaseItem(item)
        }
    }

    // 2. Отримати всі покупки (з елементами) для конкретного постачальника
    fun getPurchasesWithItemsBySupplier(supplierId: Int): Flow<List<PurchaseWithItems>> {
        return purchaseDao.getPurchasesWithItemsBySupplier(supplierId)
    }

}

