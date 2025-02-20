package com.example.coffeeshop.data.roomDone.clients.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.coffeeshop.data.roomDone.clients.room.entities.ClientDbEntity
import com.example.coffeeshop.data.roomDone.clients.room.entities.ClientSignInTuple
import com.example.coffeeshop.data.roomDone.clients.room.entities.ClientUpdateTuple
import kotlinx.coroutines.flow.Flow

@Dao
interface ClientDao {

    @Query("SELECT * FROM clients WHERE id = :id LIMIT 1")
    suspend fun findById(id: Long): ClientDbEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertClient(client: ClientDbEntity)

    @Update
    suspend fun updateClient(client: ClientDbEntity)

    @Delete
    suspend fun deleteClient(client: ClientDbEntity)

    @Query("SELECT * FROM clients WHERE email = :email LIMIT 1")
    suspend fun findByEmail(email: String): ClientDbEntity?

    @Query("SELECT * FROM clients")
    fun getAllClients(): Flow<List<ClientDbEntity>>
}


