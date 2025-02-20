package com.example.coffeeshop.data.roomDone.unit

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.PrimaryKey
import androidx.room.Query

@Entity(tableName = "units")
data class UnitDbEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String
)

@Dao
interface UnitDao {
    @Query("SELECT * FROM units")
    suspend fun getAllUnits(): List<UnitDbEntity>
}