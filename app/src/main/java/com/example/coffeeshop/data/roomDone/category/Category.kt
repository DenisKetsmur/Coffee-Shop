package com.example.coffeeshop.data.roomDone.category

import androidx.room.ColumnInfo
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.TypeConverter
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Entity(tableName = "categories")
data class CategoryDbEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    @ColumnInfo(name = "category_type") val categoryType: CategoryType
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

@Dao
interface CategoryDao {

    // Повертає усі категорії з типом PRODUCT
    @Query("SELECT * FROM categories WHERE category_type = 'product'")
    fun getAllCategoriesProduct(): Flow<List<CategoryDbEntity>>

    // Повертає усі категорії з типом GOODS
    @Query("SELECT * FROM categories WHERE category_type = 'goods'")
    fun getAllCategoriesGoods(): Flow<List<CategoryDbEntity>>

    // Якщо тобі треба все разом, без фільтра
    @Query("SELECT * FROM categories")
    fun getAllCategories(): Flow<List<CategoryDbEntity>>

    @Query("SELECT * FROM categories WHERE id = :id")
    suspend fun getCategoryById(id: Int): CategoryDbEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCategory(category: CategoryDbEntity)

    @Update
    suspend fun updateCategory(category: CategoryDbEntity)

    @Delete
    suspend fun deleteCategory(category: CategoryDbEntity)
}