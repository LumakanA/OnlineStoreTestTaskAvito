package com.example.onlinestoretesttaskavito.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.onlinestoretesttaskavito.ui.theme.LightGrayColor
import com.example.onlinestoretesttaskavito.ui.theme.White

private val DefaultHorizontalPadding = 30.dp
private val DefaultVerticalPadding = 15.dp

@Composable
fun CategoriesCard(
    categoryName: String,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(10.dp))
            .background(LightGrayColor)
            .padding(horizontal = DefaultHorizontalPadding, vertical = DefaultVerticalPadding)
            .clickable(onClick = onClick),
    ) {
        Text(
            text = categoryName,
            style = TextStyle(
                color = White,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold
            ),
            overflow = TextOverflow.Ellipsis,
            maxLines = 2
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCategoriesCard() {
    CategoriesCard(
        categoryName = "Авто",
        onClick = {}
    )
}