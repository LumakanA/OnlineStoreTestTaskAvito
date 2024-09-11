package com.example.onlinestoretesttaskavito.ui.base

import kotlinx.coroutines.flow.StateFlow

interface StateHolder<S> {
    val state: StateFlow<S>
}
