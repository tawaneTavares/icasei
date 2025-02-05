package com.example.icasei.presentation.favorites

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.icasei.presentation.components.VideoItem

@Composable
fun FavoritesScreen(modifier: Modifier = Modifier) {
    val viewModel = hiltViewModel<FavoritesViewModel>()
    val uiState by viewModel.uiStateFavorites.collectAsState()

    Box(
        modifier =
        modifier
            .fillMaxSize(),
    ) {
        if (uiState.favoritesList != null) {
            LazyColumn(
                contentPadding = PaddingValues(top = 16.dp),
            ) {
                uiState.favoritesList?.let { favoritesList ->
                    items(
                        count = favoritesList.size,
                    ) { index ->
                        favoritesList[index].let { item ->
                            VideoItem(
                                modifier = modifier.animateItem(),
                                title = item.title,
                                isFavorite = true,
                                isFromFavoriteScreen = true,
                                imageUrl = item.thumbnailUrl,
                            ) { isFavorite ->
                                viewModel.updateFavorite(isFavorite, item)
                            }
                        }
                    }
                }
            }
        }
        if (uiState.loading) {
            CircularProgressIndicator(modifier = modifier.align(Alignment.Center))
        }

        if (uiState.showError) {
            Text(
                text = uiState.msgError ?: "Algo deu errado",
                color = MaterialTheme.colorScheme.error,
                textAlign = TextAlign.Center,
                modifier =
                modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .align(Alignment.Center),
            )
        }
    }
}

@Preview
@Composable
private fun FavoritesPreview() {
    FavoritesScreen()
}
