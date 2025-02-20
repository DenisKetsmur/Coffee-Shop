package com.example.coffeeshop.data.roomDone.position

import com.example.coffeeshop.data.roomDone.position.entities.Position
import com.example.coffeeshop.data.roomDone.position.room.PositionDao
import com.example.coffeeshop.data.roomDone.position.room.PositionRepository
import com.example.coffeeshop.data.roomDone.position.room.entities.PositionEntity
import com.example.coffeeshop.wrapSQLiteException
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.*

class RoomPositionRepository(
    private val positionDao: PositionDao,
    private val ioDispatcher: CoroutineDispatcher
): PositionRepository {

    override fun getAllPositions(): Flow<List<Position>> =
        positionDao.getAllPositions().map { list -> list.map { it.toEnum() } }

    override suspend fun getPositionById(id: Int): Position? = wrapSQLiteException(ioDispatcher) {
        positionDao.getPositionById(id)?.toEnum()
    }

    override suspend fun addPosition(position: Position) = wrapSQLiteException(ioDispatcher) {
        positionDao.insert(PositionEntity.fromEnum(position))
    }

    override suspend fun updatePosition(position: Position) = wrapSQLiteException(ioDispatcher) {
        positionDao.updatePosition(PositionEntity.fromEnum(position))
    }

    override suspend fun deletePosition(position: Position) = wrapSQLiteException(ioDispatcher) {
        positionDao.deletePosition(PositionEntity.fromEnum(position))
    }
}