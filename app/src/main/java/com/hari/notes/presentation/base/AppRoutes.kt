package com.hari.notes.presentation.base

import com.hari.notes.data.model.Note

sealed interface AppRoutes {
    object DashboardRoute
    data class NoteDetailScreen(val note: Note)
}