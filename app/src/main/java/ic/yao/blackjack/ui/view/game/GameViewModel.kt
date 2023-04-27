package ic.yao.blackjack.ui.view.game

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ic.yao.blackjack.data.database.entities.PlayerScoreEntity
import ic.yao.blackjack.data.model.Blackjack
import ic.yao.blackjack.data.model.Card
import ic.yao.blackjack.domain.use_cases.AddPlayerScoreUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

/**
 * Este ViewModel representa la lógica de la pantalla de juego.
 * */
@HiltViewModel
class GameViewModel @Inject constructor(
    private val addPlayerScore: AddPlayerScoreUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _uiState = MutableLiveData<UiState>()
    val uiState: LiveData<UiState> = _uiState

    /**
     * Se inicializa el estado de la pantalla.
     * Se crea un nuevo juego de Blackjack.
     * Se obtiene el puntaje del ganador de la pantalla anterior.
     * */
    init {
        _uiState.value = UiState()
        _uiState.value!!.blackjack.startGame()
        update()
        savedStateHandle.get<String>("winnerPoints")?.let {
            setWinnerPoints(it.toInt())
        }
    }
    /**
     * Esta función le da una carta al jugador.
     * Actualiza el estado de la UI, verifica si el juego terminó
     * y guarda el puntaje en la DB.
     * */
    private fun hit() {
        if (!_uiState.value!!.blackjack.isGameOver()) {
            _uiState.value!!.blackjack.hit()
            update()
            saveToDatabase()
        }
    }
    /**
     * Mediante esta función se guarda el puntaje del ganador
     * dentro de la DB. Esto solo si el juego terminó.
     * */
    private fun saveToDatabase() {
        if (_uiState.value!!.blackjack.isGameOver()) {
            viewModelScope.launch(Dispatchers.IO) {
                // Se utiliza el caso de uso para guardar el puntaje del ganador.
                addPlayerScore(
                    PlayerScoreEntity(
                        score =
                        if (_uiState.value!!.isPlayerWinner) _uiState.value!!.playerScore
                        else _uiState.value!!.dealerScore,
                        date = LocalDateTime
                            .now()
                            .format(
                                DateTimeFormatter
                                    .ofPattern("yyyy-MM-dd HH:mm")
                            ),
                        winnerName =
                        if (_uiState.value!!.isPlayerWinner) "Player"
                        else "Croupier",
                        loserName =
                        if (_uiState.value!!.isPlayerWinner) "Croupier"
                        else "Player",
                        winnerHand =
                        if (_uiState.value!!.isPlayerWinner) _uiState.value!!.playerHand
                        else _uiState.value!!.dealerHand
                    )
                )
            }
        }
    }
    /**
     * Esta función hace que el croupier juegue.
     * Actualiza el estado de la UI, verifica si el juego terminó
     * y guarda el puntaje en la DB.
     * */
    private fun stand() {
        if (!_uiState.value!!.blackjack.isGameOver()) {
            _uiState.value!!.blackjack.stand()
            update()
            saveToDatabase()
        }

    }

    private fun takeTwo() {
        if (!_uiState.value!!.blackjack.isGameOver()) {
            _uiState.value!!.blackjack.takeTwo()
            update()
            return
        }
    }

    private fun reset() {
        _uiState.value = _uiState.value?.copy(
            blackjack = Blackjack(countToWin = _uiState.value!!.pointsToWin)
        )
        _uiState.value!!.blackjack.startGame()
        update()
    }

    /**
     * Esta función actualiza el estado de la UI.
     * */
    private fun update() {
        _uiState.value = _uiState.value?.copy(
            playerHand = _uiState.value!!.blackjack.getPlayerHand(),
            dealerHand = _uiState.value!!.blackjack.getCroupierHand(),
            playerScore = _uiState.value!!.blackjack.getPlayerScore(),
            dealerScore = _uiState.value!!.blackjack.getCroupierScore(),
            isGameOver = _uiState.value!!.blackjack.isGameOver(),
            isPlayerWinner = _uiState.value!!.blackjack.isPlayerWinner(),
            isTie = _uiState.value!!.blackjack.isTie()
        )
    }

    private fun setWinnerPoints(winnerPoints: Int) {
        _uiState.value = _uiState.value?.copy(
            blackjack = _uiState.value!!.blackjack.changeCountToWin(winnerPoints),
            pointsToWin = winnerPoints
        )
    }
    /**
     * Esta función se encarga de manejar las acciones de
     * la pantalla de juego.
     * */
    fun onAction(action: Action) {
        when (action) {
            is Action.Hit -> hit()
            is Action.Stand -> stand()
            is Action.TakeTwo -> takeTwo()
            is Action.Reset -> reset()
        }
    }
    /**
     * Esta data class representa el estado de la UI.
     * */
    data class UiState(
        val playerHand: MutableList<Card> = mutableListOf(),
        val dealerHand: MutableList<Card> = mutableListOf(),
        val playerScore: Int = 0,
        val dealerScore: Int = 0,
        val isGameOver: Boolean = false,
        val isPlayerWinner: Boolean = false,
        val isTie: Boolean = false,
        val blackjack: Blackjack = Blackjack(),
        val pointsToWin: Int = 21
    )
    /**
     * Esta sealed class representa las acciones
     * de la pantalla de juego.
     * */
    sealed class Action {
        object Hit : Action()
        object Stand : Action()
        object TakeTwo : Action()
        object Reset : Action()
    }
}
