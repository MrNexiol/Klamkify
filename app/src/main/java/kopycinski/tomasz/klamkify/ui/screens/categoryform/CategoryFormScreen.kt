package kopycinski.tomasz.klamkify.ui.screens.categoryform

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import kopycinski.tomasz.klamkify.R

@Composable
fun CategoryFormScreen(
    viewModel: CategoryFormViewModel = hiltViewModel()
) {
    val uiState = viewModel.uiState.value

    Scaffold { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(8.dp)
        ) {
            OutlinedTextField(value = uiState.categoryName, onValueChange = { viewModel.setCategoryName(it) })
            Button(onClick = { viewModel.saveCategory() }) {
                Text(text = stringResource(id = R.string.save))
            }
        }

    }
}