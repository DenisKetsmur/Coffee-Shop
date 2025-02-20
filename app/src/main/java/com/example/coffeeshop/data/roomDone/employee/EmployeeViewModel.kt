package com.example.coffeeshop.data.roomDone.employee

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coffeeshop.data.exception.EmptyFieldException
import com.example.coffeeshop.data.exception.PasswordMismatchException
import com.example.coffeeshop.data.roomDone.clients.entities.Client
import com.example.coffeeshop.data.roomDone.employee.entities.SignUpData
import com.example.coffeeshop.data.roomDone.employee.entities.Employee
import com.example.coffeeshop.data.roomDone.employee.entities.InvalidDateException
import com.example.coffeeshop.data.roomDone.employee.room.EmployeeDao
import com.example.coffeeshop.data.roomDone.employee.room.entities.EmployeeDbEntity
import com.example.coffeeshop.data.roomDone.employee.room.entities.EmployeeUpdateTuple
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class EmployeeViewModel(private val employeeDao: EmployeeDao) : ViewModel() {

    val employees: StateFlow<List<EmployeeDbEntity>> = employeeDao.getAllEmployees()
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    private val _currentEmployee = MutableStateFlow<EmployeeDbEntity?>(null)
    val currentEmployee: StateFlow<EmployeeDbEntity?> = _currentEmployee.asStateFlow()


    fun addEmployee(employee: EmployeeDbEntity) {
        employee.toEmployee()
        viewModelScope.launch {
            employeeDao.insertEmployee(employee)
        }
    }

    fun updateEmployee(employee: EmployeeUpdateTuple) {
        viewModelScope.launch {
            employeeDao.updateEmployee(employee)
        }
    }

    fun deleteEmployee(employee: EmployeeDbEntity) {
        viewModelScope.launch {
            employeeDao.deleteEmployee(employee)
        }
    }

    fun signUp(signUpData: SignUpData) {
        viewModelScope.launch {
            try {
                signUpData.validate()
                val employeeEntity = EmployeeDbEntity.fromSignUpData(signUpData)
                employeeDao.insertEmployee(employeeEntity)
                // ... інші кроки (напр. зберегти статус, показати екран)
            } catch (e: EmptyFieldException) {
                // Обробити помилку пустого поля
            } catch (e: PasswordMismatchException) {
                // Обробити невідповідність паролів
            } catch (e: InvalidDateException) {
                // Обробити некоректну дату
            }
        }
    }

    // Оновлення поточного клієнта
    fun setCurrentEmployee(employee: EmployeeDbEntity) {
        _currentEmployee.value = employee
    }

    suspend fun findEmployeeByEmail(email: String): EmployeeDbEntity? {
        return employeeDao.findByEmail(email)
    }
}

