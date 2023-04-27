
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Badge
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import ic.yao.blackjack.data.database.entities.PlayerScoreEntity
import ic.yao.blackjack.ui.theme.Poppins
import ic.yao.blackjack.ui.view.components.ScoreCard
import ic.yao.blackjack.ui.view.components.TopBar
import ic.yao.blackjack.ui.viewmodel.ScoresViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScoresScreen(
) {
    val viewModel = hiltViewModel<ScoresViewModel>()
    val scores = viewModel.playersScore.value.scores
    Scaffold (
        modifier = Modifier.fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.background,
    ) { innerPadding ->
        ScoreContent(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            scores = scores
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScoreContent(
    modifier: Modifier = Modifier,
    scores : List<PlayerScoreEntity>,
) {
    Column (
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ){

        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(bottom  = 26.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            item {
                TopBar()
                Badge(
                    modifier = Modifier
                        .padding(bottom = 26.dp)
                        .clip(MaterialTheme.shapes.extraLarge),
                    contentColor = Color(0xFFE9F2FF),
                    containerColor = Color(0xFF367CFF),
                    content = {
                        Text(
                            modifier = Modifier.padding(horizontal = 16.dp),
                            text = "HIGHEST SCORES",
                            fontFamily = Poppins,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                        )
                    }
                )
            }
            items(scores.size) { index ->
                ScoreCard(score = scores[index])
            }

        }
    }
}

