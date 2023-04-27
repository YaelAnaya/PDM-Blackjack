package ic.yao.blackjack.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ic.yao.blackjack.data.database.PlayerScoresDatabase
import ic.yao.blackjack.data.database.dao.PlayerScoreDao
import ic.yao.blackjack.data.repository.PlayerScoreRepository
import javax.inject.Singleton
/**
 * Este módulo es utilizado por Hilt para proveer
 * instancias de dependencias a la aplicación.
 * */
@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun providePlayerScoresDb(
        app : Application
    ) = Room.databaseBuilder(
        app,
        PlayerScoresDatabase::class.java,
        "player_scores_database"
    ).build()

    @Provides
    fun providePlayerScoreDao(
        db : PlayerScoresDatabase
    ) = db.playerScoreDao()

    @Provides
    @Singleton
    fun providePlayerScoreRepository(
        dao: PlayerScoreDao
    ) = PlayerScoreRepository(dao)
}