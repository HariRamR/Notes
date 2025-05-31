package com.hari.notes.presentation.note_detail.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hari.notes.R
import com.hari.notes.data.model.Note

@Composable
fun NoteText(note: Note) {
    Scaffold(modifier = Modifier.fillMaxSize()) {
        Text(
            note.note,
            style = TextStyle(
                color = colorResource(R.color.black),
                fontSize = 18.sp,
            ),
            modifier = Modifier.padding(it).padding(horizontal = 16.dp),
        )
    }
}