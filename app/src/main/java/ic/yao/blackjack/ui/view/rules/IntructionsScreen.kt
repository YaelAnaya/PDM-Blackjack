
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.with
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import ic.yao.blackjack.R
import ic.yao.blackjack.navigation.Screen
import ic.yao.blackjack.ui.theme.Comfortaa
import ic.yao.blackjack.ui.view.components.TopBar
import ic.yao.blackjack.util.OnBoardingPage
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class, ExperimentalMaterial3Api::class)
@Composable
fun InstructionsScreen(
    navController: NavController,
) {
    val context = LocalContext.current
    val boardingPages: MutableList<OnBoardingPage> = mutableListOf()
    context.resources.getStringArray(R.array.rules).map {
        boardingPages.add(OnBoardingPage(R.drawable.vector, it))
    }
    val pagerState = rememberPagerState()
    Scaffold (
        modifier = Modifier.fillMaxSize(),
        topBar = { TopBar() },
        containerColor = MaterialTheme.colorScheme.background,
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
        ) {
            HorizontalPager(
                modifier = Modifier.weight(7.6f),
                count = boardingPages.size,
                state = pagerState,
                verticalAlignment = Alignment.Top,
            ) { position ->
                PagerScreen(onBoardingPage = boardingPages[position])
            }
            HorizontalPagerIndicator(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally),
                pagerState = pagerState,
                activeColor = Color(0xFF246CF3),
                inactiveColor = Color(0xFF98AED6).copy(alpha = 0.6f),
            )
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 1.dp, vertical = 35.dp)
                    .weight(2f),
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                SkipButton(pagerState = pagerState)
                NextButton(
                    modifier = Modifier.weight(1f),
                    pagerState = pagerState,
                    navController = navController,
                )
            }
        }
    }


}
@Composable
fun PagerScreen ( onBoardingPage: OnBoardingPage ) {
    Column (
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment =  Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Image(
            modifier = Modifier
                .size(245.dp)
                .fillMaxWidth(1f)
                .fillMaxHeight(1f),
            painter = painterResource(id = onBoardingPage.image),
            contentDescription = "Pager Imager content"
        )
        Text(
            text = onBoardingPage.description,
            color = MaterialTheme.colorScheme.surface,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 40.dp, vertical = 40.dp),
        )
    }
}

@OptIn(ExperimentalPagerApi::class, ExperimentalAnimationApi::class)
@Composable
fun NextButton(
    modifier: Modifier,
    pagerState: PagerState,
    navController: NavController,
) {
    val animationScope = rememberCoroutineScope()
    Box {
        Button(
            modifier = modifier
                .padding(horizontal = 40.dp),
            onClick = {
                animationScope.launch {
                    if (pagerState.currentPage < pagerState.pageCount - 1) {
                        pagerState.animateScrollToPage(
                            page = pagerState.currentPage + 1,
                            pageOffset = 0f
                        )
                    } else {
                        navController.navigate(Screen.StartScreen.route)
                    }
                }
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Transparent,
                contentColor = MaterialTheme.colorScheme.secondary
            )
        ) {
            AnimatedContent(
                targetState = pagerState.currentPage == pagerState.pageCount - 1,
                transitionSpec = { scaleIn() with scaleOut() }
            ){  isLastPage ->
                Text(
                    fontFamily = Comfortaa,
                    style = MaterialTheme.typography.bodyLarge,
                    text = if (isLastPage) "START" else "NEXT")
            }

        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun SkipButton(pagerState: PagerState) {
    val animationScope = rememberCoroutineScope()
    TextButton(
        modifier = Modifier
            .padding(horizontal = 40.dp),
        onClick = {
            animationScope.launch {
                if (pagerState.currentPage < pagerState.pageCount - 1) {
                    pagerState.animateScrollToPage(
                        page = pagerState.pageCount - 1,
                        pageOffset = 0f
                    )
                }
            }
        },
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent,
            contentColor = MaterialTheme.colorScheme.tertiary
        )
    ) {
        Text(
            fontFamily = Comfortaa,
            style = MaterialTheme.typography.bodyLarge,
            text = "SKIP")
    }
}