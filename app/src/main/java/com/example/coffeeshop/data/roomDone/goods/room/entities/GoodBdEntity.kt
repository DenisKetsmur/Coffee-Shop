package com.example.coffeeshop.data.roomDone.goods.room.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.coffeeshop.data.roomDone.goods.entities.Good
import com.example.coffeeshop.data.roomDone.category.Category

@Entity(
    tableName = "goods",
    foreignKeys = [
        ForeignKey(
            entity = Category::class,
            parentColumns = ["id"],
            childColumns = ["category_id"],
            onDelete = ForeignKey.SET_NULL
        ),
        ForeignKey(
            entity = Unit::class,
            parentColumns = ["id"],
            childColumns = ["unit_id"],
            onDelete = ForeignKey.SET_NULL
        )
    ],
    indices = [Index("category_id"), Index("unit_id")]
)
data class GoodBdEntity(
    @PrimaryKey(autoGenerate = true) val id: Long,
    val name: String,
    @ColumnInfo(name = "category_id") val categoryId: Int?,
    val price: Double,
    @ColumnInfo(name = "unit_id") val unitId: Int?,
    val quantity: Double= 0.0,
    val description: String = ""
){
    fun toGood(): Good = Good(
        id = id,
        name = name,
        categoryId = categoryId,
        description = description,
        quantity = quantity,
        price = price,
        unitId = unitId,
    )

    companion object {
        fun fromGood(good: Good): GoodBdEntity = GoodBdEntity(
            id = good.id,
            name = good.name,
            categoryId = good.categoryId,
            description = good.description,
            quantity = good.quantity,
            price = good.price,
            unitId = good.unitId
        )
    }

}
