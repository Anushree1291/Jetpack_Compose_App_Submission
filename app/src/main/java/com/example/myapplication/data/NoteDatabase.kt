package com.example.myapplication.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.myapplication.model.Notes
import com.example.myapplication.utility.DateConverter
import com.example.myapplication.utility.UUIDconvertor

@Database(entities = [Notes::class] , version = 1 , exportSchema = false)
@TypeConverters(DateConverter::class, UUIDconvertor::class)
abstract class NoteDatabase : RoomDatabase() {

    abstract fun noteDao() : NoteDatabaseDao
}