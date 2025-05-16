package com.example.todohomework.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.todohomework.viewModels.NoteViewModel

@Composable
fun AddNoteScreen(
    onNoteSaved: () -> Unit,
    viewModel: NoteViewModel = viewModel()
) {
    var noteTitle by remember { mutableStateOf(TextFieldValue()) }
    var noteContent by remember { mutableStateOf(TextFieldValue()) }

    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            OutlinedTextField(
                value = noteTitle,
                onValueChange = { noteTitle = it },
                label = { Text("Title") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(12.dp))

            OutlinedTextField(
                value = noteContent,
                onValueChange = { noteContent = it },
                label = { Text("Content") },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(160.dp)
            )

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {
                    val titleText = noteTitle.text.trim()
                    val contentText = noteContent.text.trim()

                    if (titleText.isNotEmpty() && contentText.isNotEmpty()) {
                        viewModel.addNote(titleText, contentText)
                        onNoteSaved()
                    }
                },
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text("Save Note")
            }
        }
    }
}
