package com.example.onlinestoretesttaskavito.ui.screen.productList

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.onlinestoretesttaskavito.R
import com.example.onlinestoretesttaskavito.ui.screen.login.LoginState
import com.example.onlinestoretesttaskavito.ui.theme.BackgroundColor
import com.example.onlinestoretesttaskavito.ui.theme.White
import com.example.onlinestoretesttaskavito.ui.theme.defaultTextStyle
import org.koin.androidx.compose.koinViewModel

private val DefaultStartPadding = 25.dp
private val DefaultTopTextPadding = 20.dp

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
    state: LoginState,
    onAction: (ProductListViewAction) -> Unit = {},
    navController: NavController
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
                    .fillMaxHeight()
                    .fillMaxWidth()
                    .padding(start = DefaultStartPadding, end = DefaultStartPadding)
                    .verticalScroll(rememberScrollState())
            ) {
                Text(
                    modifier = Modifier.padding(top = 50.dp),
                    text = stringResource(R.string.product_list_screen),
                    style = defaultTextStyle.textStyle4.copy(color = White),
                )
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