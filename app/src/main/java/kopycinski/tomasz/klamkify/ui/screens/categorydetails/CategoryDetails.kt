package kopycinski.tomasz.klamkify.ui.screens.categorydetails

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import kopycinski.tomasz.klamkify.usecase.FormatNumberAsTimeUseCase

@Composable
fun CategoryDetails(
    categoryId: Long,
    onBackPress: () -> Unit,
    onEdit: (Long) -> Unit,
    onDelete: () -> Unit,
    viewModel: CategoryDetailsViewModel = hiltViewModel()
) {
    var showDialog by remember { mutableStateOf(false) }
    val category by viewModel.category
    val totalTime by viewModel.totalTime

    LaunchedEffect(Any()) {
        viewModel.refreshData(categoryId)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = category.name) },
                navigationIcon = {
                    IconButton(onClick = onBackPress) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "")
                    }
                },
                actions = {
                    IconButton(onClick = { onEdit(categoryId) }) {
                        Icon(imageVector = Icons.Filled.Edit, contentDescription = "")
                    }
                    IconButton(onClick = { showDialog = true }) {
                        Icon(imageVector = Icons.Filled.Delete, contentDescription = "")
                    }
                }
            )
        }
    ) {
        Column {
            Text(
                modifier = Modifier.padding(it),
                text = "Sum of time spent in this category: ${FormatNumberAsTimeUseCase.execute(totalTime)}"
            )
            if (showDialog) {
                AlertDialog(
                    onDismissRequest = { showDialog = false },
                    title = { Text(text = "Are you sure?") },
                    text = { Text(text = "You're going to delete the category and all recorded times. This is irreversible.") },
                    confirmButton = {
                        Button(onClick = {
                            viewModel.deleteCategory()
                            showDialog = false
                            onDelete()
                        }) {
                            Text(text = "I'm sure")
                        }
                    },
                    dismissButton = {
                        Button(onClick = { showDialog = false }) {
                            Text(text = "Cancel")
                        }
                    }
                )
            }
        }
    }

}