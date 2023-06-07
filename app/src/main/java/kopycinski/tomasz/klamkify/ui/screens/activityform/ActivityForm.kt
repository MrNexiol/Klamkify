package kopycinski.tomasz.klamkify.ui.screens.activityform

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
fun ActivityForm(
    onSuccessSave: () -> Unit,
    activityId: Long,
    viewModel: ActivityFormViewModel = hiltViewModel()
) {
    LaunchedEffect(false) {
        if (activityId != -1L) {
            viewModel.getActivity(activityId)
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
                value = viewModel.activityName.value,
                onValueChange = { viewModel.update(it) },
                label = { Text(text = stringResource(id = R.string.name)) }
            )
            Button(onClick = { viewModel.save(onSuccessSave) }) {
                Text(text = stringResource(id = R.string.save))
            }
        }

    }
}