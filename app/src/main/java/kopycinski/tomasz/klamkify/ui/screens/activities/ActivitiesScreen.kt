package kopycinski.tomasz.klamkify.ui.screens.activities

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import kopycinski.tomasz.domain.model.Category

@Composable
fun ActivitiesScreen(
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    viewModel: ActivitiesViewModel = hiltViewModel()
) {
    val uiState = viewModel.uiState.value

    Scaffold(
        scaffoldState = scaffoldState,
        floatingActionButton = {
            FloatingActionButton(onClick = { viewModel.createTestCategory() }) {
                Icon(imageVector = Icons.Filled.Add, contentDescription = "")
            }
        }
    ) { paddingValues ->
        ActivitiesContent(
            modifier = Modifier.padding(paddingValues),
            categories = uiState.categories
        )
    }
}

@Composable
fun ActivitiesContent(
    modifier: Modifier = Modifier,
    categories: List<Category>
) {
    LazyColumn(modifier = modifier) {
        items(categories) {
            Text(text = it.name)
        }
    }
}