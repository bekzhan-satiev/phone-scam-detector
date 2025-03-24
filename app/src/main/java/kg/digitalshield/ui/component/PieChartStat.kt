package kg.digitalshield.ui.component

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ir.ehsannarmani.compose_charts.PieChart
import ir.ehsannarmani.compose_charts.models.Pie
import kg.digitalshield.ui.theme.AppTheme

@Composable
fun PieChartStat() {
    var data by remember {
        mutableStateOf(
            listOf(
                Pie(label = "Android", data = 20.0, color = Color.Red),
                Pie(label = "Windows", data = 45.0, color = Color.Cyan),
                Pie(label = "Linux", data = 35.0, color = Color.Gray),
            )
        )
    }
    PieChart(
        modifier = Modifier.size(200. dp),
        data = data,
        onPieClick = {
            println("${it.label} Clicked")
            val pieIndex = data.indexOf(it)
            data = data.mapIndexed { mapIndex, pie -> pie.copy(selected = pieIndex == mapIndex) }
        },
        style = Pie.Style.Stroke(width = 30. dp)
    )
}

@Preview(showSystemUi = true)
@Composable
fun PieChartStatPreview() {
    AppTheme {
        PieChartStat()
    }
}