package com.example.coffeeshop.data.roomDone.position.room

import com.example.coffeeshop.data.roomDone.position.entities.Position
import kotlinx.coroutines.flow.Flow

interface PositionRepository {

    fun getAllPositions(): Flow<List<Position>>

    suspend fun getPositionById(id: Int): Position?

    suspend fun addPosition(position: Position)

    suspend fun updatePosition(position: Position)

    suspend fun deletePosition(position: Position)

}