package com.example.coffeeshop.data.roomDone.employee.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.coffeeshop.data.roomDone.clients.room.entities.ClientDbEntity
import com.example.coffeeshop.data.roomDone.employee.entities.Employee
import com.example.coffeeshop.data.roomDone.employee.room.entities.EmployeeDbEntity
import com.example.coffeeshop.data.roomDone.employee.room.entities.EmployeeSignInTuple
import com.example.coffeeshop.data.roomDone.employee.room.entities.EmployeeUpdateHimTuple
import com.example.coffeeshop.data.roomDone.employee.room.entities.EmployeeUpdateTuple
import kotlinx.coroutines.flow.Flow

@Dao
interface EmployeeDao {

    @Query("SELECT * FROM employees WHERE id = :id LIMIT 1")
    suspend fun findById(id: Long): EmployeeDbEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEmployee(employee: EmployeeDbEntity)

    @Update
    suspend fun updateEmployee(employee: EmployeeUpdateTuple)

    @Delete
    suspend fun deleteEmployee(employee: EmployeeDbEntity)

    @Query("SELECT * FROM employees WHERE email = :email LIMIT 1")
    suspend fun findByEmail(email: String): EmployeeDbEntity?

    @Query("SELECT * FROM employees")
    fun getAllEmployees(): Flow<List<EmployeeDbEntity>>
}



