package com.example.coffeeshop.data.roomDone.position.room.entities

import androidx.room.ColumnInfo

data class AccountSignInTuple(
    val id:Long,
    val password:String,
)

data class AccountUpdateTuple(
    val id:Long,
    @ColumnInfo(name = "first_name") val firstName: String,
    @ColumnInfo(name = "last_name") val lastName: String,
    val phone: String,
)