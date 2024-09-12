package com.example.onlinestoretesttaskavito.ui.screen.productDescription

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.example.onlinestoretesttaskavito.R
import com.example.onlinestoretesttaskavito.domain.response.products.Product
import com.example.onlinestoretesttaskavito.ui.theme.BackgroundColor
import com.example.onlinestoretesttaskavito.ui.theme.defaultRoundings
import com.example.onlinestoretesttaskavito.ui.theme.defaultTextStyle
import org.koin.androidx.compose.koinViewModel

private val DefaultStartPadding = 20.dp

@Composable
fun ProductDescriptionScreen(
    vm: ProductDescriptionViewModel,
    navController: NavController,
    id: String
) {

    LaunchedEffect(id) {
        vm.onAction(ProductDescriptionViewAction.Retry(id))
    }

    val state by vm.state.collectAsState()

    ProductDescriptionContent(
        state = state,
        onAction = vm::onAction,
        navController = navController,
        id = id
    )
}

@Composable
fun ProductDescriptionContent(
    state: ProductDescriptionState,
    onAction: (ProductDescriptionViewAction) -> Unit = {},
    navController: NavController,
    id: String
) {
    Scaffold { containerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(BackgroundColor)
                .padding(containerPadding)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = DefaultStartPadding, end = DefaultStartPadding)
            ) {
                when {
                    state.isLoading -> {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator()
                        }
                    }

                    state.error != null -> {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Text(text = stringResource(R.string.error, state.error))
                                Spacer(modifier = Modifier.height(16.dp))
                                Button(onClick = { onAction(ProductDescriptionViewAction.Retry(id)) }) {
                                    Text(text = stringResource(R.string.retry))
                                }
                            }
                        }
                    }

                    state.product != null -> {
                        ProductDescription(
                            products = state.product,
                            onBack = { navController.popBackStack() }
                        )
                    }

                    else -> {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(text = stringResource(R.string.no_product_info))
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ProductDescription(
    products: Product,
    onBack: () -> Unit
) {
    Column {
        Spacer(modifier = Modifier.height(15.dp))
        // Изображения
        Image(
            painter = rememberAsyncImagePainter(products.images.firstOrNull()),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .clip(defaultRoundings.small)
        )

        Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
            Spacer(modifier = Modifier.height(15.dp))

            // Название продукта
            Text(text = products.name, style = defaultTextStyle.subtitleBold16)
            Spacer(modifier = Modifier.height(10.dp))

            // Цены
            Text(text = "${products.price}₽", style = defaultTextStyle.bodyRegular14)
            Text(
                text = "${products.discounted_price}₽",
                style = defaultTextStyle.bodyRegular14,
                textDecoration = TextDecoration.LineThrough
            )
            Spacer(modifier = Modifier.height(20.dp))

            // Характеристики
            Text(
                text = stringResource(R.string.specifications),
                style = defaultTextStyle.subtitleBold16
            )
            Spacer(modifier = Modifier.height(10.dp))

            // Описание
            Text(text = products.description, style = defaultTextStyle.bodyRegular14)
            Spacer(modifier = Modifier.height(16.dp))

            // Рейтинг
            Text(
                text = "Рейтинг: ${products.product_rating}",
                style = defaultTextStyle.bodyRegular14
            )
            Spacer(modifier = Modifier.height(10.dp))

            // Бренд
            Text(text = "Бренд: ${products.brand}", style = defaultTextStyle.bodyRegular14)
            Spacer(modifier = Modifier.height(10.dp))

            // Спецификации
            products.product_specifications.forEach { spec ->
                Text(text = "${spec.key}: ${spec.value}", style = defaultTextStyle.bodyRegular14)
                Spacer(modifier = Modifier.height(5.dp))
            }

            Spacer(modifier = Modifier.height(80.dp))


            Button(onClick = onBack, modifier = Modifier.align(Alignment.CenterHorizontally)) {
                Text(text = stringResource(R.string.back))
            }
        }
    }
}


@Preview
@Composable
private fun ProductListScreenPreview() {
    ProductDescriptionScreen(
        vm = koinViewModel(),
        navController = rememberNavController(),
        id = ""
    )
}