package com.example.myapplication.repository

import com.example.myapplication.data.NoteDatabaseDao
import com.example.myapplication.model.Notes
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class NoteRepository @Inject constructor(private val noteDatabaseDao : NoteDatabaseDao) {

    suspend fun addNote(note: Notes)= noteDatabaseDao.insert(note)

    suspend fun update(note: Notes) = noteDatabaseDao.update(note)

    suspend fun deleteNote(note: Notes) = noteDatabaseDao.deleteNote(note)

    suspend fun deleteAllNotes(note: Notes) = noteDatabaseDao.deleteAll()

    fun getAllNotes() :Flow<List<Notes>> = noteDatabaseDao.getNotes().flowOn(Dispatchers.IO).conflate()



}