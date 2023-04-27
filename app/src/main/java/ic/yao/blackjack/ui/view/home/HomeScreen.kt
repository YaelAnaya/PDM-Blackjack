package ic.yao.blackjack.ui.view.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import ic.yao.blackjack.R
import ic.yao.blackjack.navigation.Screen
import ic.yao.blackjack.ui.theme.Poppins
import ic.yao.blackjack.ui.view.components.SettingsDialog
import ic.yao.blackjack.ui.view.home.HomeEvent
import ic.yao.blackjack.ui.view.home.HomeState
import ic.yao.blackjack.ui.view.home.HomeViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navController: NavController = rememberNavController()
) {
    val viewModel = hiltViewModel<HomeViewModel>()
    val uiState by viewModel.uiState.observeAsState()

    Scaffold(
        modifier = modifier,
        containerColor = MaterialTheme.colorScheme.background,
        floatingActionButton = {
            FilledIconButton(
                onClick = { viewModel.onEvent(HomeEvent.ShowSettingsModal) },
                colors = IconButtonDefaults.filledIconButtonColors(
                    containerColor = MaterialTheme.colorScheme.onSecondary,
                    contentColor = MaterialTheme.colorScheme.onPrimary
                ),
            ) {
                Icon(
                    tint = MaterialTheme.colorScheme.onPrimary,
                    imageVector = Icons.Filled.Settings,
                    contentDescription = "Settings"
                )
            }
        },
        content = { innerPadding ->
            uiState?.let {
                StartContent(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(innerPadding),
                    uiState = it,
                    navController = navController,
                    onEvent = viewModel::onEvent
                )
            }
        }
    )
}

@Composable
fun StartContent(
    modifier: Modifier,
    uiState: HomeState,
    navController: NavController,
    onEvent: (HomeEvent) -> Unit = { }
) {
    Box(modifier = modifier) {
        Column(
            modifier = modifier,
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            AnimatedVisibility(visible = uiState.showSettingsModal) {
                SettingsDialog(
                    countToWin = uiState.winnerPoints,
                    onConfirm = { onEvent(HomeEvent.ChangeWinnerPoints(it)) },
                    onDismiss = { onEvent(HomeEvent.DismissSettingsModal) }
                )
            }
            Image(
                painter = painterResource(id = R.drawable.logos_start_screen),
                contentDescription = "Blackjack cards Logo",
                modifier = Modifier
                    .size(215.dp)
                    .fillMaxWidth()
                    .padding(top = 54.dp)
                    .align(Alignment.CenterHorizontally)
            )
            Text(
                text = "WELCOME TO",
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                fontFamily = Poppins,
                fontWeight = FontWeight.Bold,
                fontSize = 22.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 9.dp)
                    .align(Alignment.CenterHorizontally)
            )
            Icon(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Blackjack Logo",
                tint = MaterialTheme.colorScheme.onSecondaryContainer,
                modifier = Modifier
                    .size(100.dp)
                    .fillMaxWidth()
                    .padding(top = 16.dp)
                    .align(Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.padding(vertical = 36.dp))
            Button(
                modifier = Modifier
                    .fillMaxWidth(0.50f)
                    .padding(horizontal = 16.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.onSecondary,
                    contentColor = MaterialTheme.colorScheme.onPrimary
                ),
                onClick = {
                    navController.navigate(
                        route = Screen.GameScreen.passArgs(uiState.winnerPoints)
                    )
                }
            ) {
                Text(
                    text = "PLAY",
                    style = MaterialTheme.typography.labelMedium,
                    textAlign = TextAlign.Center,
                )
            }
            Spacer(modifier = Modifier.padding(top = 16.dp))
            Button(
                modifier = Modifier
                    .fillMaxWidth(0.50f)
                    .padding(horizontal = 16.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.onSecondary,
                    contentColor = MaterialTheme.colorScheme.onPrimary
                ),
                onClick = { navController.navigate(Screen.RulesScreen.route) }
            ) {
                Text(
                    text = "INSTRUCTIONS",
                    style = MaterialTheme.typography.labelMedium,
                    textAlign = TextAlign.Center,
                )
            }
            Spacer(modifier = Modifier.padding(top = 16.dp))
            Button(
                modifier = Modifier
                    .fillMaxWidth(0.50f)
                    .padding(horizontal = 16.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.onSecondary,
                    contentColor = MaterialTheme.colorScheme.onPrimary
                ),
                onClick = { navController.navigate(Screen.ScoresScreen.route) }
            ) {
                Text(
                    text = "SCORES",
                    style = MaterialTheme.typography.labelMedium,
                    textAlign = TextAlign.Center,
                )
            }
        }
    }
}
