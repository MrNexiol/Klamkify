package kopycinski.tomasz.klamkify.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import kopycinski.tomasz.domain.usecase.FormatLongAsTimeStringUseCase
import java.time.LocalDate

@Composable
fun SessionItem(
    sessionTime: Long,
    sessionDate: LocalDate,
    timeFormatter: FormatLongAsTimeStringUseCase = FormatLongAsTimeStringUseCase()
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 4.dp)
            .clip(CardDefaults.shape)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            Text(text = timeFormatter(sessionTime))
            Text(text = sessionDate.toString())
        }
    }
}