package com.example.onlinestoretesttaskavito.ui.screen.productList

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.onlinestoretesttaskavito.R
import com.example.onlinestoretesttaskavito.ui.components.CategoriesCard
import com.example.onlinestoretesttaskavito.ui.components.ProductCard
import com.example.onlinestoretesttaskavito.ui.theme.BackgroundColor
import com.example.onlinestoretesttaskavito.ui.theme.Black
import com.example.onlinestoretesttaskavito.ui.theme.White
import com.example.onlinestoretesttaskavito.ui.theme.defaultRoundings
import com.example.onlinestoretesttaskavito.ui.theme.defaultTextStyle
import org.koin.androidx.compose.koinViewModel

private val DefaultStartPadding = 20.dp

@Composable
fun ProductListScreen(
    vm: ProductListViewModel,
    navController: NavController
) {
    val state by vm.state.collectAsState()

    ProductListContent(
        state = state,
        onAction = vm::onAction,
        navController = navController
    )
}

@Composable
fun ProductListContent(
    state: ProductListState,
    onAction: (ProductListViewAction) -> Unit = {},
    navController: NavController
) {
    var selectedTabIndex by remember { mutableIntStateOf(0) }
    var itemsPerPage by remember { mutableIntStateOf(10) }
    var selectedCategory by remember { mutableStateOf<String?>(null) }

    // Захардкоженный список категорий
    val categories = listOf("clothing", "furniture", "footwear")

    val tabTitles = listOf(
        stringResource(R.string.recommend),
        stringResource(R.string.fresh), stringResource(R.string.near_you)
    )

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
                                Button(onClick = { onAction(ProductListViewAction.FetchProducts) }) {
                                    Text(text = stringResource(R.string.retry))
                                }
                            }
                        }
                    }

                    else -> {

                        LazyRow(
                            modifier = Modifier
                                .padding(vertical = 16.dp)
                                .fillMaxWidth()
                        ) {
                            items(categories) { category ->
                                CategoriesCard(
                                    categoryName = category,
                                    onClick = {
                                        selectedCategory = category
                                        onAction(ProductListViewAction.FilterByCategory(category))
                                    }
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                            }
                        }

                        Spacer(modifier = Modifier.height(50.dp))

                        ScrollableTabRow(
                            selectedTabIndex = selectedTabIndex,
                            modifier = Modifier.fillMaxWidth(),
                            edgePadding = 1.dp,
                            contentColor = BackgroundColor,
                            containerColor = BackgroundColor,
                            divider = {},
                            indicator = {}
                        ) {
                            tabTitles.forEachIndexed { index, title ->
                                Tab(
                                    selected = selectedTabIndex == index,
                                    onClick = { selectedTabIndex = index },
                                    text = {
                                        Text(
                                            text = title,
                                            color = if (selectedTabIndex == index) White else Color.Gray,
                                            fontWeight = if (selectedTabIndex == index) FontWeight.Bold else FontWeight.Normal,
                                            style = defaultTextStyle.subtitleBold16
                                        )
                                    },
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(10.dp))

                        RecommendationsContent(
                            state = state,
                            itemsPerPage = itemsPerPage,
                            modifier = Modifier.weight(1f)
                        )

                        ShowPerOptions(
                            selectedItemsPerPage = itemsPerPage,
                            onSelectItemsPerPage = { itemsPerPage = it }
                        )
                    }
                }
            }
        }
    }
}


@Composable
fun RecommendationsContent(state: ProductListState, itemsPerPage: Int, modifier: Modifier) {
    val gridState = rememberLazyGridState()

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        state = gridState,
        contentPadding = PaddingValues(
            start = DefaultStartPadding,
            end = DefaultStartPadding,
            bottom = 16.dp
        ),
        modifier = modifier
            .fillMaxWidth()
    ) {
        items(state.products.take(itemsPerPage)) { product ->
            ProductCard(
                product = product,
                onClick = {
                    //        navController.navigate("productDetails/${product.id}")
                }
            )
        }
    }
}

@Composable
fun ShowPerOptions(
    selectedItemsPerPage: Int,
    onSelectItemsPerPage: (Int) -> Unit
) {
    LazyRow(
        verticalAlignment = Alignment.CenterVertically, modifier = Modifier
            .padding(16.dp)
            .heightIn(40.dp)
    ) {
        item {
            TextButton(
                onClick = { /* No action, just label */ },
                modifier = Modifier.padding(end = 30.dp)
            ) {
                Box(
                    modifier = Modifier
                        .clip(defaultRoundings.small)
                        .background(White)
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Показать по",
                        color = Black
                    )
                }
            }
        }

        item {
            listOf(10, 20, 30).forEach { count ->
                TextButton(
                    onClick = { onSelectItemsPerPage(count) }
                ) {
                    Box(
                        modifier = Modifier
                            .clip(defaultRoundings.small)
                            .background(White)
                            .padding(horizontal = 8.dp, vertical = 8.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = count.toString(),
                            color = if (selectedItemsPerPage == count) Black else Color.Gray
                        )
                    }
                }
            }
        }
    }
}


@Preview
@Composable
private fun ProductListScreenPreview() {
    ProductListScreen(
        vm = koinViewModel(),
        navController = rememberNavController()
    )
}