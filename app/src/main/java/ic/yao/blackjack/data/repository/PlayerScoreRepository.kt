package ic.yao.blackjack.data.repository

import ic.yao.blackjack.data.database.dao.PlayerScoreDao
import ic.yao.blackjack.data.database.entities.PlayerScoreEntity
import kotlinx.coroutines.flow.Flow
/**
 * Esta clase modela un repositorio de puntajes de jugadores.
 * El cual se comunica con la base de datos para obtener los puntajes de jugadores.
 *
 * @param playerScoreDao DAO de puntajes de jugadores.
 * @property readAllData Flujo de datos de todos los puntajes de jugadores.
 * */
class PlayerScoreRepository(private val playerScoreDao: PlayerScoreDao) {

    val readAllData: Flow<List<PlayerScoreEntity>> = playerScoreDao.getAllPlayerScores()

    suspend fun insert(score: PlayerScoreEntity) {
        playerScoreDao.addPlayerScore(score)
    }

    suspend fun deleteAll() {
        playerScoreDao.deleteAllPlayerScores()
    }
}