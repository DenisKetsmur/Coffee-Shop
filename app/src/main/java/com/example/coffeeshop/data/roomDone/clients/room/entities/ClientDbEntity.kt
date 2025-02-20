package com.example.coffeeshop.data.roomDone.clients.room.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.example.coffeeshop.data.roomDone.clients.entities.Client
import com.example.coffeeshop.data.roomDone.clients.entities.SignUpData

@Entity(
    tableName = "clients",
    indices = [
        Index("email", unique = true)
    ]
)
data class ClientDbEntity(
    @PrimaryKey(autoGenerate = true) val id: Long,
    @ColumnInfo(name = "first_name") val firstName: String,
    @ColumnInfo(name = "last_name") val lastName: String,
    val email: String,
    val phone: String,
    val password:String,
    val position: Position = Position.CLIENT,
    @ColumnInfo(name = "created_at") val createdAt:Long,
){
    fun toClient(): Client = Client(
        id = id,
        firstName = firstName,
        lastName = lastName,
        email = email,
        phone = phone,
        password = password,
        createdAt = createdAt,
    )

    companion object {
        fun fromSignUpData(signUpData: SignUpData): ClientDbEntity = ClientDbEntity(
            id = 0,
            firstName = signUpData.firstName,
            lastName = signUpData.lastName,
            email = signUpData.email,
            phone = signUpData.phone,
            password = signUpData.password,
            createdAt = System.currentTimeMillis()
        )
    }
}

class UserPositionConverter {
    @TypeConverter
    fun fromUserPosition(position: Position): String = position.name

    @TypeConverter
    fun toUserPosition(value: String): Position = Position.valueOf(value)
}


enum class Position(val nameView:String) {
    ADMIN("Адміністратор"),
    MANAGER("Менеджер"),
    CLIENT("Клієнт")
}