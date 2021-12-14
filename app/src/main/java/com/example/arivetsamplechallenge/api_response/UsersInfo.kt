package com.example.arivetsamplechallenge.api_response

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.arivetsamplechallenge.utils.Constansts.Companion.USER_TABLE

@Entity(tableName = USER_TABLE)
data class UsersInfo(
    @PrimaryKey(autoGenerate = true)
    val myId:Long=0,
    val phone: String,
    val id: Id,
    val cell: String,
    val dob: Dob,
    val email: String,
    val gender: String,

    val location: Location,

    val name: Name,
    val nat: String,

    val picture: Picture,

)