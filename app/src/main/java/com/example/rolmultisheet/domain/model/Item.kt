package com.example.rolmultisheet.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "item")
data class Item(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "item_id")
    val itemId: Long = 0,

    @ColumnInfo(name = "item_name")
    val itemName: String,

    @ColumnInfo(name = "item_price")
    val itemPrice: Int = 0,

    @ColumnInfo(name = "item_weight")
    val itemWight: Double = 0.0,

    @ColumnInfo(name = "item_description")
    val itemDescription: String? = null,
)