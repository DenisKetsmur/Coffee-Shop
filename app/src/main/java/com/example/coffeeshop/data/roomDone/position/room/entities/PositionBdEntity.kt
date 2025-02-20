package com.example.coffeeshop.data.roomDone.position.room.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.coffeeshop.data.roomDone.position.entities.Position

@Entity(tableName = "positions")
data class PositionEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val position: String
) {
    fun toEnum(): Position = Position.entries.find { it.name == position } ?: Position.CLIENT

    companion object {
        fun fromEnum(position: Position): PositionEntity {
            return PositionEntity(position = position.name)
        }
    }
}