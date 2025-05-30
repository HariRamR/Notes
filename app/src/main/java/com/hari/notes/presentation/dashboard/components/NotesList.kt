package com.hari.notes.presentation.dashboard.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.hari.notes.data.model.Note

@Composable
fun NotesList(notes: List<Note>, modifier: Modifier, onNoteClicked: (Note) -> Unit) {
    LazyColumn(
       modifier = modifier,
    ) {
        items(notes.size) {index->
            NoteItem(
                note = notes[index].note,
                modifier = Modifier.clickable {
                    onNoteClicked(notes[index])
                }
            )
        }
    }
}