package com.example.coffeeshop.data.roomDone.supplier.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "suppliers",
    indices = [Index("email", unique = true)]
)
data class SupplierDbEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val name: String,
    val email: String,
    val phone: String,
    @ColumnInfo(name = "created_at") val createdAt: Long
)


