package com.polat.ergun.coroutinesroom.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(val username: String,
                @ColumnInfo(name = "password_hash")
                val passwordHash: Int,
                val info: String) {
    @PrimaryKey
    var id: Long = 0
}