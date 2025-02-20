package com.example.coffeeshop.data.roomDone.supplier

import com.example.coffeeshop.data.roomDone.supplier.entities.SupplierDao
import com.example.coffeeshop.data.roomDone.supplier.entities.SupplierDbEntity
import com.example.coffeeshop.data.exception.wrapSQLiteException
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

interface SupplierRepository {
    fun getAllSuppliers(): Flow<List<SupplierDbEntity>>
    suspend fun insertSupplier(supplier: SupplierDbEntity)
    suspend fun updateSupplier(supplier: SupplierDbEntity)
    suspend fun deleteSupplier(supplier: SupplierDbEntity)
    suspend fun findByEmail(email: String): SupplierDbEntity?
}

class RoomSupplierRepository(
    private val dao: SupplierDao,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : SupplierRepository {

    override fun getAllSuppliers(): Flow<List<SupplierDbEntity>> {
        // Повертаємо Flow, який автоматично оновлюється
        return dao.getAllSuppliers()
            .flowOn(ioDispatcher)
    }

    override suspend fun insertSupplier(supplier: SupplierDbEntity) = wrapSQLiteException(ioDispatcher) {
        dao.insertSupplier(supplier)
    }

    override suspend fun updateSupplier(supplier: SupplierDbEntity) = wrapSQLiteException(ioDispatcher) {
        dao.updateSupplier(supplier)
    }

    override suspend fun deleteSupplier(supplier: SupplierDbEntity) = wrapSQLiteException(ioDispatcher) {
        dao.deleteSupplier(supplier)
    }

    override suspend fun findByEmail(email: String): SupplierDbEntity? = wrapSQLiteException(ioDispatcher) {
        dao.findByEmail(email)
    }
}
