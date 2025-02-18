package com.example.coffeeshop.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

interface Repository<T : HasId> {
    fun getAll(): StateFlow<List<T>>
    fun getOne(id: Int): T?
    fun edit(item: T)
    fun add(item: T)

    companion object {
        fun <T : HasId> create(): Repository<T> = RepositoryImpl()
    }
}

class RepositoryImpl<T : HasId>(initialItems: List<T> = emptyList()) : Repository<T> {
    private val _items = MutableStateFlow(initialItems)

    override fun getAll(): StateFlow<List<T>> = _items

    override fun getOne(id: Int): T? = _items.value.find { it.id == id }

    override fun edit(item: T) {
        _items.value = _items.value.map { if (it.id == item.id) item else it }
    }

    override fun add(item: T) {
        _items.value += item
    }
}

open class ItemViewModel<T : HasId>(private val repository: Repository<T>) : ViewModel() {
    val items: StateFlow<List<T>> = repository.getAll()

    fun edit(item: T) {
        viewModelScope.launch {
            repository.edit(item)
        }
    }

    fun add(item: T) {
        viewModelScope.launch {
            repository.add(item)
        }
    }
}

interface HasId {
    val id: Int
}
