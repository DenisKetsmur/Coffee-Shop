package com.example.coffeeshop.data.roomDone.employee.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.coffeeshop.data.roomDone.clients.room.entities.ClientDbEntity
import com.example.coffeeshop.data.roomDone.employee.room.entities.EmployeeDbEntity
import com.example.coffeeshop.data.roomDone.employee.room.entities.EmployeeSignInTuple
import com.example.coffeeshop.data.roomDone.employee.room.entities.EmployeeUpdateHimTuple
import com.example.coffeeshop.data.roomDone.employee.room.entities.EmployeeUpdateTuple
import kotlinx.coroutines.flow.Flow

@Dao
interface EmployeeDao {

    @Query("SELECT id, password FROM employees WHERE email = :email ")
    suspend fun findByEmail(email: String): EmployeeSignInTuple?

    @Update(entity = EmployeeDbEntity::class)
    suspend fun employeeUpdate(employeeUpdateTuple: EmployeeUpdateTuple)

    @Update(entity = EmployeeDbEntity::class)
    suspend fun employeeUpdateHim(employeeUpdateHimTuple: EmployeeUpdateHimTuple)

    @Insert
    suspend fun createEmployee(employeeDbEntity: EmployeeDbEntity)

    @Query("SELECT * FROM employees WHERE id = :employeeId ")
    fun getById(employeeId: Long):Flow<EmployeeDbEntity?>

}



