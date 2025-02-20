package com.example.coffeeshop.data.roomDone.employee

import android.database.sqlite.SQLiteConstraintException
import com.example.coffeeshop.data.AccountAlreadyExistsException
import com.example.coffeeshop.data.AuthException
import com.example.coffeeshop.data.EmptyFieldException
import com.example.coffeeshop.data.Field
import com.example.coffeeshop.data.roomDone.employee.entities.SignUpData
import com.example.coffeeshop.data.roomDone.employee.entities.Employee
import com.example.coffeeshop.data.roomDone.employee.room.EmployeeDao
import com.example.coffeeshop.data.roomDone.employee.room.EmployeesRepository
import com.example.coffeeshop.data.roomDone.employee.room.entities.EmployeeDbEntity
import com.example.coffeeshop.data.roomDone.employee.room.entities.EmployeeDbEntity.Companion.fromSignUpData
import com.example.coffeeshop.data.roomDone.employee.room.entities.EmployeeUpdateTuple
import com.example.coffeeshop.data.settings.AppSettings
import com.example.coffeeshop.utils.AsyncLoader
import com.example.coffeeshop.wrapSQLiteException
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*

class RoomEmployeeRepository(
    private val employeeDao: EmployeeDao,
    private val appSettings: AppSettings,
    private val ioDispatcher: CoroutineDispatcher
) : EmployeesRepository {

    private val currentEmployeeIdFlow = AsyncLoader {
        MutableStateFlow(EmployeeId(appSettings.getCurrentEmployeeId()))
    }

    override suspend fun isSignedIn(): Boolean {
        delay(2000)
        return appSettings.getCurrentEmployeeId() != AppSettings.NO_ACCOUNT_ID
    }

    override suspend fun signIn(email: String, password: String) = wrapSQLiteException(ioDispatcher) {
        if (email.isBlank()) throw EmptyFieldException(Field.Email)
        if (password.isBlank()) throw EmptyFieldException(Field.Password)

        delay(1000)

        val employeeId = findEmployeeIdByEmailAndPassword(email, password)
        appSettings.setCurrentEmployeeId(employeeId)
        currentEmployeeIdFlow.get().value = EmployeeId(employeeId)

        return@wrapSQLiteException
    }

    override suspend fun signUp(signUpData: SignUpData) = wrapSQLiteException(ioDispatcher) {
        signUpData.validate()
        delay(1000)
        createEmployee(signUpData)
    }

    override suspend fun logout() {
        appSettings.setCurrentEmployeeId(AppSettings.NO_ACCOUNT_ID)
        currentEmployeeIdFlow.get().value = EmployeeId(AppSettings.NO_ACCOUNT_ID)
    }

    override suspend fun employeeUpdate(
        newFirstName: String,
        newLastName: String,
        newPhone: String,
        birthDate: Long,
        positionId: Int?,
        workScheduleId: Int?,
        status: Int
    ) {
        TODO("Not yet implemented")
    }

    override suspend fun employeeUpdateHim(
        newFirstName: String,
        newLastName: String,
        newPhone: String,
        birthDate: Long,
        password: String,
    ) {
        TODO("Not yet implemented")
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    override suspend fun getEmployee(): Flow<Employee?> {
        return currentEmployeeIdFlow.get()
            .flatMapLatest { employeeId ->
                if (employeeId.value == AppSettings.NO_ACCOUNT_ID) {
                    flowOf(null)
                } else {
                    getEmployeeById(employeeId.value)
                }
            }
            .flowOn(ioDispatcher)
    }

    override fun getAllEmployee(): Flow<List<Employee>> {
        return employeeDao.getAllEmployee()
            .map { list -> list.map { it.toEmployee() } }
            .flowOn(ioDispatcher)
    }

    suspend fun employeeUpdate(
        newFirstName: String,
        newLastName: String,
        newPhone: String,
        password: String,
        birthDate: Long,
        positionId: Int,
        workScheduleId: Int,
        status:Int,
    ) = wrapSQLiteException(ioDispatcher) {
        if (newFirstName.isBlank()) throw EmptyFieldException(Field.FirstName)
        if (newLastName.isBlank()) throw EmptyFieldException(Field.LastName)
        if (newPhone.isBlank()) throw EmptyFieldException(Field.Phone)

        delay(1000)

        val employeeId = appSettings.getCurrentEmployeeId()
        if (employeeId == AppSettings.NO_ACCOUNT_ID) throw AuthException()

        updateIntoForEmployeeId(
            employeeId,
            newFirstName,
            newLastName,
            newPhone,
            password,
            birthDate,
            positionId,
            workScheduleId,
            status
        )

        currentEmployeeIdFlow.get().value = EmployeeId(employeeId)
        return@wrapSQLiteException
    }

    private suspend fun findEmployeeIdByEmailAndPassword(email: String, password: String): Long {
        val tuple = employeeDao.findByEmail(email) ?: throw AuthException()
        if (tuple.password != password) throw AuthException()
        return tuple.id
    }

    private suspend fun createEmployee(signUpData: SignUpData) {
        try {
            val entity = fromSignUpData(signUpData)
            employeeDao.createEmployee(entity)
        } catch (e: SQLiteConstraintException) {
            throw AccountAlreadyExistsException().apply { initCause(e) }
        }
    }


    private fun getEmployeeById(employeeId: Long): Flow<Employee?> {
        return employeeDao.getById(employeeId).map { it?.toEmployee() }
    }


    private suspend fun updateIntoForEmployeeId(
        accountId: Long,
        newFirstName: String,
        newLastName: String,
        newPhone: String,
        password: String,
        birthDate: Long,
        positionId: Int,
        workScheduleId: Int,
        status:Int,
    ) {
        employeeDao.employeeUpdate(
            EmployeeUpdateTuple(
                accountId,
                newFirstName,
                newLastName,
                newPhone,
                password,
                birthDate,
                positionId,
                workScheduleId,
                status
            )
        )
    }

    private class EmployeeId(val value: Long)

}