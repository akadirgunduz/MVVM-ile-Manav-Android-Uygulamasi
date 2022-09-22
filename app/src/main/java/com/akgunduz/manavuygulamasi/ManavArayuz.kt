package com.akgunduz.manavuygulamasi

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ManavArayuz {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: ManavItems)

    @Delete
    suspend fun delete(item: ManavItems)

    @Query("SELECT * FROM manav_items")
    fun getAllManavItems() : LiveData<List<ManavItems>>
}