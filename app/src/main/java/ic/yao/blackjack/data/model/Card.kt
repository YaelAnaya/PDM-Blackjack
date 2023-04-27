package ic.yao.blackjack.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * Esta clase modela una carta perteneciente
 * a la Baja Inglesa.
 * */
@Parcelize
class Card (
    private val suit : String = "spades",
    private var value : Int = 1,
    private var isFaceUp : Boolean = true
) : Parcelable {
    fun getSuit() : String  = suit

    fun getCardName() : String {
        return when (value) {
            1 -> "A"
            11 -> "J"
            12 -> "Q"
            13 -> "K"
            else -> value.toString()
        }
    }

    fun setCardValue(value: Int) {
        this.value = value
    }

    fun getCardValue() : Int {
        return when (value) {
            in 11..13 -> 10
            else -> value
        }
    }

    fun isFaceUp() : Boolean = isFaceUp

    fun flipCard() {
        isFaceUp = !isFaceUp
    }
}
