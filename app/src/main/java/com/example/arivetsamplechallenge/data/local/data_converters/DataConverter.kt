package com.example.arivetsamplechallenge.data.local.data_converters

import androidx.room.TypeConverter
import com.example.arivetsamplechallenge.api_response.*
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class DataConverter {

    @TypeConverter
    fun fromResultList(value: List<UsersInfo>): String {
        val gson = Gson()
        val type = object : TypeToken<List<UsersInfo>>() {}.type
        return gson.toJson(value, type)
    }

    @TypeConverter
    fun toResultList(value: String): List<UsersInfo> {
        val gson = Gson()
        val type = object : TypeToken<List<UsersInfo>>() {}.type
        return gson.fromJson(value, type)
    }
    // dob
    @TypeConverter
    fun dobFromJson(jsonString: String) : Dob {
        val dobType = object : TypeToken<Dob>() { }.type
        return Gson().fromJson(jsonString, dobType)
    }

    @TypeConverter
    fun jsonFromDob(dob: Dob) : String = Gson().toJson(dob)
    // Id
    @TypeConverter
    fun idFromJson(jsonString: String) : Id {
        val idType = object : TypeToken<Id>() { }.type
        return Gson().fromJson(jsonString, idType)
    }

    @TypeConverter
    fun jsonFromId(id: Id) : String = Gson().toJson(id)

    // Location
    @TypeConverter
    fun locFromJson(jsonString: String) : Location {
        val locationType = object : TypeToken<Location>() { }.type
        return Gson().fromJson(jsonString, locationType)
    }

    @TypeConverter
    fun jsonFromLoc(location: Location) : String = Gson().toJson(location)

    // Picture
    @TypeConverter
    fun picFromJson(jsonString: String) : Picture {
        val pictureType = object : TypeToken<Picture>() { }.type
        return Gson().fromJson(jsonString, pictureType)
    }
    @TypeConverter
    fun jsonFromPic(picture: Picture) : String = Gson().toJson(picture)

    // Name
    @TypeConverter
    fun nameFromJson(jsonString: String) : Name {
        val nameType = object : TypeToken<Name>() { }.type
        return Gson().fromJson(jsonString, nameType)
    }
    @TypeConverter
    fun jsonFromName(name: Name) : String = Gson().toJson(name)
}