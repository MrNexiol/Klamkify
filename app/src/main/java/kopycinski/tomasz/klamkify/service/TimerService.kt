package kopycinski.tomasz.klamkify.service

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.graphics.drawable.Icon
import android.os.Handler
import android.os.IBinder
import android.os.Looper
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import dagger.hilt.android.AndroidEntryPoint
import kopycinski.tomasz.domain.usecase.FormatLongAsTimeStringUseCase
import kopycinski.tomasz.klamkify.MainActivity
import kopycinski.tomasz.klamkify.R
import kopycinski.tomasz.klamkify.data.entity.Session
import kopycinski.tomasz.klamkify.data.repository.SessionRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@AndroidEntryPoint
class TimerService : Service() {

    @Inject lateinit var sessionRepository: SessionRepository
    val timeFormatter = FormatLongAsTimeStringUseCase()

    private var isRunning: Boolean = false
    private var startTime: Long = 0
    private var elapsedTime: Long = 0
    private var categoryName: String = ""
    private var categoryId: Long = -1
    private val handler = Handler(Looper.getMainLooper())
    private val notificationManager by lazy {
        getSystemService(NOTIFICATION_SERVICE) as NotificationManager
    }

    private lateinit var broadcastManager: LocalBroadcastManager
    private lateinit var notificationBuilder: Notification.Builder

    override fun onBind(p0: Intent?): IBinder? = null

    override fun onCreate() {
        super.onCreate()
        createNotificationChannel()
        broadcastManager = LocalBroadcastManager.getInstance(this)
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        when(intent.action) {
            ACTION_START_SERVICE -> {
                extractCategoryDataFromIntent(intent)
                createNotification(categoryName)
                startTimer()
            }
            ACTION_STOP_SERVICE -> stopSelf()
        }

        return START_NOT_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        handler.removeCallbacks(timerRunnable)
        saveSession()
    }

    private fun saveSession() {
        CoroutineScope(Dispatchers.IO).launch {
            sessionRepository.insert(
                Session(elapsedTime.toInt(),categoryId, LocalDate.now())
            )
        }
    }

    private fun createNotification(categoryName: String) {
        val action = Notification.Action.Builder(
            Icon.createWithResource(this, R.drawable.ic_launcher_foreground),
            getString(R.string.end_session),
            stopNotificationIntent()
        ).build()

        notificationBuilder = Notification.Builder(this, NOTIFICATION_CHANNEL_ID)
            .setContentText(categoryName)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentIntent(createNotificationIntent())
            .setOnlyAlertOnce(true)
            .addAction(action)

        startForeground(NOTIFICATION_ID, notificationBuilder.build())
    }

    private fun extractCategoryDataFromIntent(intent: Intent) {
        categoryName = intent.getStringExtra(START_INTENT_NAME) ?: ""
        categoryId = intent.getLongExtra(START_INTENT_ID, -1)
    }

    private fun startTimer() {
        if (!isRunning) {
            isRunning = true
            startTime = System.currentTimeMillis()
            handler.postDelayed(timerRunnable, HANDLER_DELAY)
        }
    }

    private val timerRunnable = object : Runnable {
        override fun run() {
            elapsedTime = (System.currentTimeMillis() - startTime) / 1000
            notificationBuilder.setContentText(
                "$categoryName: ${timeFormatter(elapsedTime)}"
            )
            notificationManager.notify(NOTIFICATION_ID, notificationBuilder.build())
            broadcast()
            if (isRunning) {
                handler.postDelayed(this, HANDLER_DELAY)
            }
        }
    }

    private fun broadcast() {
        val intent = Intent(BROADCAST_ACTION)
        intent.putExtra(BROADCAST_TIME_EXTRA, elapsedTime)
        intent.putExtra(BROADCAST_RUNNING_EXTRA, isRunning)
        intent.putExtra(BROADCAST_ACTIVE_ID, categoryId)
        broadcastManager.sendBroadcast(intent)
    }

    private fun createNotificationChannel() {
        val name = getString(R.string.notification_channel_name)
        val descriptionText = getString(R.string.notification_channel_description)
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val channel = NotificationChannel(NOTIFICATION_CHANNEL_ID, name, importance).apply {
            description = descriptionText
        }
        notificationManager.createNotificationChannel(channel)
    }

    private fun createNotificationIntent(): PendingIntent {
        return Intent(this, MainActivity::class.java).let { notificationIntent ->
            PendingIntent.getActivity(this, 0, notificationIntent, PendingIntent.FLAG_IMMUTABLE)
        }
    }

    private fun stopNotificationIntent(): PendingIntent {
        return Intent(this, TimerService::class.java).let {
            it.action = ACTION_STOP_SERVICE
            PendingIntent.getService(this, 0, it, PendingIntent.FLAG_IMMUTABLE)
        }
    }

    companion object {
        private const val START_INTENT_NAME = "category_name"
        private const val START_INTENT_ID = "category_index"
        private const val HANDLER_DELAY = 1000L
        private const val NOTIFICATION_CHANNEL_ID = "klamkify_timer"
        private const val NOTIFICATION_ID = 2137
        private const val ACTION_STOP_SERVICE = "stop_service"
        private const val ACTION_START_SERVICE = "start_service"
        const val BROADCAST_ACTION = "timer_update"
        const val BROADCAST_TIME_EXTRA = "elapsed_time"
        const val BROADCAST_RUNNING_EXTRA = "is_running"
        const val BROADCAST_ACTIVE_ID = "active_id"

        fun start(context: Context, categoryName: String, categoryId: Long) {
            val intent = Intent(context, TimerService::class.java)
            intent.putExtra(START_INTENT_NAME, categoryName)
            intent.putExtra(START_INTENT_ID, categoryId)
            intent.action = ACTION_START_SERVICE
            context.startService(intent)
        }

        fun stop(context: Context) {
            val intent = Intent(context, TimerService::class.java)
            context.stopService(intent)
        }
    }
}