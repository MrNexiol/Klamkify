package kopycinski.tomasz.klamkify.ui.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kopycinski.tomasz.klamkify.ui.theme.KlamkifyTheme

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CategoryItem(
    categoryName: String = "",
    onClick: () -> Unit = {},
    onLongClick: () -> Unit = {}
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 4.dp)
            .clip(CardDefaults.shape)
            .combinedClickable(
                onClick = onClick,
                onLongClick = onLongClick
            )
            .clip(CardDefaults.shape)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            Text(text = categoryName, modifier = Modifier.weight(1f))
            Icon(imageVector = Icons.Filled.PlayArrow, contentDescription = "")
        }
    }
}

@Preview
@Composable
fun CategoryItemPreview() {
    KlamkifyTheme {
        CategoryItem(categoryName = "Category")
    }
}