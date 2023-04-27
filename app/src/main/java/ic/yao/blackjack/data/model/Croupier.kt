package ic.yao.blackjack.data.model
/**
 * Esta clase modela el croupier del juego de Blackjack.
 * */
class Croupier {
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
        card2.flipCard()
        hand.add(card1)
        hand.add(card2)
    }

    fun showSecondCard() = hand[1].flipCard()

}