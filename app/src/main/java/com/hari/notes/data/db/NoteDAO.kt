package com.hari.notes.data.db

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.hari.notes.data.model.Note
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDAO {
    @Upsert
    suspend fun insertNote(note: Note): Long

    @Query("DELETE FROM Notes WHERE id = :id")
    suspend fun deleteNote(id: Int): Int

    @Query("SELECT * FROM Notes")
    suspend fun getNotes(): Flow<List<Note>>

    @Query("SELECT * FROM Notes WHERE id = :id")
    suspend fun getNote(id: Int): Note
}