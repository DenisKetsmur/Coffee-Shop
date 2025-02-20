package com.example.coffeeshop.data.roomDone.products

import com.example.coffeeshop.data.roomDone.products.entities.Product
import com.example.coffeeshop.data.roomDone.products.room.ProductDao
import com.example.coffeeshop.data.roomDone.products.room.ProductRepository
import com.example.coffeeshop.data.roomDone.products.room.entities.ProductDbEntity
import com.example.coffeeshop.data.roomDone.products.room.entities.ProductUpdateTuple
import com.example.coffeeshop.wrapSQLiteException
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class RoomProductRepository(
    private val productDao: ProductDao,
    private val ioDispatcher: CoroutineDispatcher
): ProductRepository {
    override fun getAllProduct(): Flow<List<Product>> =
        productDao.getAllProduct().map { list -> list.map { it.toProduct() }
    }

    override suspend fun getProductById(id: Long): Product? {
        return productDao.getProductById(id)?.toProduct()
    }

    override suspend fun addProduct(product: Product) = wrapSQLiteException(ioDispatcher) {
        if (product.price < 0) throw IllegalArgumentException("Ціна не може бути від’ємною")
        if (product.quantity < 0) throw IllegalArgumentException("Кількість не може бути від’ємною")

        productDao.insert(ProductDbEntity.fromProduct(product))
    }
    override suspend fun updateProduct(productUpdateTuple: ProductUpdateTuple) = wrapSQLiteException(ioDispatcher) {
        if (productUpdateTuple.price < 0) throw IllegalArgumentException("Ціна не може бути від’ємною")
        productDao.updateProduct(productUpdateTuple)
    }

    override suspend fun deleteProduct(product: Product?) = wrapSQLiteException(ioDispatcher) {
        if (product == null) throw IllegalArgumentException("Неможливо видалити: об'єкт good є null")

        productDao.deleteProduct(ProductDbEntity.fromProduct(product))
    }
}
