package com.hari.notes.domain.repository

import com.hari.notes.data.model.Note
import kotlinx.coroutines.flow.Flow

interface NoteRepo {
    fun getNotes(): Flow<List<Note>>
    suspend fun getNote(id: Int): Note
    suspend fun insertNote(note: Note): Boolean
    suspend fun deleteNote(id: Int): Boolean
}