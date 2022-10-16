package com.example.myapplication.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.Instant
import java.util.*

@Entity(tableName = "Notes_Table")
data class Notes (
    @PrimaryKey
    val Id : UUID = UUID.randomUUID(),

    @ColumnInfo(name = "Note_Title")
    val Title : String,

    @ColumnInfo(name = "Note_Description")
    val Description : String,

    @ColumnInfo(name = "Note_Time")
    val DateTime : Date = Date.from(Instant.now())
)
