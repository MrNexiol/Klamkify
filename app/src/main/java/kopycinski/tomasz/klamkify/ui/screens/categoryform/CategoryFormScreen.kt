package kopycinski.tomasz.klamkify.ui.screens.categoryform

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import kopycinski.tomasz.klamkify.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryFormScreen(
    viewModel: CategoryFormViewModel = hiltViewModel()
) {
    val uiState = viewModel.uiState.value

    Scaffold(
        topBar = { TopAppBar(title = { Text(text = "Add category") }) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(8.dp)
        ) {
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = uiState.categoryName,
                onValueChange = { viewModel.setCategoryName(it) }
            )
            Button(onClick = { viewModel.saveCategory() }) {
                Text(text = stringResource(id = R.string.save))
            }
        }

    }
}