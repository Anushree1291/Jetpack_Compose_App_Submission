package com.example.myapplication.screen

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapplication.model.Notes
import com.example.myapplication.repository.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(private val repository: NoteRepository): ViewModel() {


    private var note_List = MutableStateFlow<List<Notes>>(emptyList())
    val noteList = note_List.asStateFlow()

    init {
        viewModelScope.launch (Dispatchers.IO){
            repository.getAllNotes().distinctUntilChanged().collect{ it->
                if(it.isNullOrEmpty()){
                    Log.d("EmptyList"," List id Empty ")
                }
                else{
                    note_List.value =it
                }

            }
        }
    }

    fun addNote(note: Notes) = viewModelScope.launch { repository.addNote(note) }

    fun removeNote(note: Notes) = viewModelScope.launch { repository.deleteNote(note) }

    fun updateNote(note: Notes) = viewModelScope.launch { repository.update(note) }

}