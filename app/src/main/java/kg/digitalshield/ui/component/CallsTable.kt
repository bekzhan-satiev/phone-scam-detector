package kg.digitalshield.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import kg.digitalshield.R
import kg.digitalshield.dto.CallDTO
import kg.digitalshield.dto.CallStatus
import kg.digitalshield.ui.theme.AppTheme
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun CallsTable(
    modifier: Modifier = Modifier,
    calls: List<CallDTO>
) {
    var selectedStatus by remember { mutableStateOf(CallStatus.SAFE) }

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        TabRow(
            selectedTabIndex = when (selectedStatus) {
                CallStatus.SAFE -> 0
                CallStatus.BLOCKED -> 1
                CallStatus.SUSPICIOUS -> 2
            }
        ) {
            Tab(selected = selectedStatus == CallStatus.SAFE,
                onClick = { selectedStatus = CallStatus.SAFE },
                text = { Text(text = stringResource(id = R.string.all), fontSize = 10.sp) })
            Tab(selected = selectedStatus == CallStatus.BLOCKED,
                onClick = { selectedStatus = CallStatus.BLOCKED },
                text = { Text(text = stringResource(id = R.string.blocked), fontSize = 10.sp) })
            Tab(selected = selectedStatus == CallStatus.SUSPICIOUS,
                onClick = { selectedStatus = CallStatus.SUSPICIOUS },
                text = { Text(text = stringResource(id = R.string.suspicious), fontSize = 10.sp) })

        }

        val filteredCalls = filterCalls(calls, selectedStatus)

        LazyColumn {
            items(filteredCalls) { call ->
                Row(horizontalArrangement = Arrangement.SpaceBetween) {
                    TableCell(
                        text = call.phoneNumber,
                        weight = 1f,
                        textColor = getTextColorByCallStatus(call.callStatus)
                    )
                    TableCell(
                        text = formatDate(call.date),
                        weight = 1f,
                        textColor = getTextColorByCallStatus(call.callStatus),
                        textAlign = TextAlign.End
                    )
                }
                HorizontalDivider()
            }
        }
    }
}

private fun formatDate(date: Date): String {
    val pattern = "d MMMM HH:mm"
    val format = SimpleDateFormat(pattern, Locale("ru", "RU"))

    return format.format(date)
}

private fun filterCalls(calls: List<CallDTO>, callStatus: CallStatus): List<CallDTO> {
    return when (callStatus) {
        CallStatus.SAFE -> calls
        CallStatus.BLOCKED -> calls.filter { it.callStatus == CallStatus.BLOCKED }
        CallStatus.SUSPICIOUS -> calls.filter { it.callStatus == CallStatus.SUSPICIOUS }
    }

}

private fun getTextColorByCallStatus(callStatus: CallStatus): Color {
    return when (callStatus) {
        CallStatus.SAFE -> Color.Black
        CallStatus.BLOCKED -> Color.Red
        CallStatus.SUSPICIOUS -> Color(0xFFE5A000)
    }
}

@Preview(showSystemUi = true)
@Composable
fun CallsTablePreview() {
    val calls = listOf(
        CallDTO(phoneNumber = "+996509091625", date = Date()),
        CallDTO(phoneNumber = "Амир", date = Date(), callStatus = CallStatus.BLOCKED),
        CallDTO(phoneNumber = "Жоомарт", date = Date(), callStatus = CallStatus.SUSPICIOUS),
        CallDTO(phoneNumber = "Рысгул Жене", date = Date()),
        CallDTO(phoneNumber = "Адиль Байке", date = Date()),
        CallDTO(phoneNumber = "Даниль Байке", date = Date(), callStatus = CallStatus.SUSPICIOUS),
        CallDTO(phoneNumber = "Садыр Жапаров", date = Date(), callStatus = CallStatus.SUSPICIOUS),
    )

    AppTheme {
        CallsTable(
            modifier = Modifier
                .fillMaxSize(),
            calls = calls
        )
    }
}
