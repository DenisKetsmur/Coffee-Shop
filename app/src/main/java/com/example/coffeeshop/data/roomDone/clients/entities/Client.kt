package com.example.coffeeshop.data.roomDone.clients.entities

data class Client(
    val id:Long,
    val firstName:String,
    val lastName:String,
    val email:String,
    val phone:String,
    val createdAt:Long = UNKNOWN_CREATED_AR,
){
    companion object{
        const val UNKNOWN_CREATED_AR = 0L
    }
}
