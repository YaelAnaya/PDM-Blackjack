package ic.yao.blackjack.ui.view.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.isUnspecified
import ic.yao.blackjack.R
import ic.yao.blackjack.data.model.Card

@Preview(
    showBackground = true,
    backgroundColor = 0x24292e,
    name = "Playing Card",
    device = Devices.PIXEL_3A,
)
@Composable fun PlayingCardPreview() {
    PlayingCard()
}

@Composable
fun PlayingCard(
    modifier: Modifier = Modifier,
    size: Int = 135,
    card : Card = Card(),
    borderColor : Color = MaterialTheme.colorScheme.background,
) {
    val cardBackground = if (card.isFaceUp()) {
        painterResource(id = R.drawable.card_background)
    } else {
        painterResource(id = R.drawable.card_face_down)
    }

    val suitIcon = when (card.getSuit()) {
        "hearts" -> painterResource(id = R.drawable.hearts_icon)
        "clubs" -> painterResource(id = R.drawable.clubs_icon)
        "diamonds" -> painterResource(id = R.drawable.diamonds_icon)
        else -> painterResource(id = R.drawable.spades_icon)
    }

    Box(
        modifier = modifier
            .size((size/2).dp, (size * 0.70).dp)
            .border(
                width = 3.dp,
                color = borderColor,
                shape = MaterialTheme.shapes.small,
            ),
        contentAlignment = Alignment.TopStart,
    ) {
        Image(
            painter = cardBackground,
            contentDescription = "Card background",
            contentScale = ContentScale.Inside,
        )
        AnimatedVisibility(
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.Center),
            visible = card.isFaceUp()) {
            Text(
                color = Color(0xFF141417),
                modifier = Modifier
                    .padding(start = 8.dp, top = 2.dp)
                    .align(Alignment.TopStart),
                text = card.getCardName(),
                style = MaterialTheme.typography.titleLarge,
            )
            Image(
                modifier = Modifier
                    .padding(top = 38.dp, bottom = 12.dp),
                painter = suitIcon,
                contentDescription = "Suit icon",
                contentScale = ContentScale.Inside,
            )

        }
    }
}

