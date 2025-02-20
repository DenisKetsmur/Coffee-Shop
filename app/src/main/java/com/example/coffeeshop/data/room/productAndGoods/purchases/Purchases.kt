package com.example.coffeeshop.data.room.productAndGoods.purchases

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.coffeeshop.data.room.users.Supplier

@Entity(
    tableName = "purchases",
    foreignKeys = [
        ForeignKey(
            entity = Supplier::class,
            parentColumns = ["id"],
            childColumns = ["supplierId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index(value = ["supplierId"])]
)
data class Purchase(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val supplierId: Int,
    val purchaseDate: Long
)
