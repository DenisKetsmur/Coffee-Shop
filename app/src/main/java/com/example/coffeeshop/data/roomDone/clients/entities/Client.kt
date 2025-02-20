package com.example.coffeeshop.data.roomDone.clients.entities

import com.example.coffeeshop.data.roomDone.clients.room.entities.Position

data class Client(
    val id:Long,
    val firstName:String,
    val lastName:String,
    val email:String,
    val phone:String,
    val password:String,
    val position: Position = Position.CLIENT,
    val createdAt:Long = UNKNOWN_CREATED_AR,
){
    companion object{
        const val UNKNOWN_CREATED_AR = 0L
    }
}
