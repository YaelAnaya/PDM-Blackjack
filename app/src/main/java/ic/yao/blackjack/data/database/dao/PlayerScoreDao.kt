package ic.yao.blackjack.data.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Upsert
import ic.yao.blackjack.data.database.entities.PlayerScoreEntity
import kotlinx.coroutines.flow.Flow
/**
 * Esta clase modela un DAO de puntajes de jugadores.
 * */
@Dao
interface PlayerScoreDao {
    @Query("SELECT * FROM player_score_table ORDER BY id ASC")
    fun getAllPlayerScores(): Flow<List<PlayerScoreEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addPlayerScore(playerScore: PlayerScoreEntity)

    @Query("DELETE FROM player_score_table")
    suspend fun deleteAllPlayerScores()
}