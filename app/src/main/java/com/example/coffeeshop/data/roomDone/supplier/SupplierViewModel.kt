package com.example.coffeeshop.data.roomDone.supplier

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coffeeshop.data.roomDone.supplier.entities.SupplierDbEntity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class SupplierViewModel(private val repository: SupplierRepository) : ViewModel() {

    // Список постачальників (автоматичне оновлення)
    val suppliers: StateFlow<List<SupplierDbEntity>> = repository.getAllSuppliers()
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    // Стан завантаження
    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading.asStateFlow()

    // Стан помилки
    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()

    // Додати постачальника
    fun addSupplier(name: String, email: String, phone: String) {
        viewModelScope.launch {
            _loading.value = true
            _error.value = null
            try {
                val supplier = SupplierDbEntity(
                    name = name,
                    email = email,
                    phone = phone,
                    createdAt = System.currentTimeMillis()
                )
                repository.insertSupplier(supplier)
            } catch (e: Exception) {
                _error.value = e.message
            } finally {
                _loading.value = false
            }
        }
    }

    // Оновити постачальника
    fun updateSupplier(supplier: SupplierDbEntity) {
        viewModelScope.launch {
            _loading.value = true
            _error.value = null
            try {
                repository.updateSupplier(supplier)
            } catch (e: Exception) {
                _error.value = e.message
            } finally {
                _loading.value = false
            }
        }
    }

    // Видалити постачальника
    fun deleteSupplier(supplier: SupplierDbEntity) {
        viewModelScope.launch {
            _loading.value = true
            _error.value = null
            try {
                repository.deleteSupplier(supplier)
            } catch (e: Exception) {
                _error.value = e.message
            } finally {
                _loading.value = false
            }
        }
    }

    // Очистити помилку
    fun clearError() {
        _error.value = null
    }
}
