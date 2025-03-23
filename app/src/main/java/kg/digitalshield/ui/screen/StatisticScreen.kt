package kg.digitalshield.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import kg.digitalshield.ui.theme.AppTheme

@Composable
fun StatisticScreen(modifier: Modifier = Modifier) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Red),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Страница Статистики", color = Color.White)
    }
}

@Preview(showSystemUi = true)
@Composable
fun StatisticScreenPreview() {
    AppTheme {
        StatisticScreen()
    }
}