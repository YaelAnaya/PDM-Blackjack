package ic.yao.blackjack.ui.view.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import ic.yao.blackjack.ui.theme.Comfortaa
import ic.yao.blackjack.ui.theme.Poppins
import ic.yao.blackjack.ui.theme.badgeContainer
import ic.yao.blackjack.ui.theme.continueButtonLight
import ic.yao.blackjack.ui.theme.scoreTitleLabel
import ic.yao.blackjack.ui.theme.white

@Preview
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsDialog(
    countToWin: String = "",
    onDismiss: () -> Unit = {},
    onConfirm: (String) -> Unit = {},
) {
    var onChangeInput by remember { mutableStateOf(countToWin) }
    Dialog(
        onDismissRequest = { onDismiss() },
        properties = DialogProperties(
            dismissOnBackPress = true,
            dismissOnClickOutside = true
        )
    ) {
        ElevatedCard(
            modifier = Modifier
                .width(410.dp)
                .height(288.dp)
                .padding(20.dp),
            shape = MaterialTheme.shapes.small,
            colors = CardDefaults.elevatedCardColors(
                contentColor = scoreTitleLabel,
                containerColor = white
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(26.dp),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "CHANGE COUNT TO WIN",
                    fontFamily = Comfortaa,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Black,
                    textAlign = TextAlign.Center,
                    color = Black
                )
                Spacer(modifier = Modifier.height(20.dp))
                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth(),
                    value = onChangeInput,
                    onValueChange = { onChangeInput = it },
                    label = { Text(text = "Count") },
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = continueButtonLight,
                        focusedLabelColor = badgeContainer,
                        unfocusedBorderColor = continueButtonLight,
                        cursorColor = badgeContainer,
                        placeholderColor = badgeContainer,
                        selectionColors = TextSelectionColors(
                            handleColor = continueButtonLight,
                            backgroundColor = continueButtonLight,
                        )
                    ),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )
                Spacer(modifier = Modifier.height(20.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Button(
                        onClick = { onDismiss() },
                        modifier = Modifier
                            .height(40.dp)
                            .weight(1f),
                        shape = MaterialTheme.shapes.extraLarge,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = continueButtonLight,
                            contentColor = Color.White
                        ),
                        content = {
                            Text(
                                text = "Cancel",
                                style = MaterialTheme.typography.bodySmall,
                                fontFamily = Poppins,
                                fontWeight = FontWeight.SemiBold,
                            )
                        }
                    )
                    Spacer(modifier = Modifier.width(20.dp))
                    Button(
                        onClick = { onConfirm(onChangeInput) },
                        modifier = Modifier
                            .height(40.dp)
                            .weight(1f),
                        shape = MaterialTheme.shapes.extraLarge,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = continueButtonLight,
                            contentColor = Color.White
                        ),
                        content = {
                            Text(
                                text = "Ok",
                                fontFamily = Poppins,
                                style = MaterialTheme.typography.bodySmall,
                                fontWeight = FontWeight.SemiBold,
                            )
                        }
                    )
                }
            }
        }
    }
}