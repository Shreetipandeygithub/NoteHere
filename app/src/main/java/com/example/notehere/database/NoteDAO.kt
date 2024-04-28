package com.example.notehere.database

import android.provider.ContactsContract.Data
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.notehere.model.Note


@Dao
interface NoteDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note:Note)

    @Update
    suspend fun updateNote(note: Note)

    @Delete
    suspend fun deleteNote(note: Note)

    //
    @Query("SELECT * FROM NOTES ORDER BY id DESC")
    fun getAllNote():LiveData<List<Note>>


    //this function will search for the noteTitle and NoteBody
    @Query("SELECT * FROM NOTES WHERE noteTitle LIKE :query OR noteBody LIKE :query ")
    fun searchNote(query: String?):LiveData<List<Note>>


}