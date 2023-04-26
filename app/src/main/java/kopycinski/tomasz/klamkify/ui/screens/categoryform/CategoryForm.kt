package kopycinski.tomasz.klamkify.ui.screens.categoryform

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun CategoryForm(
    onSuccessSave: () -> Unit,
    categoryId: Long,
    viewModel: CategoryFormViewModel = hiltViewModel()
) {
    LaunchedEffect(false) {
        if (categoryId != -1L) {
            viewModel.getCategory(categoryId)
        }
    }

    Column(
        Modifier.padding(8.dp)
    ) {
        OutlinedTextField(
            modifier = Modifier
                .padding(bottom = 8.dp)
                .fillMaxWidth(),
            value = viewModel.categoryName.value,
            onValueChange = { viewModel.update(it) },
            label = { Text(text = "Category name") }
        )
        Button(onClick = { viewModel.save(onSuccessSave) }) {
            Text(text = "Save")
        }
    }
}