package com.example.coffeeshop.data.room.productAndGoods

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.coffeeshop.data.productAndGoods.Product
import com.example.coffeeshop.data.room.users.Supplier

@Entity(
    tableName = "products",
    foreignKeys = [
        ForeignKey(
            entity = Category::class,
            parentColumns = ["id"],
            childColumns = ["category_id"],
            onDelete = ForeignKey.SET_NULL
        ),
        ForeignKey(
            entity = Supplier::class,
            parentColumns = ["id"],
            childColumns = ["supplier_id"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = Unit::class,
            parentColumns = ["id"],
            childColumns = ["unit_id"],
            onDelete = ForeignKey.SET_NULL
        )
    ],
    indices = [Index("category_id"), Index("supplier_id"), Index("unit_id")]
)
data class Product(
    @PrimaryKey(autoGenerate = true) val id: Long,
    val name: String,
    @ColumnInfo(name = "category_id") val categoryId: Int?,
    val quantity: Double,
    @ColumnInfo(name = "supplier_id")val supplierId: Int,
    val price: Double,
    @ColumnInfo(name = "unit_id")val unitId: Int?,
    val description: String = ""
){
    fun toProduct(): Product = com.example.coffeeshop.data.productAndGoods.Product(
        id = id,
        name = name,
        categoryId = categoryId,
        description = description,
        quantity = quantity,
        supplierId = supplierId,
        price = price,
        unitId = unitId,
    )
}