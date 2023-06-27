package kopycinski.tomasz.klamkify.ui.screens.activities

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import kopycinski.tomasz.domain.model.Activity
import kopycinski.tomasz.domain.model.Category

@Composable
fun ActivitiesScreen(
    onFabClick: () -> Unit,
    viewModel: ActivitiesViewModel = hiltViewModel()
) {
    val uiState = viewModel.uiState.value

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = onFabClick) {
                Icon(imageVector = Icons.Filled.Add, contentDescription = "")
            }
        }
    ) { paddingValues ->
        ActivitiesContent(
            modifier = Modifier.padding(paddingValues),
            categoryMap = uiState.categories
        )
    }
}

@Composable
fun ActivitiesContent(
    modifier: Modifier = Modifier,
    categoryMap: Map<Category, List<Activity>>
) {
    LazyColumn(modifier = modifier) {
        for (entry in categoryMap) {
            item {
                Text(text = entry.key.name)
            }
            items(entry.value) {
                Text(text = it.name)
            }
        }
    }
}