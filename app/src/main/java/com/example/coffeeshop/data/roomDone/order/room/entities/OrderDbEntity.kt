package com.example.coffeeshop.data.roomDone.order.room.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.coffeeshop.data.roomDone.clients.room.entities.ClientDbEntity
import com.example.coffeeshop.data.roomDone.supplier.entities.SupplierDbEntity

@Entity(
    tableName = "orders",
    foreignKeys = [
        ForeignKey(
            entity = ClientDbEntity::class, // Припускаємо, що така сутність існує
            parentColumns = ["id"],
            childColumns = ["client_id"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index("client_id")]
)
data class OrderDbEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    @ColumnInfo(name = "client_id") val clientId: Long,
    @ColumnInfo(name = "order_date") val orderDate: Long
)