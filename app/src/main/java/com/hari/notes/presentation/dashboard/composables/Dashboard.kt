package com.hari.notes.presentation.dashboard.composables

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hari.notes.R
import com.hari.notes.data.model.Note
import com.hari.notes.presentation.dashboard.NoteViewmodel
import com.hari.notes.presentation.dashboard.components.CustomTextField
import com.hari.notes.presentation.dashboard.components.NotesList
import org.koin.androidx.compose.koinViewModel

@Composable
fun BuildDashboard(viewmodel: NoteViewmodel = koinViewModel<NoteViewmodel>(), onNoteClicked: (Note) -> Unit) {
    val userInput = remember { mutableStateOf<String>("") }
    val localContext = LocalContext.current
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LaunchedEffect(viewmodel.noteInsertionState.value) {
            if (viewmodel.noteInsertionState.value == true) {
                Toast.makeText(localContext, "Note Added!", Toast.LENGTH_SHORT).show()
            } else if (viewmodel.noteInsertionState.value == false) {
                Toast.makeText(
                    localContext,
                    "Cannot add Note. Please try again later!",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
        LaunchedEffect(viewmodel.noteDeletionState.value) {
            if (viewmodel.noteDeletionState.value == true) {
                Toast.makeText(localContext, "Note Deleted!", Toast.LENGTH_SHORT).show()
            } else if (viewmodel.noteDeletionState.value == false) {
                Toast.makeText(
                    localContext,
                    "Cannot delete Note. Please try again later!",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
        LaunchedEffect(viewmodel.noteState.value) {
            if (viewmodel.noteState.value != null) {
                // Do something with the note
            }
        }
        CustomTextField(
            value = userInput.value,
            hint = "Enter your note here",
            modifier = Modifier.fillMaxWidth(),
            onValueChanged = { newInput ->
                userInput.value = newInput
            })
        ElevatedButton(
            onClick = {
                if(userInput.value.isEmpty()) {
                    Toast.makeText(
                        localContext,
                        "Write something to add!",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    viewmodel.insertNote(
                        Note(
                            note = userInput.value
                        )
                    )
                }
            },
            modifier = Modifier.background(
                color = colorResource(R.color.purple_500)
            )
        ) {
            Text(
                "Add +",
                style = TextStyle(
                    color = colorResource(R.color.white),
                    fontSize = 14.sp,
                ),
            )
        }
        NotesList(
            notes = viewmodel.notesState.value,
            modifier = Modifier,
            onNoteClicked = {note->
                onNoteClicked(note)
            }
        )
    }
}






