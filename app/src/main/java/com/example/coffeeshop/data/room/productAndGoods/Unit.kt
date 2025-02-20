package com.example.coffeeshop.data.room.productAndGoods

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.PrimaryKey
import androidx.room.Query

@Entity(tableName = "units")
data class Unit(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String
)

@Dao
interface UnitDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(unit: Unit)

    @Query("SELECT * FROM units WHERE id = :id")
    suspend fun getUnitById(id: Int): Unit?

    @Query("SELECT * FROM units")
    suspend fun getAllUnits(): List<Unit>

    @Delete
    suspend fun deleteUnit(unit: Unit)
}