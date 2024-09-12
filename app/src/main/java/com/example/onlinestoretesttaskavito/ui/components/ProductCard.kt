package com.example.onlinestoretesttaskavito.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.onlinestoretesttaskavito.domain.response.products.Product
import com.example.onlinestoretesttaskavito.ui.theme.BackgroundColor
import com.example.onlinestoretesttaskavito.ui.theme.White

@Composable
fun ProductCard(
    product: Product,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .background(BackgroundColor)
            .padding(8.dp)
            .clickable(onClick = onClick),
        horizontalAlignment = Alignment.Start
    ) {
        Image(
            painter = rememberAsyncImagePainter(product.images.firstOrNull()),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(159.dp)
                .padding(8.dp)
                .clip(RoundedCornerShape(8.dp))
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = product.name,
            style = TextStyle(
                color = White,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            ),
            overflow = TextOverflow.Ellipsis,
            maxLines = 2
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "${product.discountedPrice.toInt()} ₽",
            style = TextStyle(
                color = White,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        )

        Spacer(modifier = Modifier.width(4.dp))

        Text(
            text = "${product.price.toInt()} ₽",
            style = TextStyle(
                color = White,
                fontSize = 14.sp,
                textDecoration = TextDecoration.LineThrough
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewProductCard() {
    ProductCard(
        product = Product(
            id = "1",
            name = "Sample Product",
            category = listOf("Category 1", "Category 2"),
            price = 2000.0,
            discountedPrice = 1500.0,
            images = listOf("https://example.com/image.jpg"),
            description = "Sample Description",
            productRating = 4.5,
            brand = "Sample Brand",
            productSpecifications = emptyList()
        ),
        onClick = {}
    )
}
