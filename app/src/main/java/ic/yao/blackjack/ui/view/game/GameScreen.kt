package ic.yao.blackjack.ui.view.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Badge
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExposurePlus2
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import ic.yao.blackjack.R
import ic.yao.blackjack.ui.theme.Comfortaa
import ic.yao.blackjack.ui.theme.Poppins
import ic.yao.blackjack.ui.view.components.HandBar
import ic.yao.blackjack.ui.view.game.GameViewModel
import ic.yao.blackjack.ui.view.home.HomeEvent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameScreen() {
    val viewModel = hiltViewModel<GameViewModel>()
    val uiState by viewModel.uiState.observeAsState()
    Scaffold(
        topBar = {
            TopBar()
        },
        bottomBar = {
            BottomBar(
                uiState = uiState ?: GameViewModel.UiState(),
                onAction = viewModel::onAction
            )
        }
    ) { innerPadding ->
        AnimatedVisibility(
            visible = uiState!!.isGameOver,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            WinnerMessage(
                playerWins = uiState!!.isPlayerWinner ,
            )
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.9f)
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            BlackjackContent(
                uiState = uiState ?: GameViewModel.UiState()
            )
        }
    }
}

@Composable
fun BlackjackContent(
    uiState: GameViewModel.UiState = GameViewModel.UiState(),
) {
    Box(
        contentAlignment = Alignment.TopEnd,
    ) {
        HandBar(cards = uiState.dealerHand)
        ScoreBadge(score = uiState.dealerScore)
    }
    Spacer(modifier = Modifier.size(95.dp))
    Box(
        contentAlignment = Alignment.TopEnd,
    ) {
        HandBar(cards = uiState.playerHand)
        ScoreBadge(score = uiState.playerScore)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar() {
    CenterAlignedTopAppBar(
        modifier = Modifier.padding(vertical = 48.dp),
        title = {
            Icon(
                modifier = Modifier.size(60.dp),
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Blackjack Logo",
                tint = Color(0xFF367CFF),
            )
        },
        windowInsets = TopAppBarDefaults.windowInsets,
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.background
        )
    )
}

@Composable
fun ScoreBadge(
    modifier: Modifier = Modifier,
    score: Int = 10
) {
    Badge(
        modifier = modifier
            .padding(top = 4.dp)
            .size(width = 50.dp, height = 30.dp)
            .clip(MaterialTheme.shapes.extraLarge),
        backgroundColor = MaterialTheme.colorScheme.secondary,
        content = {
            Text(
                text = "$score",
                style = MaterialTheme.typography.bodySmall,
                fontWeight = FontWeight.Black,
                color = MaterialTheme.colorScheme.primary
            )
        }
    )
}

@Composable
fun BottomBar(
    uiState: GameViewModel.UiState = GameViewModel.UiState(),
    onAction: (GameViewModel.Action) -> Unit = {}
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 46.dp),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Button(
            onClick = { onAction(GameViewModel.Action.Hit) },
            colors = ButtonDefaults.buttonColors(
                contentColor = Color(0xFFDEE8F5),
                containerColor = Color(0xFF296BE7)
            ),
            contentPadding = PaddingValues(horizontal = 45.dp)
        ) {
            Text(
                fontFamily = Poppins,
                fontWeight = FontWeight.SemiBold,
                fontSize = 14.sp,
                text = "Hit"
            )
        }

        FilledIconButton(
            colors = IconButtonDefaults.filledIconButtonColors(
                contentColor = Color(0xFF296BE7),
                containerColor = MaterialTheme.colorScheme.onTertiary
            ),
            onClick = {
                if (uiState.isGameOver) {
                    onAction(GameViewModel.Action.Reset)
                } else {
                    onAction(GameViewModel.Action.TakeTwo)
                }
            }
        ) {
            AnimatedVisibility(
                visible = uiState.isGameOver,
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                Icon(
                    modifier = Modifier.size(20.dp),
                    imageVector = Icons.Default.Refresh,
                    contentDescription = "Reset",
                )
            }

            AnimatedVisibility(
                visible = (!uiState.isGameOver),
                enter = fadeIn(),
                exit = fadeOut()
            ) {

                Icon(
                    modifier = Modifier.size(20.dp),
                    imageVector = Icons.Default.ExposurePlus2,
                    contentDescription = "Plus 2 cards",
                )
            }

        }

        Button(
            onClick = { onAction(GameViewModel.Action.Stand) },
            colors = ButtonDefaults.buttonColors(
                contentColor = Color(0xFF296BE7),
                containerColor = MaterialTheme.colorScheme.onTertiary
            ),
            contentPadding = PaddingValues(horizontal = 45.dp)
        ) {
            Text(
                fontFamily = Poppins,
                fontWeight = FontWeight.SemiBold,
                fontSize = 14.sp,
                text = "Stand"
            )
        }
    }
}

@Composable
fun WinnerMessage(
    playerWins: Boolean = false,
    isTie : Boolean = false
) {
    val message = if (isTie) "TIE"
    else if (!playerWins) "CROUPIER WINS"
    else "PLAYER 1 WINS"

    Text(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 140.dp),
        text = message,
        style = MaterialTheme.typography.titleLarge,
        fontFamily = Comfortaa,
        fontWeight = FontWeight.Black,
        textAlign = TextAlign.Center,
        fontSize = 26.sp,
        color = MaterialTheme.colorScheme.surface
    )

}