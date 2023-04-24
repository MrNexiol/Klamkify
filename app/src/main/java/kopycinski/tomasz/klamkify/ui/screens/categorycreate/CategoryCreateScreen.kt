package kopycinski.tomasz.klamkify.ui.screens.categorycreate

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun CategoryCreateScreen(
    onSuccessSave: () -> Unit,
    viewModel: CategoryCreateViewModel = hiltViewModel()
) {
    Column {
        TextField(value = viewModel.categoryName.value, onValueChange = { viewModel.update(it) })
        Button(onClick = { viewModel.save(onSuccessSave) }) {
            Text(text = "Save")
        }
    }
}