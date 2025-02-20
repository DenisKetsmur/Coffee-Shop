package com.example.coffeeshop.data.roomDone.purchases.room

import androidx.room.Dao
import androidx.room.Embedded
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Relation
import androidx.room.Transaction
import com.example.coffeeshop.data.roomDone.purchases.room.entities.PurchaseDbEntity
import com.example.coffeeshop.data.roomDone.purchases.room.entities.PurchaseItemEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PurchaseDao {

    // Отримати всі покупки
    @Query("SELECT * FROM purchases")
    fun getAllPurchases(): Flow<List<PurchaseDbEntity>>

    // Знайти покупку за ID
    @Query("SELECT * FROM purchases WHERE id = :purchaseId")
    suspend fun getPurchaseById(purchaseId: Int): PurchaseDbEntity?

    // Додати/оновити покупку
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPurchase(purchase: PurchaseDbEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPurchaseReturnId(purchase: PurchaseDbEntity): Long

    @Transaction
    @Query("SELECT * FROM purchases WHERE supplier_id = :supplierId")
    fun getPurchasesWithItemsBySupplier(supplierId: Int): Flow<List<PurchaseWithItems>>

    // Отримати одну покупку з позиціями за ID
    @Transaction
    @Query("SELECT * FROM purchases WHERE id = :purchaseId")
    suspend fun getPurchaseWithItemsById(purchaseId: Int): PurchaseWithItems?
}

data class PurchaseWithItems(
    @Embedded val purchase: PurchaseDbEntity,

    @Relation(
        parentColumn = "id",
        entityColumn = "purchase_id"
    )
    val items: List<PurchaseItemEntity>
)