package kopycinski.tomasz.klamkify.ui.screens.activitytimer

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import kopycinski.tomasz.klamkify.service.TimerService
import kopycinski.tomasz.klamkify.ui.components.Timer

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ActivityTimerScreen(
    onDetailsClick: (Long) -> Unit,
    viewModel: ActivityTimerViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState

    val context = LocalContext.current
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
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text(text = uiState.activityName) },
                actions = {
                    IconButton(onClick = { onDetailsClick(uiState.activityId) }) {
                        Icon(imageVector = Icons.Filled.Settings, contentDescription = "")
                    }
                }
            )
        }
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
        ) {
            Timer(
                secondsToShow = elapsedTime,
                isRunning = isRunning,
                onStartTimer = {
                    isRunning = true
                    TimerService.start(
                        context,
                        categoryName = uiState.activityName,
                        activityId = uiState.activityId)
                },
                onStopTimer = {
                    isRunning = false
                    TimerService.stop(context)
                }
            )
        }
    }
}