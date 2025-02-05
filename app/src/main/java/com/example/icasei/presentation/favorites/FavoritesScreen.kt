package com.example.icasei.presentation.favorites

import androidx.compose.foundation.background
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.icasei.common.State
import com.example.icasei.presentation.components.VideoItem
import kotlinx.coroutines.flow.collectLatest

@Composable
fun FavoritesScreen(modifier: Modifier = Modifier) {
    val viewModel = hiltViewModel<FavoritesViewModel>()
    val uiState by viewModel.uiStateFavorites.collectAsState()
    var showSuccess by remember { mutableStateOf(false) }
    var loading by remember { mutableStateOf(false) }
    var showError by remember { mutableStateOf(false) }
    var msgError: State.Error? by remember { mutableStateOf(null) }

    LaunchedEffect(Unit) {
        viewModel.getFavoritesList()

        viewModel.favoritesList.collectLatest {
            when (it) {
                is State.Data -> {
                    uiState.favoritesList = it.data
                    showSuccess = true
                    loading = false
                }

                is State.Error -> {
                    msgError = it
                    showError = true
                }
                is State.Loading -> loading = true
                else -> Unit
            }
        }
    }

    Box(
        modifier =
        modifier
            .fillMaxSize()
            .background(Color.Black),
    ) {
        if (showSuccess) {
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
        if (loading) {
            CircularProgressIndicator(modifier = modifier.align(Alignment.Center))
        }

        if (showError) {
            Text(
                text = msgError?.cause?.message ?: "Algo deu errado",
                color = MaterialTheme.colorScheme.error,
                textAlign = TextAlign.Center,
                modifier =
                Modifier
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
