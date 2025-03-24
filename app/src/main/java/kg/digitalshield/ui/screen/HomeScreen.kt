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
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
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
fun HomeScreen(modifier: Modifier = Modifier, navController: NavController) {

    var phoneNumber = "+996 500 00 00 00"
    var searchQuery by remember { mutableStateOf("") }
    var searchActiveness by remember { mutableStateOf(false) }

    val calls = listOf(
        CallDTO(id = 1, phoneNumber = "+996509091625", date = Date()),
        CallDTO(id = 2, phoneNumber = "Амир", date = Date()),
        CallDTO(id = 3, phoneNumber = "Жоомарт", date = Date()),
        CallDTO(id = 4, phoneNumber = "Рысгул Жене", date = Date()),
        CallDTO(
            id = 5,
            phoneNumber = "Адиль Байке",
            date = Date(),
            callStatus = CallStatus.SUSPICIOUS
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

    val filteredCalls = calls.filter { it.phoneNumber.contains(searchQuery, ignoreCase = true) }

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
                    .fillMaxSize()
                    .padding(16.dp)
            )

            Column(
                modifier = Modifier.fillMaxHeight(),
                verticalArrangement = Arrangement.SpaceAround
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth(),
                    text = phoneNumber,
                    fontSize = 20.sp,
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

                    SearchBar(
                        query = searchQuery,
                        onQueryChange = { searchQuery = it },
                        onSearch = { searchActiveness = false },
                        active = false,
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
            CallsTable(
                calls = if (searchQuery.isNotEmpty()) filteredCalls else calls,
                navController = navController
            )
        }

    }
}

@Preview(showSystemUi = true)
@Composable
fun HomeScreenPreview() {
    AppTheme {
        HomeScreen(modifier = Modifier.fillMaxSize(), navController = rememberNavController())
    }
}

