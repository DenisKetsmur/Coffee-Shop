package com.example.coffeeshop.data.roomDone.clients.room

import com.example.coffeeshop.data.roomDone.clients.entities.Client
import com.example.coffeeshop.data.roomDone.clients.entities.SignUpData
import com.example.coffeeshop.data.roomDone.clients.room.entities.ClientUpdateTuple
import kotlinx.coroutines.flow.Flow

interface ClientsRepository {

    suspend fun isSignedIn():Boolean

    suspend fun signIn(email:String, password:String)

    suspend fun signUp(signUpData: SignUpData)

    suspend fun logout()

    suspend fun getClient(): Flow<Client?>

    suspend fun clientUpdate(newFirstName: String, newLastName: String, newPhone: String)

}
