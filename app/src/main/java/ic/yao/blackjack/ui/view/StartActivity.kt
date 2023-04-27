package ic.yao.blackjack.ui.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import ic.yao.blackjack.navigation.AppNavigation
import ic.yao.blackjack.ui.theme.BlackjackTheme
/**
 * Esta Activity actua como el punto de entrada para
 * inyeccion de dependencias de Hilt.
 * Se encarga de inicializar la navegación y de proveer el tema
 * de la aplicación.
 * */
@AndroidEntryPoint
class StartActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BlackjackTheme {
                AppNavigation(navController = rememberNavController())
            }
        }
    }
}

