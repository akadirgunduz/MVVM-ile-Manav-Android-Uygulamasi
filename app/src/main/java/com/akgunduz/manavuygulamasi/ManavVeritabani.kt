package com.akgunduz.manavuygulamasi

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [ManavItems::class],version = 1)
abstract class ManavVeritabani : RoomDatabase() {

    abstract fun getManavArayuz() : ManavArayuz

    companion object{
        @Volatile
        private var instance : ManavVeritabani? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK){
            instance ?: createDatabase(context).also {
                instance = it
            }
        }

    private fun createDatabase(context: Context)=
        Room.databaseBuilder(context.applicationContext,ManavVeritabani::class.java, "Manav.db").build()
}
}