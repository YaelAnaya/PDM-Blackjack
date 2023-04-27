package ic.yao.blackjack.ui.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ic.yao.blackjack.data.database.entities.PlayerScoreEntity
import ic.yao.blackjack.domain.use_cases.GetPlayersScoreUseCase
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

/**
 * Mediante este ViewModel se obtienen los puntajes de los jugadores
 * desde la DB y se guardan en el estado de la UI.
 * */
@HiltViewModel
class ScoresViewModel @Inject constructor(
    getPlayersScoreUseCase: GetPlayersScoreUseCase
) : ViewModel() {
    private val _playersScore = mutableStateOf(ScoresState())
    val playersScore: State<ScoresState> = _playersScore

    init {
        getPlayersScoreUseCase().onEach { score ->
            _playersScore.value = _playersScore.value.copy(
                scores = score
            )
        }.launchIn(viewModelScope)
    }
}

data class ScoresState(
    val scores: List<PlayerScoreEntity> = emptyList()
)
