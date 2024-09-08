package com.example.onlinestoretesttaskavito.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.onlinestoretesttaskavito.ui.theme.Black
import com.example.onlinestoretesttaskavito.ui.theme.ButtonColorBlue
import com.example.onlinestoretesttaskavito.ui.theme.White
import com.example.onlinestoretesttaskavito.ui.theme.defaultTextStyle

@Composable
fun AppButton(
    modifier: Modifier = Modifier,
    modifierText: Modifier = Modifier,
    backgroundColor: Color = ButtonColorBlue,
    text: String,
    textStyle: TextStyle = defaultTextStyle.textButton2,
    buttonEnabled: Boolean = true,
    onClick: () -> Unit = {}
) {
    Button(
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(containerColor = backgroundColor),
        onClick = onClick,
        shape = RoundedCornerShape(CornerSize(10.dp))
    ) {
        BasicText(
            modifier = modifierText.padding(horizontal = 8.dp, vertical = 4.dp),
            text = text,
            style = textStyle.copy(color = Black),
        )
    }
}


@Preview
@Composable
private fun AppButtonPreview1() {
    AppButton(
        text = "text"
    )
}

@Preview
@Composable
private fun AppButtonPreview2() {
    AppButton(
        text = "text",
        backgroundColor = White
    )
}

@Preview
@Composable
private fun AppButtonPreview3() {
    AppButton(
        text = "text",
        buttonEnabled = false
    )
}

@Preview
@Composable
private fun AppButtonPreview4() {
    AppButton(
        text = "text",
        backgroundColor = White,
        buttonEnabled = false
    )
}