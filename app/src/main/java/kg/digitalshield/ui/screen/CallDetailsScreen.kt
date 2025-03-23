package kg.digitalshield.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kg.digitalshield.R
import kg.digitalshield.dto.CallDTO
import kg.digitalshield.dto.CallStatus
import kg.digitalshield.ui.theme.AppTheme
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun CallDetailsScreen(modifier: Modifier = Modifier, callId: Long) {

    val calls = listOf(
        CallDTO(id = 1, phoneNumber = "+996509091625", date = Date()),
        CallDTO(id = 2, phoneNumber = "Амир", date = Date()),
        CallDTO(id = 3, phoneNumber = "Жоомарт", date = Date()),
        CallDTO(id = 4, phoneNumber = "Рысгул Жене", date = Date()),
        CallDTO(
            id = 5,
            phoneNumber = "Адиль Байке",
            date = Date(),
            callStatus = CallStatus.SUSPICIOUS,
            suspiciousPhrases = listOf(
                "от ващего имени перевели деньги",
                "нужно срочно показать карту",
                "откройте мобильный банкинг",
                "откройте мобильный банкинг",
                "откройте мобильный банкинг",
                "откройте мобильный банкинг",
                "откройте мобильный банкинг",
                "откройте мобильный банкинг",
                "откройте мобильный банкинг",
                "откройте мобильный банкинг",
            )
        ),
        CallDTO(id = 6, phoneNumber = "Даниль Байке", date = Date()),
        CallDTO(id = 7, phoneNumber = "Садыр Жапаров", date = Date()),
        CallDTO(
            id = 8,
            phoneNumber = "Садыр Жапаров",
            date = Date(),
            callStatus = CallStatus.SUSPICIOUS
        ),
        CallDTO(
            id = 9,
            phoneNumber = "Садыр Жапаров",
            date = Date(),
            callStatus = CallStatus.BLOCKED
        ),
        CallDTO(
            id = 10,
            phoneNumber = "Садыр Жапаров",
            date = Date(),
            callStatus = CallStatus.BLOCKED
        ),
        CallDTO(id = 11, phoneNumber = "Садыр Жапаров", date = Date()),
        CallDTO(id = 12, phoneNumber = "Садыр Жапаров", date = Date()),
        CallDTO(id = 13, phoneNumber = "Садыр Жапаров", date = Date()),
        CallDTO(id = 14, phoneNumber = "Садыр Жапаров", date = Date()),
        CallDTO(id = 15, phoneNumber = "Садыр Жапаров", date = Date()),
        CallDTO(id = 16, phoneNumber = "Садыр Жапаров", date = Date()),
        CallDTO(id = 17, phoneNumber = "Садыр Жапаров", date = Date()),
        CallDTO(id = 18, phoneNumber = "Садыр Жапаров", date = Date()),
        CallDTO(id = 19, phoneNumber = "Садыр Жапаров", date = Date()),
        CallDTO(id = 20, phoneNumber = "Садыр Жапаров", date = Date()),
    )

    val callDTO = calls.filter { it.id == callId }[0]


    Column {
        Box(
            modifier = Modifier
                .weight(0.05f)
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = stringResource(id = R.string.call_details),
                textAlign = TextAlign.Center,
                fontSize = 26.sp
            )
        }

        Column(
            modifier = Modifier
                .weight(0.2f),
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = stringResource(id = R.string.number))
                Text(text = callDTO.phoneNumber)
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = stringResource(id = R.string.duration))
                Text(text = "13 мин")
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = stringResource(id = R.string.call_date))
                Text(text = formatDate(callDTO.date))
            }
        }

        Box(
            modifier = Modifier
                .weight(0.5f)
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.schield),
                contentDescription = stringResource(id = R.string.shield_image_description),
                alpha = 0.1f,
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .padding(20.dp)
                    .fillMaxSize()
            )

            Image(
                painter = painterResource(id = getImageIdBasedOnCallStatus(callDTO.callStatus)),
                contentDescription = null,
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .fillMaxSize()
            )


        }
        Box(
            modifier = Modifier.weight(0.05f),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = stringResource(id = getStringIdBasedOnCallStatus(callDTO.callStatus)),
                fontSize = 26.sp,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
            )

        }

        if (callDTO.callStatus == CallStatus.SUSPICIOUS) {
            Column(
                modifier = Modifier
                    .weight(0.2f)
                    .padding(8.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.suspicious_phrases),
                    fontSize = 20.sp
                )

                LazyColumn(
                ) {
                    items(callDTO.suspiciousPhrases) { phrase ->
                        Text(text = "$phrase,", color = Color(0xFFE5A000))
                    }

                }
            }
        }
    }
}

private fun formatDate(date: Date): String {
    val pattern = "d MMMM HH:mm"
    val format = SimpleDateFormat(pattern, Locale("ru", "RU"))

    return format.format(date)
}

private fun getImageIdBasedOnCallStatus(callStatus: CallStatus): Int {
    return when (callStatus) {
        CallStatus.SAFE -> R.drawable.safe
        CallStatus.BLOCKED -> R.drawable.block
        CallStatus.SUSPICIOUS -> R.drawable.suspicious
    }
}

private fun getStringIdBasedOnCallStatus(callStatus: CallStatus): Int {
    return when (callStatus) {
        CallStatus.SAFE -> R.string.safe_call
        CallStatus.BLOCKED -> R.string.blocked_call
        CallStatus.SUSPICIOUS -> R.string.suspicious_call
    }
}

@Preview(showSystemUi = true)
@Composable
fun CallDetailsScreenPreview() {
    AppTheme {
        CallDetailsScreen(
            callId = 6
        )
    }
}