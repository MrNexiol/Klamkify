package kopycinski.tomasz.klamkify.ui.screens.activitydetails

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ActivityDetails(
    activityId: Long,
    onEdit: (Long) -> Unit,
    onDelete: () -> Unit,
    viewModel: ActivityDetailsViewModel = hiltViewModel()
) {
    var showDialog by remember { mutableStateOf(false) }
    val timeFormatter = FormatLongAsTimeStringUseCase()
    val activityName by viewModel.activityName
    val totalTime by viewModel.totalTime
    val sessionList by viewModel.sessionsList

    LaunchedEffect(Any()) {
        viewModel.refreshData(activityId)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = activityName) },
                actions = {
                    IconButton(onClick = { onEdit(activityId) }) {
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
                        viewModel.archiveActivity()
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