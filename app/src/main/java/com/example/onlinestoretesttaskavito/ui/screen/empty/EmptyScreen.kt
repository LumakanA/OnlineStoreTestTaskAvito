package com.example.onlinestoretesttaskavito.ui.screen.empty

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
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.onlinestoretesttaskavito.R
import com.example.onlinestoretesttaskavito.ui.components.AppButton
import com.example.onlinestoretesttaskavito.ui.theme.BackgroundColor
import com.example.onlinestoretesttaskavito.ui.theme.White
import com.example.onlinestoretesttaskavito.ui.theme.defaultTextStyle

private val DefaultStartPadding = 25.dp

@Composable
fun EmptyScreen(
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
                    text = stringResource(R.string.empty_screen),
                    style = defaultTextStyle.textStyle4.copy(color = White),
                )

                AppButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 100.dp),
                    text = stringResource(R.string.back),
                    backgroundColor = White,
                    onClick = {
                        navController.navigateUp()
                    }
                )
            }
        }
    }
}


@Preview
@Composable
private fun ProductListScreenPreview() {
    EmptyScreen(
        navController = rememberNavController()
    )
}