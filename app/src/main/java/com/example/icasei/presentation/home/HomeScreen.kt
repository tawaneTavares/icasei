package com.example.icasei.presentation.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TextFieldDefaults.indicatorLine
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import com.example.icasei.domain.model.SearchItem
import com.example.icasei.presentation.components.VideoItem
import com.example.icasei.presentation.uiState.HomeUiState
import com.example.icasei.ui.theme.DarkGray
import com.example.icasei.ui.theme.Grey
import com.example.icasei.ui.theme.Red
import com.example.icasei.ui.theme.Transparent
import com.example.icasei.ui.theme.White
import java.util.UUID

@Composable
fun HomeScreen(modifier: Modifier = Modifier, onClickVideo: (SearchItem) -> Unit) {
    val homeViewModel = hiltViewModel<HomeViewModel>()
    val uiState by homeViewModel.uiStateHome.collectAsState()

    Column(
        modifier =
        modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        SearchField(
            modifier = modifier.padding(top = 16.dp),
            uiState,
        )

        MainContent(
            modifier = modifier,
            viewModel = homeViewModel,
            onClickVideo = onClickVideo,
        )
    }
}

@Composable
fun MainContent(modifier: Modifier = Modifier, viewModel: HomeViewModel, onClickVideo: (SearchItem) -> Unit) {
    val paging = viewModel.searchList.collectAsLazyPagingItems()

    if (paging.loadState.refresh is LoadState.Error) {
        Button(
            modifier = modifier
                .padding(24.dp)
                .fillMaxWidth(),
            onClick = {
                paging.retry()
            },
        ) {
            Text("Tente Novamente") // todo: adicionar um tratamento melhor de erro
        }
    }

    if (paging.loadState.refresh is LoadState.Loading) {
        Box(modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator() // todo: substituir por shimmer
        }
    }

    if (paging.loadState.refresh is LoadState.NotLoading) {
        if (paging.itemCount == 0) {
            Box(
                modifier = modifier.fillMaxSize(1f),
                contentAlignment = Alignment.Center,
            ) {
                Text("Nothing found")
            }
        }
    }

    LazyColumn(
        modifier = modifier,
    ) {
        item {
            if (paging.loadState.prepend is LoadState.Loading) {
                Box(modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }
        }

        item {
            if (paging.loadState.prepend is LoadState.Error) {
                Button(
                    modifier = modifier
                        .fillMaxWidth(),
                    onClick = {
                        paging.retry()
                    },
                ) {
                    Text("Tente Novamente") // todo: adicionar um tratamento melhor de erro
                }
            }
        }

        if (paging.loadState.refresh is LoadState.NotLoading) {
            if (paging.itemCount != 0) {
                items(
                    count = paging.itemCount,
                    key = paging.itemKey { (UUID.randomUUID().toString()) },
                    contentType = paging.itemContentType { "contentType" },
                ) { index ->
                    paging[index]?.let { item ->
                        VideoItem(
                            modifier = modifier.clickable {
                                onClickVideo(item)
                            },
                            title = item.snippet.title,
                            isFromFavoriteScreen = false,
                            imageUrl = item.snippet.thumbnails.high.url,
                        )
                    }
                }
            }
        }

        item {
            if (paging.loadState.append is LoadState.Loading) {
                Box(modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }
        }
        item {
            if (paging.loadState.append is LoadState.Error) {
                Button(
                    modifier = modifier
                        .padding(24.dp)
                        .fillMaxWidth(),
                    onClick = {
                        paging.retry()
                    },
                ) {
                    Text("Tente Novamente") // todo: adicionar um tratamento melhor de erro
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SearchField(modifier: Modifier = Modifier, uiState: HomeUiState) {
    TextField(
        value = uiState.searchText,
        onValueChange = {
            uiState.onSearchTextValueChanged?.invoke(it)
        },
        modifier =
        modifier
            .clip(
                RoundedCornerShape(50),
            )
            .indicatorLine(
                enabled = false,
                isError = false,
                interactionSource =
                remember {
                    MutableInteractionSource()
                },
                colors =
                TextFieldDefaults.colors(
                    focusedIndicatorColor = Transparent,
                    unfocusedIndicatorColor = Transparent,
                    disabledIndicatorColor = Transparent,
                ),
                focusedIndicatorLineThickness = 0.dp,
                unfocusedIndicatorLineThickness = 0.dp,
            ),
        placeholder = { Text(text = "Buscar vídeos", color = Grey) },
        leadingIcon = {
            Icon(
                imageVector = Icons.Rounded.Search,
                contentDescription = "Buscar vídeos",
                tint = Grey,
            )
        },
        colors =
        TextFieldDefaults.colors(
            focusedContainerColor = DarkGray,
            focusedTextColor = White,
            unfocusedContainerColor = DarkGray,
            unfocusedTextColor = White,
            focusedIndicatorColor = Transparent,
            unfocusedIndicatorColor = Transparent,
            disabledIndicatorColor = Transparent,
            cursorColor = Red,
        ),
    )
}

@Preview
@Composable
private fun HomeScreenPreview() {
    HomeScreen {
    }
}
