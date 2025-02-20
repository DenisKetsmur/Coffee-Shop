package com.example.coffeeshop.data.roomDone.clients

import android.database.sqlite.SQLiteConstraintException
import com.example.coffeeshop.data.AccountAlreadyExistsException
import com.example.coffeeshop.data.AuthException
import com.example.coffeeshop.data.EmptyFieldException
import com.example.coffeeshop.data.Field
import com.example.coffeeshop.data.roomDone.clients.entities.Client
import com.example.coffeeshop.data.roomDone.clients.entities.SignUpData
import com.example.coffeeshop.data.roomDone.clients.room.ClientDao
import com.example.coffeeshop.data.roomDone.clients.room.ClientsRepository
import com.example.coffeeshop.data.roomDone.clients.room.entities.ClientDbEntity
import com.example.coffeeshop.data.roomDone.clients.room.entities.ClientDbEntity.Companion.fromSignUpData
import com.example.coffeeshop.data.roomDone.clients.room.entities.ClientUpdateTuple
import com.example.coffeeshop.data.settings.AppSettings
import com.example.coffeeshop.utils.AsyncLoader
import com.example.coffeeshop.wrapSQLiteException
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*

class RoomClientsRepository(
    private val clientDao: ClientDao,
    private val appSettings: AppSettings,
    private val ioDispatcher: CoroutineDispatcher
) : ClientsRepository {

    private val currentClientIdFlow = AsyncLoader {
        MutableStateFlow(ClientId(appSettings.getCurrentEmployeeId()))
    }

    override suspend fun isSignedIn(): Boolean {
        delay(2000)
        return appSettings.getCurrentEmployeeId() != AppSettings.NO_ACCOUNT_ID
    }

    override suspend fun signIn(email: String, password: String) = wrapSQLiteException(ioDispatcher) {
        if (email.isBlank()) throw EmptyFieldException(Field.Email)
        if (password.isBlank()) throw EmptyFieldException(Field.Password)

        delay(1000)

        val clientId = findClientIdByEmailAndPassword(email, password)
        appSettings.setCurrentEmployeeId(clientId)
        currentClientIdFlow.get().value = ClientId(clientId)

        return@wrapSQLiteException
    }

    override suspend fun signUp(signUpData: SignUpData) = wrapSQLiteException(ioDispatcher) {
        signUpData.validate()
        delay(1000)
        createClient(signUpData)
    }

    override suspend fun logout() {
        appSettings.setCurrentEmployeeId(AppSettings.NO_ACCOUNT_ID)
        currentClientIdFlow.get().value = ClientId(AppSettings.NO_ACCOUNT_ID)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    override suspend fun getClient(): Flow<Client?> {
        return currentClientIdFlow.get()
            .flatMapLatest { clientId ->
                if (clientId.value == AppSettings.NO_ACCOUNT_ID) {
                    flowOf(null)
                } else {
                    getAccountById(clientId.value)
                }
            }
            .flowOn(ioDispatcher)
    }

    override suspend fun clientUpdate(
        newFirstName: String,
        newLastName: String,
        newPhone: String
    ) = wrapSQLiteException(ioDispatcher) {
        if (newFirstName.isBlank()) throw EmptyFieldException(Field.FirstName)
        if (newLastName.isBlank()) throw EmptyFieldException(Field.LastName)
        if (newPhone.isBlank()) throw EmptyFieldException(Field.Phone)

        delay(1000)

        val clientId = appSettings.getCurrentEmployeeId()
        if (clientId == AppSettings.NO_ACCOUNT_ID) throw AuthException()

        updateIntoForClientId(clientId, newFirstName,newLastName,newPhone)

        currentClientIdFlow.get().value = ClientId(clientId)
        return@wrapSQLiteException
    }

    override fun getAllClients(): Flow<List<Client>> {
        return clientDao.getAllClients()
            .map { list -> list.map { it.toClient() } }
            .flowOn(ioDispatcher)
    }

    private suspend fun findClientIdByEmailAndPassword(email: String, password: String): Long {
        val tuple = clientDao.findByEmail(email) ?: throw AuthException()
        if (tuple.password != password) throw AuthException()
        return tuple.id
    }

    private suspend fun createClient(signUpData: SignUpData) {
        try {
            val entity = fromSignUpData(signUpData)
            clientDao.createClient(entity)
        } catch (e: SQLiteConstraintException) {
            throw AccountAlreadyExistsException().apply { initCause(e) }
        }
    }


    private fun getAccountById(clientId: Long): Flow<Client?> {
        return clientDao.getById(clientId).map { it?.toClient() }
    }

    private suspend fun updateIntoForClientId(
        accountId: Long,
        newFirstName: String,
        newLastName: String,
        newPhone: String
    ) {
        clientDao.clientUpdateTuple(ClientUpdateTuple(accountId,newFirstName,newLastName,newPhone))
    }

    private class ClientId(val value: Long)

}