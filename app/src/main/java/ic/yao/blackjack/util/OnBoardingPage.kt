package ic.yao.blackjack.util

import androidx.annotation.DrawableRes
/**
 * Data classa que modela una página
 * del onboarding que permite visializar
 * las reglas del juego.
 * */
data class OnBoardingPage(
    @DrawableRes
    val image : Int,
    val description: String
)
