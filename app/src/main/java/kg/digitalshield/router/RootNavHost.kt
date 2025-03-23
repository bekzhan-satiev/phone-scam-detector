package kg.digitalshield.router

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import kg.digitalshield.ui.screen.CallDetailsScreen
import kg.digitalshield.ui.screen.HomeScreen
import kg.digitalshield.ui.screen.LoginScreen

@Composable
fun RootNavHost(modifier: Modifier) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.Login.route) {
        composable(Screen.Login.route) { LoginScreen(navController = navController) }
        composable(Screen.Home.route) { HomeScreen(navController = navController) }
        composable(route = "call/{id}", arguments = listOf(
            navArgument("id") {
                type = NavType.LongType
                nullable = false
                defaultValue = 0
            }
        )) {
            it.arguments?.getLong("id")?.let {
                CallDetailsScreen(callId = it)
            }
        }
    }
}