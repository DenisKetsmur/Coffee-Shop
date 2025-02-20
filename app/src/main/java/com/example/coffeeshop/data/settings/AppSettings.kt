package com.example.coffeeshop.data.settings

interface AppSettings {

    fun getCurrentEmployeeId(): Long

    fun setCurrentEmployeeId(accountId: Long)

    companion object {
        const val NO_ACCOUNT_ID = -1L
    }

}