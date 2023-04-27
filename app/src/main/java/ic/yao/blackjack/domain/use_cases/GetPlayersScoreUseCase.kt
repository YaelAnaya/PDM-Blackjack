package ic.yao.blackjack.domain.use_cases

import ic.yao.blackjack.data.database.entities.PlayerScoreEntity
import ic.yao.blackjack.data.repository.PlayerScoreRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
/**
 * Esta clase modela el caso de uso que obtiene los
 * puntajes de jugadores de la DB.
 * */
class GetPlayersScoreUseCase @Inject constructor(
    private val repository: PlayerScoreRepository
) {
     operator fun invoke() : Flow<List<PlayerScoreEntity>> {
         return repository.readAllData
     }
}