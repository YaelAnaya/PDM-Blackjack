package ic.yao.blackjack.domain.use_cases

import ic.yao.blackjack.data.database.entities.PlayerScoreEntity
import ic.yao.blackjack.data.repository.PlayerScoreRepository
import javax.inject.Inject

class RemoveAllUseCase @Inject constructor(
    private val repository: PlayerScoreRepository
) {
    suspend operator fun invoke() {
        repository.deleteAll()
    }
}