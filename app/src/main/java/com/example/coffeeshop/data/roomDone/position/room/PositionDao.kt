package com.example.coffeeshop.data.roomDone.position.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.coffeeshop.data.roomDone.position.room.entities.PositionDbEntity
import kotlinx.coroutines.flow.Flow
@Dao
interface PositionDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(position: PositionDbEntity)

    @Query("SELECT * FROM positions WHERE id = :id")
    suspend fun getPositionById(id: Int): PositionDbEntity?

    @Query("SELECT * FROM positions")
    fun getAllPositions(): Flow<List<PositionDbEntity>>

    @Update
    suspend fun updatePosition(position: PositionDbEntity)

    @Delete
    suspend fun deletePosition(position: PositionDbEntity)
}



