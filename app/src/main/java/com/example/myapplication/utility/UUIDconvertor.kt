package com.example.myapplication.utility

import androidx.room.TypeConverter
import java.util.UUID

class UUIDconvertor {

    @TypeConverter
    fun fromUUID(uuid : UUID) : String? {
        return uuid.toString()
    }

    @TypeConverter
    fun uuidFromString(string :String?) : UUID? {
        return UUID.fromString(string)
    }
}