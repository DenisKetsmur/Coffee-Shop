package com.example.coffeeshop.data.roomDone.clients.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.coffeeshop.data.roomDone.clients.room.entities.ClientDbEntity
import com.example.coffeeshop.data.roomDone.clients.room.entities.ClientSignInTuple
import com.example.coffeeshop.data.roomDone.clients.room.entities.ClientUpdateTuple
import kotlinx.coroutines.flow.Flow

@Dao
interface ClientDao {

    @Query("SELECT id, password FROM clients WHERE email = :email ")
    suspend fun findByEmail(email: String): ClientSignInTuple?

    @Update(entity = ClientDbEntity::class)
    suspend fun clientUpdateTuple(employeeUpdateTuple: ClientUpdateTuple)

    @Insert
    suspend fun createClient(clientDbEntity: ClientDbEntity)

    @Query("SELECT * FROM clients WHERE id = :clientId ")
    fun getById(clientId: Long):Flow<ClientDbEntity?>

}



