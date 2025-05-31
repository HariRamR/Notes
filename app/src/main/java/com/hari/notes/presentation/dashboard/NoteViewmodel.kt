package com.hari.notes.presentation.dashboard

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hari.notes.data.model.Note
import com.hari.notes.domain.repository.NoteRepo
import com.hari.notes.domain.state.NoteState
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class NoteViewmodel(
    private val noteRepo: NoteRepo
): ViewModel() {
    val userInput = mutableStateOf<String>("")
    val notesState = mutableStateOf<List<Note>>(listOf())
    val noteState = mutableStateOf<Note?>(null)
    private val _noteFlow = MutableSharedFlow<NoteState>()
    val noteFlow = _noteFlow.asSharedFlow()


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
            _noteFlow.emit(NoteState.NoteInsertion(result))
        }
    }

    fun deleteNote(id: Int) {
        viewModelScope.launch {
            val result = noteRepo.deleteNote(id)
            _noteFlow.emit(NoteState.NoteDeletion(result))
        }
    }
}
