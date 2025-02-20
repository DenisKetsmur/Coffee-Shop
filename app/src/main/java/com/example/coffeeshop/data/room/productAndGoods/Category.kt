package com.example.coffeeshop.data.room.productAndGoods

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter

@Entity(tableName = "categories")
data class Category(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val categoryType: CategoryType
)

enum class CategoryType(val type: String) {
    PRODUCT("product"),
    GOODS("goods");

    companion object {
        fun fromString(value: String) = entries.first { it.type == value }
    }
}

class CategoryTypeConverter {
    @TypeConverter
    fun fromCategoryType(categoryType: CategoryType): String {
        return categoryType.type
    }

    @TypeConverter
    fun toCategoryType(value: String): CategoryType {
        return CategoryType.fromString(value)
    }
}