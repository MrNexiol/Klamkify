package kopycinski.tomasz.klamkify.ui.screens.categorylist

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import kopycinski.tomasz.domain.usecase.FormatLongAsTimeStringUseCase
import kopycinski.tomasz.klamkify.R
import kopycinski.tomasz.klamkify.service.TimerService
import kopycinski.tomasz.klamkify.ui.components.ActivityItem

@Composable
fun ActivityList(
    onFabClick: () -> Unit,
    onItemClick: (Long) -> Unit,
    viewModel: ActivityListViewModel = hiltViewModel()
) {
    val activities by viewModel.activityList
    val context = LocalContext.current
    val timeFormatter = FormatLongAsTimeStringUseCase()
    var elapsedTime by remember { mutableStateOf(0L) }
    var isRunning by remember { mutableStateOf(false) }
    var activeActivity by remember { mutableStateOf(-1L) }

    val broadcastReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent) {
            val newElapsedTime = intent.getLongExtra(TimerService.BROADCAST_TIME_EXTRA, 0L)
            val newIsRunning = intent.getBooleanExtra(TimerService.BROADCAST_RUNNING_EXTRA, false)
            val newActiveCategory = intent.getLongExtra(TimerService.BROADCAST_ACTIVE_ID, -1)
            elapsedTime = newElapsedTime
            isRunning = newIsRunning
            activeActivity = newActiveCategory
        }
    }

    LocalBroadcastManager.getInstance(context).registerReceiver(
        broadcastReceiver, IntentFilter(TimerService.BROADCAST_ACTION)
    )

    LaunchedEffect(Any()) {
        viewModel.update()
    }

    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text(text = stringResource(id = R.string.app_name))
            })
        },
        floatingActionButton = {
            FloatingActionButton(onClick = onFabClick) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = stringResource(id = R.string.add_category)
                )
            }
        }
    ) { paddingValues ->
        LazyColumn(modifier = Modifier.padding(paddingValues)) {
            items(activities) { category ->
                ActivityItem(
                    activity = category,
                    currentTime = elapsedTime,
                    onStart = {
                        isRunning = true
                        activeActivity = category.activityId
                        TimerService.start(context, categoryName = category.name, categoryId = category.activityId)
                    },
                    onStop = {
                        isRunning = false
                        activeActivity = -1
                        TimerService.stop(context)
                    },
                    onClick = { onItemClick(category.activityId) },
                    isActive = category.activityId == activeActivity,
                    disabled = isRunning,
                    timeFormatter = timeFormatter
                )
            }
        }
    }
}