package kopycinski.tomasz.klamkify.ui.screens.activitytimer

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import kopycinski.tomasz.domain.usecase.FormatLongAsTimeStringUseCase
import kopycinski.tomasz.klamkify.service.TimerService

@Composable
fun ActivityTimerScreen(
    onDetailsClick: (Long) -> Unit,
    viewModel: ActivityTimerViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState

    val context = LocalContext.current
    val timeFormatter = FormatLongAsTimeStringUseCase()
    var elapsedTime by remember { mutableStateOf(0L) }
    var isRunning by remember { mutableStateOf(false) }

    val broadcastReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent) {
            val newElapsedTime = intent.getLongExtra(TimerService.BROADCAST_TIME_EXTRA, 0L)
            val newIsRunning = intent.getBooleanExtra(TimerService.BROADCAST_RUNNING_EXTRA, false)
            elapsedTime = newElapsedTime
            isRunning = newIsRunning
        }
    }

    LocalBroadcastManager.getInstance(context).registerReceiver(
        broadcastReceiver, IntentFilter(TimerService.BROADCAST_ACTION)
    )

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
            Text(elapsedTime.toString())
            Button(onClick = {
                isRunning = true
                TimerService.start(
                    context,
                    categoryName = uiState.activityName,
                    activityId = uiState.activityId)
            }) {
                Text(text = "Start")
            }
            Button(onClick = {
                isRunning = false
                TimerService.stop(context)
            }) {
                Text(text = "Stop")
            }
        }
    }
}