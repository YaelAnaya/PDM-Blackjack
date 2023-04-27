package ic.yao.blackjack.ui.view.components

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ic.yao.blackjack.data.model.Card

@Composable
fun HandBar(
    cards: List<Card> = listOf(),
) {
    LazyRow(
        modifier = Modifier.animateContentSize(animationSpec = tween()),
        contentPadding = PaddingValues(start = 18.dp, end = 18.dp),
        horizontalArrangement = Arrangement.spacedBy((-22).dp)
    ) {
        items(count = cards.size, key = { it }) { index ->
            PlayingCard(
                card = cards[index]
            )
        }
    }

}

