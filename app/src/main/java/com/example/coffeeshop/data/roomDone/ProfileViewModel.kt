package com.example.coffeeshop.data.roomDone

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coffeeshop.data.roomDone.clients.room.ClientDao
import com.example.coffeeshop.data.roomDone.clients.room.entities.ClientDbEntity
import com.example.coffeeshop.data.roomDone.employee.room.EmployeeDao
import com.example.coffeeshop.data.roomDone.employee.room.entities.EmployeeDbEntity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val clientDao: ClientDao,
    private val employeeDao: EmployeeDao
) : ViewModel() {

    // Поля для зберігання поточного користувача
    private val _client = MutableStateFlow<ClientDbEntity?>(null)
    val client: StateFlow<ClientDbEntity?> = _client.asStateFlow()

    private val _employee = MutableStateFlow<EmployeeDbEntity?>(null)
    val employee: StateFlow<EmployeeDbEntity?> = _employee.asStateFlow()

    // Завантажити профіль за userId
    fun loadProfile(userId: Long) {
        viewModelScope.launch {
            // 1. Спробуємо знайти клієнта
            val foundClient = clientDao.findById(userId)
            if (foundClient != null) {
                // Це клієнт
                _client.value = foundClient
                _employee.value = null
            } else {
                // 2. Якщо не знайшли, шукаємо працівника
                val foundEmployee = employeeDao.findById(userId)
                if (foundEmployee != null) {
                    _employee.value = foundEmployee
                    _client.value = null
                } else {
                    // Немає ні клієнта, ні працівника
                    // Обробити помилку
                }
            }
        }
    }
}
