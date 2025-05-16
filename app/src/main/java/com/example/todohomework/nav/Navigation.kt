package com.example.todohomework.nav


import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.todohomework.screens.AddNoteScreen
import com.example.todohomework.screens.MainScreen
import com.example.todohomework.screens.NoteDetailScreen
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.todohomework.viewModels.NoteViewModel
import androidx.compose.runtime.*
import androidx.lifecycle.compose.collectAsStateWithLifecycle


@Composable
fun AppNavigation(
    navController: NavHostController,
    viewModel: NoteViewModel = viewModel()
) {
    val notes by viewModel.notes.collectAsStateWithLifecycle()

    NavHost(
        navController = navController,
        startDestination = "home"
    ) {
        composable(route = "home") {
            MainScreen(
                viewModel = viewModel,
                onAddNoteClick = {
                    navController.navigate("add_note")
                },
                onNoteClick = { note ->
                    navController.navigate("note_detail/${note.id}")
                }
            )
        }

        composable(route = "add_note") {
            AddNoteScreen(
                viewModel = viewModel,
                onNoteSaved = {
                    navController.popBackStack()
                }
            )
        }

        composable(route = "note_detail/{noteId}") { backStackEntry ->
            val noteIdParam = backStackEntry.arguments?.getString("noteId")
            val noteId = noteIdParam?.toIntOrNull()
            val selectedNote = notes.find { it.id == noteId }

            if (selectedNote != null) {
                NoteDetailScreen(
                    note = selectedNote,
                    onBack = { navController.popBackStack() }
                )
            }
        }
    }
}

