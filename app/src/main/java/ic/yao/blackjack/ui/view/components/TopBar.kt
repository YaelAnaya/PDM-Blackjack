package ic.yao.blackjack.ui.view.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import ic.yao.blackjack.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar () {
    CenterAlignedTopAppBar(
        modifier = Modifier.padding(vertical = 18.dp),
        title = {
            Icon(
                modifier = Modifier.size(width = 50.dp, height = 54.dp),
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Blackjack Logo",
                tint = Color(0xFF296BE7),
            )
        },
        windowInsets = TopAppBarDefaults.windowInsets,
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.background
        )
    )
}