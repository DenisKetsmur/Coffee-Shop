package com.example.coffeeshop.data.roomDone.clients

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coffeeshop.data.roomDone.clients.entities.Client
import com.example.coffeeshop.data.roomDone.clients.entities.SignUpData
import com.example.coffeeshop.data.roomDone.clients.room.ClientDao
import com.example.coffeeshop.data.roomDone.clients.room.ClientsRepository
import com.example.coffeeshop.data.roomDone.clients.room.entities.ClientDbEntity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ClientViewModel(private val clientDao: ClientDao) : ViewModel() {

    val clients: StateFlow<List<ClientDbEntity>> = clientDao.getAllClients()
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()

    private val _currentClient = MutableStateFlow<ClientDbEntity?>(null)
    val currentClient: StateFlow<ClientDbEntity?> = _currentClient.asStateFlow()


    fun addClient(client: ClientDbEntity) {
        viewModelScope.launch {
            _loading.value = true
            _error.value = null
            try {
                clientDao.insertClient(client)
            } catch (e: Exception) {
                _error.value = "Помилка додавання клієнта: ${e.message}"
            } finally {
                _loading.value = false
            }
        }
    }

    fun updateClient(client: ClientDbEntity) {
        viewModelScope.launch {
            _loading.value = true
            _error.value = null
            try {
                clientDao.updateClient(client)
            } catch (e: Exception) {
                _error.value = "Помилка оновлення клієнта: ${e.message}"
            } finally {
                _loading.value = false
            }
        }
    }

    fun deleteClient(client: ClientDbEntity) {
        viewModelScope.launch {
            _loading.value = true
            _error.value = null
            try {
                clientDao.deleteClient(client)
            } catch (e: Exception) {
                _error.value = "Помилка видалення клієнта: ${e.message}"
            } finally {
                _loading.value = false
            }
        }
    }

    suspend fun findClientByEmail(email: String): ClientDbEntity? {
        return clientDao.findByEmail(email)
    }

    fun setCurrentClient(client: ClientDbEntity) {
        _currentClient.value = client
    }

    fun clearError() {
        _error.value = null
    }
}

