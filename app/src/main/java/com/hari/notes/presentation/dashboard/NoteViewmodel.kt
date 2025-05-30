package com.hari.notes.presentation.dashboard

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hari.notes.data.model.Note
import com.hari.notes.domain.repository.NoteRepo
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class NoteViewmodel(
    private val noteRepo: NoteRepo
): ViewModel() {
    val notesState = mutableStateOf<List<Note>>(listOf())
        private set
    val noteState = mutableStateOf<Note?>(null)
        private set
    val noteInsertionState = mutableStateOf<Boolean?>(null)
        private set
    val noteDeletionState = mutableStateOf<Boolean?>(null)
        private set


    init {
        getNotes()
    }

    private fun getNotes() {
        viewModelScope.launch {
            noteRepo.getNotes()
                .collectLatest {
                    notesState.value = it
                }
        }
    }

    fun getNote(id: Int) {
        viewModelScope.launch {
            val note = noteRepo.getNote(id)
            noteState.value = note
        }
    }
    fun insertNote(note: Note) {
        viewModelScope.launch {
            val result = noteRepo.insertNote(note)
            noteInsertionState.value = result
        }
    }

    fun deleteNote(id: Int) {
        viewModelScope.launch {
            val result = noteRepo.deleteNote(id)
            noteInsertionState.value = result
        }
    }
}
