package com.example.navigationmodule

data class User(
    val login:String,
    val password:String,
    val position:Position = Position.Guest
)

enum class Position{
    Administrator, Manager, Guest
}
