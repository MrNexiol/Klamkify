package kopycinski.tomasz.klamkify.ui.screens.activitytimer

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun ActivityTimerScreen(
    onDetailsClick: (Long) -> Unit,
    viewModel: ActivityTimerViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState

    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
        ) {
            Row(
                horizontalArrangement = Arrangement.End,
                modifier = Modifier.fillMaxWidth()
            ) {
                IconButton(onClick = { onDetailsClick(uiState.activityId) }) {
                    Icon(imageVector = Icons.Filled.Info, contentDescription = "")
                }
            }
            Text(text = uiState.activityName)
            Text("0:00")
            Button(onClick = { /*TODO*/ }) {
                Text(text = "Start")
            }
        }
    }
}