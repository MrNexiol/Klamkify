package kopycinski.tomasz.klamkify.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import kopycinski.tomasz.domain.usecase.FormatLongAsTimeStringUseCase
import kopycinski.tomasz.klamkify.R
import kopycinski.tomasz.klamkify.data.entity.Category

@Composable
fun CategoryItem(
    category: Category,
    currentTime: Long = 0,
    onStart: () -> Unit = {},
    onStop: () -> Unit = {},
    onClick: () -> Unit = {},
    isActive: Boolean = false,
    disabled: Boolean = false,
    timeFormatter: FormatLongAsTimeStringUseCase
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .padding(horizontal = 8.dp)
            .clickable { onClick() },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = category.name, modifier = Modifier.weight(1f))

        if (!disabled) {
            IconButton(
                onClick = onStart,
                Modifier.border(BorderStroke(5.dp, Color.Black))
            ) {
                Icon(
                    imageVector = Icons.Filled.PlayArrow,
                    contentDescription = stringResource(id = R.string.start_timer, category.name)
                )
            }
        }

        if (isActive) {
            IconButton(
                onClick = onStop,
                Modifier
                    .padding(end = 8.dp)
                    .border(BorderStroke(5.dp, Color.Black))
            ) {
                Icon(
                    imageVector = Icons.Filled.Stop,
                    contentDescription = stringResource(id = R.string.stop_timer)
                )
            }
            Text(text = timeFormatter(currentTime))
        }
    }
}