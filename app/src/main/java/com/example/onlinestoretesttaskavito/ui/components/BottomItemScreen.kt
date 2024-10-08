package com.example.onlinestoretesttaskavito.ui.components

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.onlinestoretesttaskavito.R
import com.example.onlinestoretesttaskavito.ui.navigation.ScreenRouts

enum class BottomItemScreen(
    @StringRes val title: Int,
    @DrawableRes val icon: Int,
    val route: String,
) {
    SEARCH(
        title = R.string.search,
        R.drawable.search_icon,
        ScreenRouts.ProductListScreen.route
    ),
    FAVORITES(
        title = R.string.favorites,
        R.drawable.favorites_icon,
        ScreenRouts.EmptyScreen.route
    ),
    ADS(
        title = R.string.ads,
        R.drawable.ads_icon,
        ScreenRouts.EmptyScreen.route
    ),
    MESSAGES(
        title = R.string.messages,
        R.drawable.messages_icon,
        ScreenRouts.EmptyScreen.route
    ),
    PROFILE(
        title = R.string.profile,
        R.drawable.profile_icon,
        ScreenRouts.EmptyScreen.route
    ),
}