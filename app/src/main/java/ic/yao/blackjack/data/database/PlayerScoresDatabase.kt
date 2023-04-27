package ic.yao.blackjack.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ic.yao.blackjack.data.database.dao.PlayerScoreDao
import ic.yao.blackjack.data.database.entities.CardsTypeConverter
import ic.yao.blackjack.data.database.entities.PlayerScoreEntity
/**
 * Esta clase modela la base de datos de puntajes de jugadores.
 * */
@Database(
    entities = [PlayerScoreEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(CardsTypeConverter::class)
abstract class PlayerScoresDatabase : RoomDatabase() {
    abstract fun playerScoreDao() : PlayerScoreDao
}