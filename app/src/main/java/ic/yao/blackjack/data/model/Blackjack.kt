package ic.yao.blackjack.data.model
/**
 * Clase que representa el juego de Blackjack y sus reglas básicas.
 * */
class Blackjack(
    private var countToWin: Int = 21
) {
    private val croupier = Croupier()
    private val player = Player()
    private val deck = Deck()
    private var isGameOver = false
    private var isPlayerWinner = false
    private var tie = false

    fun getCroupierHand(): MutableList<Card> = croupier.getHand()

    fun getPlayerHand(): MutableList<Card> = player.getHand()

    fun getCroupierScore(): Int = croupier.getScore()

    fun getPlayerScore(): Int = player.getScore()

    fun isGameOver(): Boolean = isGameOver

    fun isPlayerWinner(): Boolean = isPlayerWinner

    fun isTie(): Boolean = tie

    fun changeCountToWin(count: Int) : Blackjack {
        this.countToWin = count
        return this
    }

    fun startGame() {
        croupier.takeTwoCards(deck.getCard(), deck.getCard())
        player.takeTwoCards(deck.getCard(), deck.getCard())
    }

    fun hit() {
        val card = deck.getCard()
        if (card.getCardName() == "A") {
            card.setCardValue(aceValue(player.getScore()))
        }
        player.takeCard(card)
        verifyWinner()
    }
    /**
     * El croupier toma cartas hasta que su puntaje sea mayor o
     * igual a 17. Si el croupier tiene un puntaje mayor al del jugador y
     * menor o igual a 21, el croupier gana.
     * */
    fun stand() {
        croupier.showSecondCard()
        while (croupier.getScore() < countToWin ) {
            val card = deck.getCard()
            if (card.getCardName() == "A") {
                card.setCardValue(aceValue(croupier.getScore()))
            }
            if (croupier.getScore() > countToWin - 5 &&
                croupier.getScore() > player.getScore()) {
                break
            }
            croupier.takeCard(card)
        }
        verifyWinner()
    }

    fun takeTwo() {
        player.takeTwoCards(deck.getCard(), deck.getCard())
        verifyWinner()
    }

    private fun aceValue(playerScore : Int) : Int {
        return if (playerScore + 11 > countToWin) {
            1
        } else {
            11
        }
    }
    /**
     * Verifica si el jugador o el croupier ganaron o si hay un empate.
     * */
    private fun verifyWinner() {
        when {
            player.getScore() == croupier.getScore() -> {
                tie = true
                isGameOver = true
            }

            player.getScore() == countToWin -> {
                isPlayerWinner = true
                isGameOver = true
                croupier.showSecondCard()
            }

            croupier.getScore() == countToWin -> {
                isPlayerWinner = false
                isGameOver = true
            }

            player.getScore() > countToWin -> {
                isPlayerWinner = false
                isGameOver = true
                croupier.showSecondCard()
            }

            croupier.getScore() > countToWin  -> {
                isPlayerWinner = true
                isGameOver = true
            }
            croupier.getScore() > player.getScore() &&
                    croupier.getScore() < countToWin -> {
                isPlayerWinner = false
                isGameOver = true
            }
            else -> {
                isPlayerWinner = false
                isGameOver = false
            }
        }
    }
}