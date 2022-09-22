package com.akgunduz.manavuygulamasi

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "manav_items")
data class ManavItems (
    @ColumnInfo(name = "itemAd")
    var itemAd: String,

    @ColumnInfo(name = "itemAdet")
    var itemAdet: Int,

    @ColumnInfo(name = "itemFiyat")
    var itemFiyat: Int,
){
    @PrimaryKey(autoGenerate = true)
    var id:Int?=null
}