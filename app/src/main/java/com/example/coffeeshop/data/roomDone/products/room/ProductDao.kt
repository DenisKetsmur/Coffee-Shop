package com.example.coffeeshop.data.roomDone.products.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.coffeeshop.data.roomDone.goods.room.entities.GoodBdEntity
import com.example.coffeeshop.data.roomDone.products.room.entities.ProductDbEntity
import com.example.coffeeshop.data.roomDone.products.room.entities.ProductUpdateTuple
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(product: ProductDbEntity)

    @Query("SELECT * FROM products WHERE id = :id")
    suspend fun getProductById(id: Long): ProductDbEntity?

    @Query("SELECT * FROM products")
    fun getAllProduct(): Flow<List<ProductDbEntity>>

    @Delete
    suspend fun deleteProduct(product: ProductDbEntity)

    @Update(entity = GoodBdEntity::class)
    suspend fun updateProduct(productUpdateTuple: ProductUpdateTuple)

}
