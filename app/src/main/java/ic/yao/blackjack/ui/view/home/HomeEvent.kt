package ic.yao.blackjack.ui.view.home
/**
 * Esta interface modela los eventos
 * presentes en la vista Principal.
 **/
sealed interface HomeEvent {
    object ShowSettingsModal : HomeEvent
    data class ChangeWinnerPoints(val points : String) : HomeEvent
    object DismissSettingsModal : HomeEvent
}
