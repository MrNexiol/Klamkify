package kopycinski.tomasz.klamkify.ui.screens.activityform

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import kopycinski.tomasz.klamkify.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ActivityForm(
    onSuccessSave: () -> Unit,
    onAddCategory: () -> Unit,
    activityId: Long,
    viewModel: ActivityFormViewModel = hiltViewModel()
) {
    val uiState = viewModel.uiState.value

    var activityName by remember { mutableStateOf("") }

    var isDropdownExpanded by remember { mutableStateOf(false) }
    var dropdownValue by remember { mutableStateOf("") }

    Scaffold(topBar = {
        TopAppBar(title = { Text(text = stringResource(id = R.string.add_activity)) })
    }) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(8.dp)
        ) {
            OutlinedTextField(
                modifier = Modifier
                    .padding(bottom = 8.dp)
                    .fillMaxWidth(),
                value = activityName,
                onValueChange = { activityName = it },
                label = { Text(text = stringResource(id = R.string.name)) })
            Row(
                verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()
            ) {
                ExposedDropdownMenuBox(
                    expanded = isDropdownExpanded,
                    onExpandedChange = { isDropdownExpanded = !isDropdownExpanded },
                    modifier = Modifier.weight(1f)
                ) {
                    OutlinedTextField(
                        readOnly = true,
                        value = dropdownValue,
                        onValueChange = {},
                        trailingIcon = {
                            ExposedDropdownMenuDefaults.TrailingIcon(expanded = isDropdownExpanded)
                        },
                        label = { Text(text = stringResource(id = R.string.category)) },
                        modifier = Modifier.menuAnchor().fillMaxWidth()
                    )
                    ExposedDropdownMenu(expanded = isDropdownExpanded,
                        onDismissRequest = { isDropdownExpanded = false }) {
                        uiState.categoryList.forEach {
                            DropdownMenuItem(
                                onClick = {
                                    dropdownValue = it.name
                                    viewModel.setCurrentCategoryId(it.categoryId)
                                    isDropdownExpanded = false
                                },
                                text = { Text(text = it.name) }
                            )
                        }
                    }
                }
                IconButton(onClick = onAddCategory) {
                    Icon(
                        imageVector = Icons.Filled.Add,
                        contentDescription = stringResource(id = R.string.add_category)
                    )
                }
            }
            Button(onClick = {
                viewModel.save(activityName)
                onSuccessSave()
            }) {
                Text(text = stringResource(id = R.string.save))
            }
        }

    }
}