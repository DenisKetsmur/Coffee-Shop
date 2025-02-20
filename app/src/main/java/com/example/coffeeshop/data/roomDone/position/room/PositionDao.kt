package com.example.coffeeshop.data.roomDone.position.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.coffeeshop.data.roomDone.position.room.entities.PositionEntity
import kotlinx.coroutines.flow.Flow
@Dao
interface PositionDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(position: PositionEntity)

    @Query("SELECT * FROM positions WHERE id = :id")
    suspend fun getPositionById(id: Int): PositionEntity?

    @Query("SELECT * FROM positions")
    fun getAllPositions(): Flow<List<PositionEntity>>

    @Update
    suspend fun updatePosition(position: PositionEntity)

    @Delete
    suspend fun deletePosition(position: PositionEntity)
}



