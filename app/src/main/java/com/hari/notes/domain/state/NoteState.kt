package com.hari.notes.domain.state

import com.hari.notes.data.model.Note

sealed interface NoteState {
    object Loading: NoteState
    data class Success(val notes: List<Note>): NoteState
    data class Error(val exception: Exception): NoteState
}