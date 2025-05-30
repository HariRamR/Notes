package com.hari.notes.data.repository

import com.hari.notes.data.db.NoteDAO
import com.hari.notes.data.model.Note
import com.hari.notes.domain.repository.NoteRepo
import kotlinx.coroutines.flow.Flow

class NoteRepoImpl(
    private val noteDAO: NoteDAO
): NoteRepo {
    override suspend fun getNotes(): Flow<List<Note>> {
        return noteDAO.getNotes()
    }

    override suspend fun getNote(id: Int): Note {
        return noteDAO.getNote(id)
    }

    override suspend fun insertNote(note: Note): Boolean {
        val result = noteDAO.insertNote(note)
        return result != -1L
    }

    override suspend fun deleteNote(id: Int): Boolean {
        val result = noteDAO.deleteNote(id)
        return result > 0
    }
}