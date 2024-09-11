package com.example.onlinestoretesttaskavito.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.onlinestoretesttaskavito.ui.theme.Black
import com.example.onlinestoretesttaskavito.ui.theme.ButtonColorBlue
import com.example.onlinestoretesttaskavito.ui.theme.White
import com.example.onlinestoretesttaskavito.ui.theme.defaultRoundings
import com.example.onlinestoretesttaskavito.ui.theme.defaultTextStyle

private val DefaultContainerHeight = 46.dp

private val DefaultProgressIndicatorSize = 18.dp
private val DefaultProgressIndicatorStrokeWidth = 2.dp

@Composable
fun AppButton(
    text: String,
    modifier: Modifier = Modifier,
    loading: Boolean = false,
    onClick: () -> Unit = {},
    enabled: Boolean = true,
    backgroundColor: Color = ButtonColorBlue,
    contentColor: Color = Black
) {
    Box(
        modifier = modifier
            .clip(defaultRoundings.large)
            .background(backgroundColor)
            .height(DefaultContainerHeight)
            .clickable(enabled = enabled) {
                if (enabled) {
                    onClick()
                }
            }
            .padding(horizontal = 16.dp),
        contentAlignment = Alignment.Center
    ) {
        if (loading) {
            CircularProgressIndicator(
                modifier = Modifier
                    .size(DefaultProgressIndicatorSize),
                color = contentColor,
                strokeWidth = DefaultProgressIndicatorStrokeWidth
            )
        } else {
            BasicText(
                text = text,
                style = defaultTextStyle.textButton2.copy(
                    color = contentColor
                ),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
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
        enabled = false
    )
}

@Preview
@Composable
private fun AppButtonPreview4() {
    AppButton(
        text = "text",
        backgroundColor = White,
        enabled = false
    )
}