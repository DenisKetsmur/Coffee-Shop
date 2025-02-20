package com.example.coffeeshop.data.roomDone.purchases.room

import com.example.coffeeshop.data.roomDone.purchases.room.entities.PurchaseItemEntity

@Dao
interface PurchaseItemDao {

    // Отримати всі позиції покупок
    @Query("SELECT * FROM purchase_items")
    fun getAllPurchaseItems(): Flow<List<PurchaseItemEntity>>

    // Отримати позиції для конкретної покупки
    @Query("SELECT * FROM purchase_items WHERE purchase_id = :purchaseId")
    fun getItemsForPurchase(purchaseId: Int): Flow<List<PurchaseItemEntity>>

    // Додати/оновити позицію
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPurchaseItem(item: PurchaseItemEntity)

    // Оновити
    @Update
    suspend fun updatePurchaseItem(item: PurchaseItemEntity)

    // Видалити
    @Delete
    suspend fun deletePurchaseItem(item: PurchaseItemEntity)
}