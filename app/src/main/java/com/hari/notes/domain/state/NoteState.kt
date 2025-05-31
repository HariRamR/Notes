package com.hari.notes.domain.state

sealed interface NoteState {
    data class NoteInsertion(val isInserted: Boolean): NoteState
    data class NoteDeletion(val isDeleted: Boolean): NoteState
}