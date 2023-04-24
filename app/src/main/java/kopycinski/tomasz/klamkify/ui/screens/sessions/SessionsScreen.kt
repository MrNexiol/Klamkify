package kopycinski.tomasz.klamkify.ui.screens.sessions

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import kopycinski.tomasz.klamkify.usecase.FormatNumberAsTimeUseCase

@Composable
fun SessionsScreen(
    categoryId: Long,
    viewModel: SessionsViewModel = hiltViewModel()
) {
    val totalTime by viewModel.totalTime

    LaunchedEffect(Any()) {
        viewModel.refreshData(categoryId)
    }

    Text(text = "Sum of time spent in this category: ${FormatNumberAsTimeUseCase.execute(totalTime)}")
}