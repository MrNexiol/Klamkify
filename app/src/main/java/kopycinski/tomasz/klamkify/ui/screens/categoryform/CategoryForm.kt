package kopycinski.tomasz.klamkify.ui.screens.categoryform

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import kopycinski.tomasz.klamkify.R

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

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(id = R.string.add_category)) }
            )
        }
    ) {paddingValues ->
        Column(
            Modifier
                .padding(paddingValues)
                .padding(8.dp)
        ) {
            OutlinedTextField(
                modifier = Modifier
                    .padding(bottom = 8.dp)
                    .fillMaxWidth(),
                value = viewModel.categoryName.value,
                onValueChange = { viewModel.update(it) },
                label = { Text(text = stringResource(id = R.string.name)) }
            )
            Button(onClick = { viewModel.save(onSuccessSave) }) {
                Text(text = stringResource(id = R.string.save))
            }
        }

    }
}