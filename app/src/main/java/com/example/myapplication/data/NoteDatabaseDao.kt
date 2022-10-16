package com.example.myapplication.data

import androidx.room.*
import com.example.myapplication.model.Notes
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDatabaseDao {

    @Query("SELECT *FROM Notes_Table")
    fun getNotes() : Flow<List<Notes>>

    @Query("SELECT *FROM Notes_Table WHERE Id=:id")
    suspend fun getNotesById(id :String) : List<Notes>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(note : Notes)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(note : Notes)

    @Query("DELETE from Notes_Table")
    suspend fun deleteAll()

    @Delete
    suspend fun deleteNote(note : Notes)
}
