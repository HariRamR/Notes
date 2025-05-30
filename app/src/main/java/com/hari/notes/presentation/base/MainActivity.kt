package com.hari.notes.presentation.base

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.navigation3.runtime.entry
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay
import com.hari.notes.presentation.base.AppRoutes.DashboardRoute
import com.hari.notes.presentation.base.AppRoutes.NoteDetailScreen
import com.hari.notes.presentation.dashboard.composables.BuildDashboard
import com.hari.notes.presentation.note_detail.components.NoteText
import com.hari.notes.presentation.theme.NotesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            enableEdgeToEdge()
            NotesTheme {
                /*val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = DashboardRoute
                ) {
                    composable<DashboardRoute> {
                        BuildDashboard()
                    }
                }*/
                val backStack = remember { mutableStateListOf<Any>(DashboardRoute) }
                NavDisplay(
                    backStack = backStack,
                    onBack = {
                        backStack.removeLastOrNull()
                    },
                    entryProvider = entryProvider {
                        entry<DashboardRoute> {
                            BuildDashboard(
                                onNoteClicked = { note ->
                                    backStack.add(NoteDetailScreen(note))
                                }
                            )
                        }
                        entry<NoteDetailScreen> {key->
                            NoteText(key.note)
                        }
                    }
                )
            }
        }
    }
}