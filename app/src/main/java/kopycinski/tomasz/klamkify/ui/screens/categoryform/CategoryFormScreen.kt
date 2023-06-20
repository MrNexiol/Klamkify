package kopycinski.tomasz.klamkify.ui.screens.categoryform

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun CategoryFormScreen(
    viewModel: CategoryFormViewModel = hiltViewModel()
) {
    var categoryName by remember { mutableStateOf("") }

    Column {
        TextField(value = categoryName, onValueChange = { categoryName = it })
        Button(onClick = { viewModel.createCategory(categoryName) }) {
            Text(text = "Submit")
        }
    }
}