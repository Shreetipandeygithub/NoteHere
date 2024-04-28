package com.example.notehere.repository

import androidx.room.Query
import androidx.room.RoomDatabase
import com.example.notehere.database.NoteDatabase
import com.example.notehere.model.Note

class NoteRepository(private val db:NoteDatabase) {


    //creating function connected with NoteDAO's functions
    suspend fun insertNOTE(note: Note)=db.getNodeDao().insertNote(note)
    suspend fun deleteNOTE(note: Note)=db.getNodeDao().deleteNote(note)
    suspend fun updateNOTE(note: Note)=db.getNodeDao().updateNote(note)

    fun getAllNOTES()=db.getNodeDao().getAllNote()
    fun searchNOTES(query: String?)=db.getNodeDao().searchNote(query)



}