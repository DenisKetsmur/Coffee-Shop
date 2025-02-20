package com.example.coffeeshop.data.roomDone.purchases.room.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.coffeeshop.data.roomDone.products.room.entities.ProductDbEntity

@Entity(
    tableName = "purchase_items",
    foreignKeys = [
        ForeignKey(
            entity = PurchaseDbEntity::class,
            parentColumns = ["id"],
            childColumns = ["purchase_id"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = ProductDbEntity::class, // Припускаємо, що така сутність існує
            parentColumns = ["id"],
            childColumns = ["product_id"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index("purchase_id"), Index("product_id")]
)
data class PurchaseItemEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "purchase_id") val purchaseId: Int,
    @ColumnInfo(name = "product_id") val productId: Int,
    val quantity: Double,
    @ColumnInfo(name = "unit_price") val unitPrice: Double
)