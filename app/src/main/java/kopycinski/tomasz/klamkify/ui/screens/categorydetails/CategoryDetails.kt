package kopycinski.tomasz.klamkify.ui.screens.categorydetails

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import kopycinski.tomasz.klamkify.usecase.FormatNumberAsTimeUseCase

@Composable
fun CategoryDetails(
    categoryId: Long,
    onBackPress: () -> Unit,
    viewModel: CategoryDetailsViewModel = hiltViewModel()
) {
    val category by viewModel.category
    val totalTime by viewModel.totalTime

    LaunchedEffect(Any()) {
        viewModel.refreshData(categoryId)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = category.name) },
                navigationIcon = {
                    IconButton(onClick = onBackPress) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "")
                    }
                }
            )
        }
    ) {
        Text(
            modifier = Modifier.padding(it),
            text = "Sum of time spent in this category: ${FormatNumberAsTimeUseCase.execute(totalTime)}"
        )
    }

}