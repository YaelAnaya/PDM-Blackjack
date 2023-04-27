package ic.yao.blackjack.ui.view.home

/**
 * Esta data class modela el
 * estado de la vista Principal.
 * */
data class HomeState(
    val showSettingsModal : Boolean = false,
    val winnerPoints : String = "21",
)
