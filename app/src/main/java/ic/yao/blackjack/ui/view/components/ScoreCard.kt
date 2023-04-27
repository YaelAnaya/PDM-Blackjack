package ic.yao.blackjack.ui.view.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.Badge
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ic.yao.blackjack.data.database.entities.PlayerScoreEntity

@Composable
fun ScoreCard(
    score : PlayerScoreEntity,
) {
    ElevatedCard (
        shape = MaterialTheme.shapes.medium,
        colors = CardDefaults.elevatedCardColors(
            containerColor = MaterialTheme.colorScheme.onBackground,
            contentColor = MaterialTheme.colorScheme.onSurface,
        )
    ) {
        Box(
            modifier = Modifier
                .size(width = 324.dp, height = 267.dp)
                .padding(16.dp),
            contentAlignment = Alignment.TopCenter
        ) {
            Column (
                horizontalAlignment = Alignment.CenterHorizontally,
            ){
                Row (
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                ){
                    Badge(
                        modifier = Modifier
                            .size(width = 70.dp, height = 30.dp)
                            .clip(MaterialTheme.shapes.extraLarge),
                        backgroundColor = MaterialTheme.colorScheme.secondary,
                        content = {
                            Icon(
                                imageVector = Icons.Outlined.Favorite,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.primary,
                                modifier = Modifier
                                    .size(16.dp)
                                    .padding(end = 4.dp),
                            )
                            Text(
                                text = score.score.toString(),
                                color = MaterialTheme.colorScheme.primary,
                                style = MaterialTheme.typography.bodySmall,
                                fontWeight = FontWeight.Black
                            )
                        }
                    )
                    Spacer(modifier = Modifier.size(36.dp))
                    Text(
                        text = score.date,
                        style = MaterialTheme.typography.bodySmall,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.surface
                    )
                }
                Spacer(modifier = Modifier.size(23.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(36.dp, alignment = Alignment.CenterHorizontally),
                ){
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Text(
                            color = MaterialTheme.colorScheme.surface,
                            text = "WINNER",
                            style = MaterialTheme.typography.titleSmall
                        )
                        Text(
                            text = score.winnerName,
                            style = MaterialTheme.typography.bodySmall
                        )
                    }

                    Text(
                        color = MaterialTheme.colorScheme.surface,
                        text = "VS",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )

                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Text(
                            color = MaterialTheme.colorScheme.surface,
                            text = "LOSER",
                            style = MaterialTheme.typography.titleSmall
                        )
                        Text(
                            text = score.loserName,
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                }
                Spacer(modifier = Modifier.size(14.dp))
                LazyRow (
                    horizontalArrangement = Arrangement.spacedBy((-22).dp)
                ){
                    items(score.winnerHand.size) { index ->
                        PlayingCard(
                            card = score.winnerHand[index],
                            borderColor = MaterialTheme.colorScheme.onBackground,
                        )
                    }
                }
            }

        }

    }
}