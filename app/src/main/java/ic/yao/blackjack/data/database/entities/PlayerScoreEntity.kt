package ic.yao.blackjack.data.database.entities

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.google.gson.Gson
import ic.yao.blackjack.data.model.Card
import kotlinx.parcelize.Parcelize
/**
 * Esta clase representa un modelo/entidad de un puntaje de jugador.
 * */
@Entity(tableName = "player_score_table")
@Parcelize
data class PlayerScoreEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val score: Int,
    val date : String,
    @ColumnInfo(name = "winner_name")
    val winnerName: String,
    @ColumnInfo(name = "loser_name")
    val loserName: String,
    @ColumnInfo(name = "winner_hand")
    val winnerHand : List<Card>,

) : Parcelable

/**
 * Esta clase pertime guardar la lista de cartas ganadoras
 * del jugador en la base de datos. Esto mediante el uso de
 * la librería GSON, la cual permite convertir objetos a JSON
 * y viceversa.
 * */
class CardsTypeConverter {
    @TypeConverter
    fun listToJson(value: List<Card>?): String = Gson().toJson(value)
    @TypeConverter
    fun jsonToList(value: String) = Gson().fromJson(value, Array<Card>::class.java).toList()
}