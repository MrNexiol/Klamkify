package kopycinski.tomasz.klamkify.ui.screens.categories

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun CategoriesScreen(
    viewModel: CategoriesViewModel = hiltViewModel()
) {
    val categories by viewModel.categoryList

    LazyColumn {
        items(categories) {
            Text(text = it.name)
        }
    }
}