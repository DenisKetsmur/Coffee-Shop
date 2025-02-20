package com.example.coffeeshop.data.roomDone.workScheduleEntity

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coffeeshop.data.roomDone.supplier.entities.SupplierDbEntity
import com.example.coffeeshop.data.roomDone.workScheduleEntity.entities.WorkScheduleDao
import com.example.coffeeshop.data.roomDone.workScheduleEntity.entities.WorkScheduleDbEntity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class WorkScheduleViewModel(private val dao: WorkScheduleDao) : ViewModel() {

    // 🔹 Стан завантаження
    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading.asStateFlow()

    // 🔹 Стан помилки
    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()

    // 1. Додавання нового розкладу
    fun addWorkSchedule(
        startTime: Long,
        endTime: Long,
        workSchedule: String,
        paymentPerHour: Double,
        employeesId: Int
    ) {
        viewModelScope.launch {
            _loading.value = true
            _error.value = null
            try {
                val newSchedule = WorkScheduleDbEntity(
                    startTime = startTime,
                    endTime = endTime,
                    workSchedule = workSchedule,
                    paymentPerHour = paymentPerHour,
                    employeesId = employeesId
                )
                dao.insertWorkSchedule(newSchedule)
                // Завдяки Flow, allSchedules автоматично оновиться
            } catch (e: Exception) {
                _error.value = "Помилка додавання розкладу: ${e.message}"
            } finally {
                _loading.value = false
            }
        }
    }

    // 2. Оновлення розкладу
    fun updateWorkSchedule(schedule: WorkScheduleDbEntity) {
        viewModelScope.launch {
            _loading.value = true
            _error.value = null
            try {
                dao.updateWorkSchedule(schedule)
            } catch (e: Exception) {
                _error.value = "Помилка оновлення розкладу: ${e.message}"
            } finally {
                _loading.value = false
            }
        }
    }

    // 3. Видалення розкладу
    fun deleteWorkSchedule(schedule: WorkScheduleDbEntity) {
        viewModelScope.launch {
            _loading.value = true
            _error.value = null
            try {
                dao.deleteWorkSchedule(schedule)
            } catch (e: Exception) {
                _error.value = "Помилка видалення розкладу: ${e.message}"
            } finally {
                _loading.value = false
            }
        }
    }

    // 4. Отримання розкладів для конкретного працівника (опційно)
    //    Можна зробити окрему StateFlow
    private val _employeeSchedules = MutableStateFlow<List<WorkScheduleDbEntity>>(emptyList())
    val employeeSchedules: StateFlow<List<WorkScheduleDbEntity>> = _employeeSchedules.asStateFlow()

    fun loadSchedulesForEmployee(employeeId: Long) {
        viewModelScope.launch {
            dao.getWorkSchedulesForEmployee(employeeId).collect { list ->
                _employeeSchedules.value = list
            }
        }
    }

    // 5. Очистити помилку
    fun clearError() {
        _error.value = null
    }
}

