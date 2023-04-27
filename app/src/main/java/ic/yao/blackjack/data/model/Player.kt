package ic.yao.blackjack.data.model
/**
 * Esta clase modela un jugador del juego de Blackjack.
 * */
class Player {
    private val hand = mutableListOf<Card>()

    fun getHand() : MutableList<Card> = hand

    fun getScore() : Int = hand.sumOf {
        if (it.isFaceUp()) {
            it.getCardValue()
        } else {
            0
        }
    }

    fun takeCard(card: Card) {
        hand.add(card)
    }

    fun takeTwoCards(card1: Card, card2: Card) {
        hand.add(card1)
        hand.add(card2)
    }

}