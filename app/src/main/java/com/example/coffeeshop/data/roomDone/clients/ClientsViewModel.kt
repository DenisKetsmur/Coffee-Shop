package com.example.coffeeshop.data.roomDone.clients

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coffeeshop.data.roomDone.clients.entities.Client
import com.example.coffeeshop.data.roomDone.clients.entities.SignUpData
import com.example.coffeeshop.data.roomDone.clients.room.ClientsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ClientsViewModel(
    private val repository: ClientsRepository
) : ViewModel() {

    private val _loadingState = MutableStateFlow(false)
    val loadingState: StateFlow<Boolean> = _loadingState.asStateFlow()

    private val _errorState = MutableStateFlow<String?>(null)
    val errorState: StateFlow<String?> = _errorState.asStateFlow()

    // Профіль клієнта зберігаємо тут
    private val _clientProfile = MutableStateFlow<Client?>(null)
    val clientProfile: StateFlow<Client?> = _clientProfile.asStateFlow()

    // Перевірка, чи користувач увійшов
    private val _isSignedIn = MutableStateFlow(false)
    val isSignedIn: StateFlow<Boolean> = _isSignedIn.asStateFlow()

    init {
        // Стартове завантаження
        viewModelScope.launch {
            _isSignedIn.value = repository.isSignedIn()

            // Збираємо (collect) профіль клієнта
            repository.getClient().collect { client ->
                _clientProfile.value = client
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
                _clientProfile.value = null
            } catch (e: Exception) {
                _errorState.value = e.message
            } finally {
                _loadingState.value = false
            }
        }
    }

    // Оновлення даних профілю
    fun updateClient(newFirstName: String, newLastName: String, newPhone: String) {
        viewModelScope.launch {
            _loadingState.value = true
            _errorState.value = null
            try {
                repository.clientUpdate(newFirstName, newLastName, newPhone)
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

