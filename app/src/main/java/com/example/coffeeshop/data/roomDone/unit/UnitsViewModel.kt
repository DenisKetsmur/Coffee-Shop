package com.example.coffeeshop.data.roomDone.unit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class UnitsViewModel(private val unitDao: UnitDao) : ViewModel() {
    private val _units = MutableStateFlow<List<UnitDbEntity>>(emptyList())
    val units: StateFlow<List<UnitDbEntity>> = _units.asStateFlow()

    init {
        loadUnits()
    }

    private fun loadUnits() {
        viewModelScope.launch {
            val all = unitDao.getAllUnits()
            _units.value = all
        }
    }
}