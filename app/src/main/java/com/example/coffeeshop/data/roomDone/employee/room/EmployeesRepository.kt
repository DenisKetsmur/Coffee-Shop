package com.example.coffeeshop.data.roomDone.employee.room

import com.example.coffeeshop.data.roomDone.employee.entities.SignUpData
import com.example.coffeeshop.data.roomDone.employee.entities.Employee
import com.example.coffeeshop.data.roomDone.employee.room.entities.EmployeeDbEntity
import kotlinx.coroutines.flow.Flow

interface EmployeesRepository {

    suspend fun isSignedIn():Boolean

    suspend fun signIn(email:String, password:String)

    suspend fun signUp(signUpData: SignUpData)

    suspend fun logout()

    suspend fun getEmployee(): Flow<Employee?>

    fun getAllEmployee(): Flow<List<Employee>>

    suspend fun employeeUpdate(
        newFirstName:String,
        newLastName:String,
        newPhone:String,
        birthDate: Long,
        positionId: Int?,
        workScheduleId: Int?,
        status: Int
    )

    suspend fun employeeUpdateHim(
        newFirstName:String,
        newLastName:String,
        newPhone:String,
        birthDate: Long,
        password:String,
    )

}
