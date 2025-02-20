package com.example.coffeeshop.data.roomDone.employee

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coffeeshop.data.roomDone.employee.entities.SignUpData
import com.example.coffeeshop.data.roomDone.employee.entities.Employee
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class EmployeeViewModel(
    private val repository: RoomEmployeeRepository
) : ViewModel() {

    private val _loadingState = MutableStateFlow(false)
    val loadingState: StateFlow<Boolean> = _loadingState.asStateFlow()

    private val _errorState = MutableStateFlow<String?>(null)
    val errorState: StateFlow<String?> = _errorState.asStateFlow()

    // Профіль клієнта зберігаємо тут
    private val _employeeProfile = MutableStateFlow<Employee?>(null)
    val employeeProfile: StateFlow<Employee?> = _employeeProfile.asStateFlow()

    // Перевірка, чи користувач увійшов
    private val _isSignedIn = MutableStateFlow(false)
    val isSignedIn: StateFlow<Boolean> = _isSignedIn.asStateFlow()

    init {
        // Стартове завантаження
        viewModelScope.launch {
            _isSignedIn.value = repository.isSignedIn()

            // Збираємо (collect) профіль клієнта
            repository.getEmployee().collect { employee ->
                _employeeProfile.value = employee
            }
        }
    }

    // Вхід
    fun signIn(email: String, password: String) {
        viewModelScope.launch {
            _loadingState.value = true
            _errorState.value = null
            try {
                repository.signIn(email, password)
                _isSignedIn.value = true
            } catch (e: Exception) {
                _errorState.value = e.message
            } finally {
                _loadingState.value = false
            }
        }
    }

    // Реєстрація
    fun signUp(signUpData: SignUpData) {
        viewModelScope.launch {
            _loadingState.value = true
            _errorState.value = null
            try {
                repository.signUp(signUpData)
                _isSignedIn.value = true
            } catch (e: Exception) {
                _errorState.value = e.message
            } finally {
                _loadingState.value = false
            }
        }
    }

    // Вихід
    fun logout() {
        viewModelScope.launch {
            _loadingState.value = true
            _errorState.value = null
            try {
                repository.logout()
                _isSignedIn.value = false
                _employeeProfile.value = null
            } catch (e: Exception) {
                _errorState.value = e.message
            } finally {
                _loadingState.value = false
            }
        }
    }

    // Оновлення даних профілю
    fun updateEmployee(
        newFirstName: String,
        newLastName: String,
        newPhone: String,
        password: String,
        birthDate: Long,
        positionId: Int,
        workScheduleId: Int,
        status:Int,
    ) {
        viewModelScope.launch {
            _loadingState.value = true
            _errorState.value = null
            try {
                repository.employeeUpdate(
                    newFirstName,
                    newLastName,
                    newPhone,
                    password,
                    birthDate,
                    positionId,
                    workScheduleId,
                    status
                )
            } catch (e: Exception) {
                _errorState.value = e.message
            } finally {
                _loadingState.value = false
            }
        }
    }

    // Очищення помилки
    fun clearError() {
        _errorState.value = null
    }
}

