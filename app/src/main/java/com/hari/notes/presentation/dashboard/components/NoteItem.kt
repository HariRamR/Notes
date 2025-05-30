package com.hari.notes.presentation.dashboard.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.hari.notes.R

@Composable
fun NoteItem(note: String, modifier: Modifier) {
    Text(
        note,
        modifier = modifier,
        style = TextStyle(
            color = colorResource(R.color.black),
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
        )
    )
}