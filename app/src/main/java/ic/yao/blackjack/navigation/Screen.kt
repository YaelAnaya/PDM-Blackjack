package ic.yao.blackjack.navigation

import androidx.navigation.NavArgument
/**
 * Esta Sealed Class modela cada una de las pantallas
 * que a las que se puede navegar en la aplicación.
 *
 * @param route es la ruta de la vista.
 * */
sealed class Screen(val route : String) {
    object StartScreen : Screen("start")
    object GameScreen : Screen("game?winnerPoints={winnerPoints}") {
        fun passArgs(winnerPoints : String) : String {
            return "game?winnerPoints=$winnerPoints"
        }
    }
    object ScoresScreen : Screen("scores")
    object RulesScreen : Screen("rules")
}
