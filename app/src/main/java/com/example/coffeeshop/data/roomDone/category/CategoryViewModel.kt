package com.example.coffeeshop.data.roomDone.category

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class CategoryViewModel(private val dao: CategoryDao) : ViewModel() {

    // 🔹 Список усіх категорій
    private val _categories = MutableStateFlow<List<CategoryDbEntity>>(emptyList())
    val categories: StateFlow<List<CategoryDbEntity>> = _categories.asStateFlow()

    // 🔹 Список тільки продуктів
    private val _productCategories = MutableStateFlow<List<CategoryDbEntity>>(emptyList())
    val productCategories: StateFlow<List<CategoryDbEntity>> = _productCategories.asStateFlow()

    // 🔹 Список тільки товарів
    private val _goodsCategories = MutableStateFlow<List<CategoryDbEntity>>(emptyList())
    val goodsCategories: StateFlow<List<CategoryDbEntity>> = _goodsCategories.asStateFlow()

    // 🔹 Стан завантаження
    private val _loadingState = MutableStateFlow(false)
    val loadingState: StateFlow<Boolean> = _loadingState.asStateFlow()

    // 🔹 Помилки
    private val _errorState = MutableStateFlow<String?>(null)
    val errorState: StateFlow<String?> = _errorState.asStateFlow()

    init {
        loadCategories()
        loadProductCategories()
        loadGoodsCategories()
    }

    //  Завантаження всіх категорій
    private fun loadCategories() {
        viewModelScope.launch {
            _loadingState.value = true
            try {
                dao.getAllCategories()
                    .collect { categories ->
                        _categories.value = categories
                    }
            } catch (e: Exception) {
                _errorState.value = "Помилка завантаження категорій: ${e.message}"
            } finally {
                _loadingState.value = false
            }
        }
    }

    //  Завантаження категорій "продукти"
    private fun loadProductCategories() {
        viewModelScope.launch {
            dao.getAllCategoriesProduct().collect { categories ->
                _productCategories.value = categories
            }
        }
    }

    //  Завантаження категорій "товари"
    private fun loadGoodsCategories() {
        viewModelScope.launch {
            dao.getAllCategoriesGoods().collect { categories ->
                _goodsCategories.value = categories
            }
        }
    }

    //  Додавання нової категорії
    fun addCategory(name: String, type: CategoryType) {
        viewModelScope.launch {
            _loadingState.value = true
            try {
                val newCategory = CategoryDbEntity(name = name, categoryType = type)
                dao.insertCategory(newCategory)
                loadCategories() // Оновити список після вставки
            } catch (e: Exception) {
                _errorState.value = "Помилка додавання категорії: ${e.message}"
            } finally {
                _loadingState.value = false
            }
        }
    }

    //  Оновлення категорії
    fun updateCategory(category: CategoryDbEntity) {
        viewModelScope.launch {
            _loadingState.value = true
            try {
                dao.updateCategory(category)
                loadCategories()
            } catch (e: Exception) {
                _errorState.value = "Помилка оновлення категорії: ${e.message}"
            } finally {
                _loadingState.value = false
            }
        }
    }

    //  Видалення категорії
    fun deleteCategory(category: CategoryDbEntity) {
        viewModelScope.launch {
            _loadingState.value = true
            try {
                dao.deleteCategory(category)
                loadCategories()
            } catch (e: Exception) {
                _errorState.value = "Помилка видалення категорії: ${e.message}"
            } finally {
                _loadingState.value = false
            }
        }
    }

    //  Очистити помилку
    fun clearError() {
        _errorState.value = null
    }
}