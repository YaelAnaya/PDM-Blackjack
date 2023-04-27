package ic.yao.blackjack

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import ic.yao.blackjack.data.database.PlayerScoresDatabase
import ic.yao.blackjack.data.repository.PlayerScoreRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
/**
 * Esta clase es la que se encarga de inicializar
 * la base de datos y el repositorio.
 * */
@HiltAndroidApp
class BlackjackApplication : Application()