package kopycinski.tomasz.klamkify.ui.screens.activitytimer

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun ActivityTimerScreen(
    viewModel: ActivityTimerViewModel = hiltViewModel()
) {
    Scaffold {
        Column(
            modifier = Modifier.padding(it)
        ) {
            Text("Activity name")
            Text("0:00")
            Button(onClick = { /*TODO*/ }) {
                Text(text = "Start")
            }
        }
    }
}