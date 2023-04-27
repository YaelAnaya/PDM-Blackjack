package ic.yao.blackjack.data.model
/**
 * Esta clase modela una baraja inglesa, con la que se juega al Blackjack.
 * */
class Deck {
    private val deck = mutableListOf<Card>()
    private val suits = arrayOf("spades", "hearts", "diamonds", "clubs")

    init {
        createDeck()
        shuffleDeck()
    }

    private fun createDeck() {
        for (suit in suits) {
            for (value in 1..13) {
                deck.add(Card(suit, value))
            }
        }
        deck.shuffle()
    }

    fun getCard() : Card {
        return deck.last().also {
            deck.remove(it)
        }
    }

    private fun shuffleDeck() {
        deck.shuffle()
    }

}