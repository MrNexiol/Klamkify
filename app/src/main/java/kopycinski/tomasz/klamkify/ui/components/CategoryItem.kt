package kopycinski.tomasz.klamkify.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kopycinski.tomasz.klamkify.data.entity.Category
import kopycinski.tomasz.klamkify.usecase.FormatNumberAsTimeUseCase

@Composable
fun CategoryItem(
    category: Category,
    currentTime: Int = 0,
    onStart: () -> Unit = {},
    onStop: () -> Unit = {},
    onClick: () -> Unit = {},
    isActive: Boolean = false,
    disabled: Boolean = false
) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp).clickable { onClick() },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = category.name,
        modifier = Modifier.weight(1f))

        if (!disabled) {
            Button(onClick = onStart) {
                Text(text = "Start")
            }
        }

        if (isActive) {
            Button(onClick = onStop) {
                Text(text = "Stop")
            }
            Text(text = FormatNumberAsTimeUseCase.execute(currentTime))
        }
    }
}