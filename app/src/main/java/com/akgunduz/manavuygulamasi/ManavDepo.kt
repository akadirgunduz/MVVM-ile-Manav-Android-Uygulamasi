package com.akgunduz.manavuygulamasi

class ManavDepo(private val db:ManavVeritabani) {

    suspend fun insert(items: ManavItems) = db.getManavArayuz().insert(items)
    suspend fun delete(items: ManavItems) = db.getManavArayuz().delete(items)

    fun getAllItems() = db.getManavArayuz().getAllManavItems()
}