package com.example.icasei.extension

import androidx.compose.foundation.lazy.LazyListState

fun LazyListState.reachedBottom(): Boolean {
    val visibleItemsInfo = layoutInfo.visibleItemsInfo
    return if (layoutInfo.totalItemsCount == 0) {
        false
    } else {
        val lastVisibleItem = visibleItemsInfo.last()
        (lastVisibleItem.index + 1 == layoutInfo.totalItemsCount)
    }
}
