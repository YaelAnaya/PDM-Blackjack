package ic.yao.blackjack.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import ic.yao.blackjack.R

val Comfortaa = FontFamily(
    Font(R.font.comfortaa_regular, FontWeight.W500),
    Font(R.font.comfortaa_medium, FontWeight.W800),
    Font(R.font.comfortaa_bold, FontWeight.W900),
)

val Poppins = FontFamily(
    Font(R.font.poppins_regular, FontWeight.W500),
    Font(R.font.poppins_medium, FontWeight.W600),
    Font(R.font.poppins_semibold, FontWeight.W700),
    Font(R.font.poppins_bold, FontWeight.W800),
    Font(R.font.poppins_extrabold, FontWeight.W900),
)
// Set of Material typography styles to start with
val Typography = Typography(
    bodySmall = TextStyle(
        fontFamily = Comfortaa,
        fontWeight = FontWeight.Black,
        fontSize = 14.sp,
        textAlign = TextAlign.Justify,
    ),
    bodyMedium = TextStyle(
        fontFamily = Poppins,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        textAlign = TextAlign.Justify,
    ),
    bodyLarge = TextStyle(
        fontFamily = Comfortaa,
        fontWeight = FontWeight.W900,
        fontSize = 17.sp,
        lineHeight = 24.sp,
    ),
    titleLarge = TextStyle(
        fontFamily = Comfortaa,
        fontWeight = FontWeight.Black,
        fontSize = 20.sp,
    ),
    titleMedium = TextStyle(
        fontFamily = Poppins,
        fontWeight = FontWeight.SemiBold,
        fontSize = 28.sp,
    ),
    titleSmall = TextStyle(
        fontFamily = Poppins,
        fontWeight = FontWeight.SemiBold,
        fontSize = 16.sp,
    ),
    labelMedium = TextStyle(
        fontFamily = Poppins,
        fontWeight = FontWeight.SemiBold,
        fontSize = 15.sp,
    ),
)