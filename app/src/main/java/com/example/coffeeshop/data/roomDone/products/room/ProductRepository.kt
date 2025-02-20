package com.example.coffeeshop.data.roomDone.products.room

import com.example.coffeeshop.data.roomDone.products.entities.Product
import com.example.coffeeshop.data.roomDone.products.room.entities.ProductUpdateTuple
import kotlinx.coroutines.flow.Flow

interface ProductRepository {

    fun getAllProduct(): Flow<List<Product>>

    suspend fun getProductById(id: Long): Product?

    suspend fun addProduct(product: Product)

    suspend fun updateProduct(productUpdateTuple: ProductUpdateTuple)

    suspend fun deleteProduct(product: Product?)
}
