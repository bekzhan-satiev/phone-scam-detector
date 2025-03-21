package kg.digitalshield.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import kg.digitalshield.ui.theme.AppTheme

@Composable
fun HomeScreen() {
    Column {
        Text("Hello world!")
    }
}

@Preview(showSystemUi = true)
@Composable()
fun HomeScreenPreview() {
    AppTheme {
        HomeScreen()
    }
}