package com.example.coffeeshop.data.roomDone.supplier.entities

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface SupplierDao {

    // Отримати весь список постачальників (реактивно, через Flow)
    @Query("SELECT * FROM suppliers")
    fun getAllSuppliers(): Flow<List<SupplierDbEntity>>

    // Знайти постачальника за email (повертає одного або null)
    @Query("SELECT * FROM suppliers WHERE email = :email")
    suspend fun findByEmail(email: String): SupplierDbEntity?

    // Додати/оновити постачальника
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSupplier(supplier: SupplierDbEntity)

    // Оновити існуючого постачальника
    @Update
    suspend fun updateSupplier(supplier: SupplierDbEntity)

    // Видалити постачальника
    @Delete
    suspend fun deleteSupplier(supplier: SupplierDbEntity)
}
