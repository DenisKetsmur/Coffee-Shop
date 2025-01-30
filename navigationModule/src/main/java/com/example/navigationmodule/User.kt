package com.example.navigationmodule

data class User(
    val login: String,
    val password: String,
    val position: Position = Position.Guest
)

enum class Position {
    Administrator, Manager, Guest
}

interface UserProvider {
    fun getCurrentUser(): User?
}

class AdministratorUserProvider(private val user: User) : UserProvider {
    override fun getCurrentUser(): User {
        return user
    }
}

class ManagerUserProvider(private val user: User) : UserProvider {
    override fun getCurrentUser(): User {
        return user
    }
}


