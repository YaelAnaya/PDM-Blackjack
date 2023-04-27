package ic.yao.blackjack.navigation

import InstructionsScreen
import ScoresScreen
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import ic.yao.blackjack.ui.view.screens.GameScreen
import ic.yao.blackjack.ui.view.screens.HomeScreen

/**
 * Esta función composable permite navegar entre
 * las pantallas de la aplicación. Esto mediante la
 * librería de navegación de Jetpack Compose.
 * */
@Composable
fun AppNavigation(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Screen.StartScreen.route
    ) {
        composable(route = Screen.StartScreen.route) {
            HomeScreen(navController = navController)
        }
        composable(
            route = Screen.GameScreen.route,
            arguments = listOf(
                navArgument("winnerPoints") {
                    defaultValue = "21"
                    type = NavType.StringType
                }
            )
        ) {
            GameScreen()
        }
        composable(route = Screen.ScoresScreen.route) {
            ScoresScreen()
        }
        composable(route = Screen.RulesScreen.route) {
            InstructionsScreen(
                navController = navController,
            )
        }
    }
}