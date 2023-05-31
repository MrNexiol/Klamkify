package kopycinski.tomasz.klamkify.ui.screens.categorydetails

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import kopycinski.tomasz.domain.usecase.FormatLongAsTimeStringUseCase
import kopycinski.tomasz.klamkify.R

@Composable
fun CategoryDetails(
    categoryId: Long,
    onBackPress: () -> Unit,
    onEdit: (Long) -> Unit,
    onDelete: () -> Unit,
    viewModel: CategoryDetailsViewModel = hiltViewModel()
) {
    var showDialog by remember { mutableStateOf(false) }
    val timeFormatter = FormatLongAsTimeStringUseCase()
    val categoryName by viewModel.categoryName
    val totalTime by viewModel.totalTime
    val sessionList by viewModel.sessionsList

    LaunchedEffect(Any()) {
        viewModel.refreshData(categoryId)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = categoryName) },
                navigationIcon = {
                    IconButton(onClick = onBackPress) {
                        Icon(
                            Icons.Filled.ArrowBack,
                            contentDescription = stringResource(id = R.string.return_to_previous_screen)
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { onEdit(categoryId) }) {
                        Icon(
                            imageVector = Icons.Filled.Edit,
                            contentDescription = stringResource(id = R.string.edit_category)
                        )
                    }
                    IconButton(onClick = { showDialog = true }) {
                        Icon(
                            imageVector = Icons.Filled.Delete,
                            contentDescription = stringResource(id = R.string.archive_category)
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        LazyColumn(
            Modifier
                .padding(paddingValues)
                .padding(8.dp)
        ) {
            item {
                Text(
                    text = stringResource(
                        id = R.string.time_sum, timeFormatter(totalTime)
                    )
                )
            }
            item {
                Text(
                    text = stringResource(id = R.string.activity_log),
                    fontWeight = FontWeight.Bold
                )
            }
            items(sessionList) {
                Text(text = "${it.date} - ${timeFormatter(it.timeInSeconds)}")
            }
        }
        if (showDialog) {
            AlertDialog(
                onDismissRequest = { showDialog = false },
                title = { Text(text = stringResource(id = R.string.are_you_sure)) },
                text = { Text(text = stringResource(id = R.string.archive_warning)) },
                confirmButton = {
                    Button(onClick = {
                        viewModel.deleteCategory()
                        showDialog = false
                        onDelete()
                    }) {
                        Text(text = stringResource(id = R.string.sure))
                    }
                },
                dismissButton = {
                    Button(onClick = { showDialog = false }) {
                        Text(text = stringResource(id = R.string.cancel))
                    }
                }
            )
        }
    }
}