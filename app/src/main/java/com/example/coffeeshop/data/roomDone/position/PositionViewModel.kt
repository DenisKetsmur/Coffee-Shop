package com.example.coffeeshop.data.roomDone.position

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coffeeshop.data.roomDone.goods.entities.Good
import com.example.coffeeshop.data.roomDone.goods.room.GoodsRepository
import com.example.coffeeshop.data.roomDone.goods.room.entities.GoodUpdateTuple
import com.example.coffeeshop.data.roomDone.position.entities.Position
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class PositionViewModel(
    private val repository: RoomPositionRepository
) : ViewModel() {

    val allPositions: StateFlow<List<Position>> = repository.getAllPositions()
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    fun addPosition(position: Position) {
        viewModelScope.launch {
            repository.addPosition(position)
        }
    }
    fun updatePosition(position: Position) {
        viewModelScope.launch {
            repository.updatePosition(position)
        }
    }

    fun deletePosition(position: Position) {
        viewModelScope.launch {
            repository.deletePosition(position)
        }
    }
}