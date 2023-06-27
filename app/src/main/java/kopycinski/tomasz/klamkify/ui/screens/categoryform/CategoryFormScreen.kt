package kopycinski.tomasz.klamkify.ui.screens.categoryform

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import kopycinski.tomasz.klamkify.R

@Composable
fun CategoryFormScreen(
    viewModel: CategoryFormViewModel = hiltViewModel()
) {
    var categoryName by remember { mutableStateOf("") }

    Scaffold {
        Column(
            modifier = Modifier
                .padding(it)
                .padding(8.dp)
        ) {
            OutlinedTextField(value = categoryName, onValueChange = { categoryName = it })
            Button(onClick = { viewModel.createCategory(categoryName) }) {
                Text(text = stringResource(id = R.string.save))
            }
        }

    }
}