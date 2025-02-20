package com.example.coffeeshop.data.roomDone.clients.room.entities

import androidx.room.ColumnInfo

data class ClientSignInTuple(
    val id:Long,
    val password:String,
)

data class ClientUpdateTuple(
    val id:Long,
    @ColumnInfo(name = "first_name") val firstName: String,
    @ColumnInfo(name = "last_name") val lastName: String,
    val phone: String,
)