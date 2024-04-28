package com.example.notehere.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.notehere.model.Note
import com.example.notehere.repository.NoteRepository
import kotlinx.coroutines.launch

class NoteViewModel(app:Application,
    private val noteRepository: NoteRepository):AndroidViewModel(app) {

    fun addNote(note: Note) =
        viewModelScope.launch {
            noteRepository.insertNOTE(note)
        }
    fun deleteNote(note: Note)=
        viewModelScope.launch {
            noteRepository.deleteNOTE(note)
        }
    fun updateNote(note: Note)=
        viewModelScope.launch {
            noteRepository.updateNOTE(note)
        }
    fun getAllNotes()=noteRepository.getAllNOTES()

    fun searchNote(query:String?)=
        noteRepository.searchNOTES(query)
}