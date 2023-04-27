package ic.yao.blackjack.domain.use_cases

import ic.yao.blackjack.data.database.entities.PlayerScoreEntity
import ic.yao.blackjack.data.repository.PlayerScoreRepository
import javax.inject.Inject

/**
 * Esta clase modela el caso de uso que agrega un
 * puntaje de jugador a la DB.
 * */
class AddPlayerScoreUseCase @Inject constructor(
    private val repository: PlayerScoreRepository
) {
    suspend operator fun invoke(score: PlayerScoreEntity) {
        repository.insert(score)
    }
}
