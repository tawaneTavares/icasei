package com.example.icasei.presentation.favorites

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.icasei.presentation.components.VideoItem

@Composable
fun FavoritesScreen() {
    val modifier = Modifier
    Column(
        modifier =
        modifier
            .fillMaxSize()
            .background(Color.Black),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        LazyColumn(
            contentPadding = PaddingValues(top = 16.dp),
        ) {
            items(2) { index ->
                VideoItem(modifier, title = index.toString(), false, "") // TODO: adicionar listagem
            }
        }
    }
}

@Preview
@Composable
private fun FavoritesPreview() {
    FavoritesScreen()
}
