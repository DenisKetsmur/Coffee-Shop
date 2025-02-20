package com.example.coffeeshop.data.roomDone.purchases.room.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.coffeeshop.data.roomDone.supplier.entities.SupplierDbEntity

@Entity(
    tableName = "purchases",
    foreignKeys = [
        ForeignKey(
            entity = SupplierDbEntity::class,
            parentColumns = ["id"],
            childColumns = ["supplier_id"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index("supplier_id")]
)
data class PurchaseDbEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "supplier_id") val supplierId: Int,
    @ColumnInfo(name = "purchase_date") val purchaseDate: Long
)