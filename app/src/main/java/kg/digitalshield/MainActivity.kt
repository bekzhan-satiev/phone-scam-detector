package kg.digitalshield

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import kg.digitalshield.navigation.BottomNavBar
import kg.digitalshield.navigation.Screen
import kg.digitalshield.ui.screen.CallDetailsScreen
import kg.digitalshield.ui.screen.HomeScreen
import kg.digitalshield.ui.screen.LoginScreen
import kg.digitalshield.ui.screen.RegisterScreen
import kg.digitalshield.ui.screen.SearchScreen
import kg.digitalshield.ui.screen.StatisticScreen
import kg.digitalshield.ui.theme.AppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppTheme {
                val navController = rememberNavController()
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                var showBottomBar by rememberSaveable { mutableStateOf(true) }
                showBottomBar = when (navBackStackEntry?.destination?.route) {
                    Screen.Login.route -> false
                    Screen.Register.route -> false
                    else -> true
                }

                Scaffold(modifier = Modifier.fillMaxSize(),
                    bottomBar = {
                        if (showBottomBar) BottomNavBar(navController = navController)
                    }) { innerPadding ->
                    NavHost(
                        modifier = Modifier.padding(innerPadding),
                        navController = navController,
                        startDestination = Screen.Login.route
                    ) {
                        composable(route = Screen.Login.route) { LoginScreen(navController = navController) }
                        composable(route = Screen.Home.route) { HomeScreen(navController = navController) }
                        composable(route = Screen.Register.route) { RegisterScreen(navController = navController) }
                        composable(route = Screen.Statistic.route) { StatisticScreen() }
                        composable(route = Screen.Search.route) { SearchScreen() }
                        composable(
                            route = Screen.CallDetailed.route,
                            arguments = listOf(
                                navArgument("id") {
                                    type = NavType.LongType
                                    nullable = false
                                    defaultValue = 0
                                })
                        ) { backStackEntry ->
                            backStackEntry.arguments?.getLong("id")?.let { callId ->
                                CallDetailsScreen(callId = callId)
                            }
                        }
                    }
                }
            }
        }
    }

}