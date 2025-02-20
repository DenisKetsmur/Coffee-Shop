package com.example.coffeeshop.data.room.productAndGoods.purchases

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.PrimaryKey
import androidx.room.Query
import com.example.coffeeshop.data.room.productAndGoods.Product

@Entity(
    tableName = "purchase_items",
    foreignKeys = [
        ForeignKey(
            entity = Purchase::class,
            parentColumns = ["id"],
            childColumns = ["purchaseId"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = Product::class,
            parentColumns = ["id"],
            childColumns = ["productId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index("purchaseId"), Index("productId")]
)
data class PurchaseItem(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val purchaseId: Int,
    val productId: Int,
    val quantity: Double,
    val unitPrice: Double
)

@Dao
interface PurchaseItemDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(purchaseItem: PurchaseItem)

    @Query("SELECT * FROM purchase_items WHERE id = :id")
    suspend fun getPurchaseItemById(id: Int): PurchaseItem?

    @Query("SELECT * FROM purchase_items")
    suspend fun getAllPurchaseItems(): List<PurchaseItem>

    @Delete
    suspend fun deletePurchaseItem(purchaseItem: PurchaseItem)
}