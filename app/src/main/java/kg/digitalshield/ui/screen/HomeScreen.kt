package kg.digitalshield.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kg.digitalshield.R
import kg.digitalshield.dto.CallDTO
import kg.digitalshield.dto.CallStatus
import kg.digitalshield.ui.component.CallsTable
import kg.digitalshield.ui.component.LabelMarker
import kg.digitalshield.ui.component.TopRoundedColumn
import kg.digitalshield.ui.theme.AppTheme
import java.util.Date

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    var phoneNumber = "+996 500 00 00 00"

    var searchQuery by remember { mutableStateOf("") }
    var searchActiveness by remember { mutableStateOf(false) }

    val calls = listOf(
        CallDTO(phoneNumber = "+996509091625", date = Date()),
        CallDTO(phoneNumber = "Амир", date = Date()),
        CallDTO(phoneNumber = "Жоомарт", date = Date()),
        CallDTO(phoneNumber = "Рысгул Жене", date = Date()),
        CallDTO(phoneNumber = "Адиль Байке", date = Date(), callStatus = CallStatus.SUSPICIOUS),
        CallDTO(phoneNumber = "Даниль Байке", date = Date()),
        CallDTO(phoneNumber = "Садыр Жапаров", date = Date()),
        CallDTO(phoneNumber = "Садыр Жапаров", date = Date(), callStatus = CallStatus.SUSPICIOUS),
        CallDTO(phoneNumber = "Садыр Жапаров", date = Date(), callStatus = CallStatus.BLOCKED),
        CallDTO(phoneNumber = "Садыр Жапаров", date = Date(), callStatus = CallStatus.BLOCKED),
        CallDTO(phoneNumber = "Садыр Жапаров", date = Date()),
        CallDTO(phoneNumber = "Садыр Жапаров", date = Date()),
        CallDTO(phoneNumber = "Садыр Жапаров", date = Date()),
        CallDTO(phoneNumber = "Садыр Жапаров", date = Date()),
        CallDTO(phoneNumber = "Садыр Жапаров", date = Date()),
        CallDTO(phoneNumber = "Садыр Жапаров", date = Date()),
        CallDTO(phoneNumber = "Садыр Жапаров", date = Date()),
        CallDTO(phoneNumber = "Садыр Жапаров", date = Date()),
        CallDTO(phoneNumber = "Садыр Жапаров", date = Date()),
        CallDTO(phoneNumber = "Садыр Жапаров", date = Date()),
    )
    val filteredCalls = calls.filter { it.phoneNumber.contains(searchQuery, ignoreCase = true) }

    val titles = listOf("Номер", "Время")

    Column(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .weight(0.4f)
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {

            Image(
                painter = painterResource(id = R.drawable.schield),
                contentDescription = stringResource(id = R.string.shield_image_description),
                alpha = 0.3f,
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .padding(20.dp)
                    .fillMaxSize()
            )

            Column(
                modifier = Modifier.fillMaxHeight(),
                verticalArrangement = Arrangement.SpaceAround
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth(),
                    text = phoneNumber,
                    fontSize = 26.sp,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold
                )

                Column {
                    LabelMarker(
                        description = stringResource(id = R.string.safe),
                        color = Color.Black
                    )
                    LabelMarker(
                        description = stringResource(id = R.string.blocked),
                        color = Color.Red
                    )
                    LabelMarker(
                        description = stringResource(id = R.string.suspicious),
                        color = Color(0xFFE5A000)
                    )
                }

                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        text = stringResource(id = R.string.incoming_calls),
                        fontSize = 26.sp
                    )

                    SearchBar(
                        query = searchQuery,
                        onQueryChange = { searchQuery = it },
                        onSearch = { searchActiveness = false },
                        active = searchActiveness,
                        onActiveChange = { searchActiveness = it },
                        placeholder = { Text(text = stringResource(id = R.string.enter_phone_number)) },
                        trailingIcon = {
                            Icon(
                                imageVector = Icons.Rounded.Search,
                                contentDescription = stringResource(id = R.string.search_icon_description),
                                tint = MaterialTheme.colorScheme.onSurface
                            )
                        },
                    ) {
                        filteredCalls.forEach { item ->
                            Text(
                                text = item.phoneNumber,
                                fontSize = 15.sp
                            )
                        }
                    }
                }

            }

        }

        TopRoundedColumn(
            modifier = Modifier
                .weight(0.6f, fill = true)
                .background(MaterialTheme.colorScheme.onPrimary)
                .fillMaxSize()
        ) {
            CallsTable(calls = calls)
        }

    }
}

@Preview(showSystemUi = true)
@Composable
fun HomeScreenPreview() {
    AppTheme {
        HomeScreen(modifier = Modifier.fillMaxSize())
    }
}

