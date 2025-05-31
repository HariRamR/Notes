package com.hari.notes.presentation.dashboard.composables

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hari.notes.R
import com.hari.notes.data.model.Note
import com.hari.notes.domain.state.NoteState
import com.hari.notes.presentation.dashboard.NoteViewmodel
import com.hari.notes.presentation.dashboard.components.CustomTextField
import com.hari.notes.presentation.dashboard.components.NotesList
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.compose.koinViewModel

@Composable
fun BuildDashboard(viewmodel: NoteViewmodel = koinViewModel<NoteViewmodel>(), onNoteClicked: (Note) -> Unit) {
    val localContext = LocalContext.current
    Scaffold(
        bottomBar = {
            Box(
                modifier = Modifier.padding(16.dp)
            ) {
                ElevatedButton(
                    onClick = {
                        if(viewmodel.userInput.value.isEmpty()) {
                            Toast.makeText(
                                localContext,
                                "Write something to add!",
                                Toast.LENGTH_SHORT
                            ).show()
                        } else {
                            viewmodel.insertNote(
                                Note(
                                    note = viewmodel.userInput.value
                                )
                            )
                        }
                    },
                    colors = ButtonColors(
                        containerColor = colorResource(R.color.purple_500),
                        disabledContainerColor = Color.Gray,
                        contentColor = colorResource(R.color.white),
                        disabledContentColor = colorResource(R.color.white),
                    ),
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(corner = CornerSize(5.dp)),
                ) {
                    Text(
                        "Add Note",
                        style = TextStyle(
                            color = colorResource(R.color.white),
                            fontSize = 14.sp,
                        ),
                    )
                }
            }
        }
    ) {
        Column(
            modifier = Modifier.fillMaxSize().padding(it).padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LaunchedEffect(Unit) {
                viewmodel.noteFlow.collectLatest {noteState->
                    when(noteState) {
                        is NoteState.NoteInsertion -> {
                            if (noteState.isInserted) {
                                viewmodel.userInput.value = ""
                                Toast.makeText(localContext, "Note Added!", Toast.LENGTH_SHORT).show()
                            } else {
                                Toast.makeText(
                                    localContext,
                                    "Cannot add Note. Please try again later!",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                        is NoteState.NoteDeletion -> {
                            if (noteState.isDeleted) {
                                Toast.makeText(localContext, "Note Deleted!", Toast.LENGTH_SHORT).show()
                            } else {
                                Toast.makeText(
                                    localContext,
                                    "Cannot delete Note. Please try again later!",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                    }
                }
            }
            CustomTextField(
                value = viewmodel.userInput.value,
                hint = "Enter your note here",
                modifier = Modifier.fillMaxWidth(),
                onValueChanged = { newInput ->
                    viewmodel.userInput.value = newInput
                })
            Spacer(
                modifier = Modifier.height(20.dp)
            )
            NotesList(
                notes = viewmodel.notesState.value,
                modifier = Modifier.fillMaxWidth(),
                onNoteClicked = {note->
                    onNoteClicked(note)
                }
            )
            Spacer(
                modifier = Modifier.height(40.dp)
            )
        }
    }
}






