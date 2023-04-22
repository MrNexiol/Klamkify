package kopycinski.tomasz.klamkify.ui.screens.categories

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import kopycinski.tomasz.klamkify.ui.components.CategoryItem

@Composable
fun CategoriesScreen(
    onCreateClick: () -> Unit,
    viewModel: CategoriesViewModel = hiltViewModel()
) {
    val categories by viewModel.categoryList
    val isTimerRunning by viewModel.isRunning
    val activeId by viewModel.activeCategoryID

    LaunchedEffect(Any()) {
        viewModel.update()
    }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = onCreateClick) {
                Icon(imageVector = Icons.Filled.Add, contentDescription = "Add")
            }
        }
    ) { paddingValues ->
        LazyColumn(modifier = Modifier.padding(paddingValues)) {
            itemsIndexed(categories) {index, category ->
                CategoryItem(
                    category = category,
                    currentTime = viewModel.currentTime.value,
                    onStart = { viewModel.onStart(index) },
                    onStop = { viewModel.onStop() },
                    isActive = index == activeId,
                    disabled = isTimerRunning
                )
            }
        }
    }
}