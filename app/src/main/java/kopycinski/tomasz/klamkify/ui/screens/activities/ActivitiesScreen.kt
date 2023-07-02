package kopycinski.tomasz.klamkify.ui.screens.activities

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import kopycinski.tomasz.domain.model.Activity
import kopycinski.tomasz.domain.model.Category
import kopycinski.tomasz.klamkify.R
import kopycinski.tomasz.klamkify.ui.components.CategoryItem
import kopycinski.tomasz.klamkify.ui.components.NewActivityItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ActivitiesScreen(
    onFabClick: () -> Unit,
    onActivityClick: (Long) -> Unit,
    onActivityLongClick: (Long) -> Unit,
    onCategoryLongClick: (Long) -> Unit,
    viewModel: ActivitiesViewModel = hiltViewModel()
) {
    val uiState = viewModel.uiState.value

    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = stringResource(id = R.string.activities)) })
        },
        floatingActionButton = {
            FloatingActionButton(onClick = onFabClick) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = stringResource(id = R.string.add_activity)
                )
            }
        }
    ) { paddingValues ->
        ActivitiesContent(
            modifier = Modifier.padding(paddingValues),
            categoryMap = uiState.categories,
            onActivityClick = onActivityClick,
            onActivityLongClick = onActivityLongClick,
            onCategoryClick = { viewModel.toggleCategory(it) },
            onCategoryLongClick = onCategoryLongClick,
            extendedCategory = uiState.extendedCategory
        )
    }
}

@Composable
fun ActivitiesContent(
    modifier: Modifier = Modifier,
    categoryMap: Map<Category, List<Activity>>,
    onActivityClick: (Long) -> Unit,
    onActivityLongClick: (Long) -> Unit,
    onCategoryClick: (Long) -> Unit,
    onCategoryLongClick: (Long) -> Unit,
    extendedCategory: Long
) {
    LazyColumn(modifier = modifier) {
        for (entry in categoryMap) {
            item {
                CategoryItem(
                    categoryName = entry.key.name,
                    onClick = { onCategoryClick(entry.key.categoryId) },
                    onLongClick = { onCategoryLongClick(entry.key.categoryId) }
                )
            }
            if (entry.key.categoryId == extendedCategory) {
                items(entry.value) {
                    NewActivityItem(
                        activityName = it.name,
                        onClick = { onActivityClick(it.activityId) },
                        onLongClick = { onActivityLongClick(it.activityId) }
                    )
                }
            }
        }
    }
}