package kopycinski.tomasz.klamkify.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kopycinski.tomasz.domain.usecase.FormatLongAsTimeStringUseCase

@Composable
fun Timer(
    secondsToShow: Long,
    isRunning: Boolean = false,
    onStartTimer: () -> Unit = {},
    onStopTimer: () -> Unit = {},
    timeFormatter: FormatLongAsTimeStringUseCase = FormatLongAsTimeStringUseCase()
) {
    val borderColor = MaterialTheme.colorScheme.surfaceVariant

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(275.dp)
    ) {
        Canvas(
            modifier = Modifier
                .size(258.dp)
                .align(Alignment.TopCenter)
        ) {
            drawCircle(
                color = borderColor,
                radius = 125.dp.toPx(),
                style = Stroke(width = 4.dp.toPx())
            )
        }
        Text(
            text = timeFormatter(secondsToShow),
            modifier = Modifier.align(Alignment.Center)
        )
        Surface(
            modifier = Modifier
                .width(75.dp)
                .align(Alignment.BottomCenter)
        ) {
            if (!isRunning) {
                IconButton(onClick = onStartTimer) {
                    Icon(imageVector = Icons.Filled.PlayArrow, contentDescription = "")
                }
            } else {
                IconButton(onClick = onStopTimer) {
                    Icon(imageVector = Icons.Filled.Stop, contentDescription = "")
                }
            }
        }
    }
}

@Preview
@Composable
fun TimerPreview() {
    Timer(100L)
}