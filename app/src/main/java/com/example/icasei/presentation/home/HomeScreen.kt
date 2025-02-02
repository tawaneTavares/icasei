package com.example.icasei.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TextFieldDefaults.indicatorLine
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.icasei.common.State
import com.example.icasei.presentation.components.VideoItem
import com.example.icasei.presentation.uiState.HomeUiState
import kotlinx.coroutines.flow.collectLatest

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    val homeViewModel = hiltViewModel<HomeViewModel>()
    val uiState by homeViewModel.uiStateHome.collectAsState()
    var showSuccess by remember { mutableStateOf(false) }
    var loading by remember { mutableStateOf(false) }
    var showError by remember { mutableStateOf(false) }
    var msgError: State.Error? by remember { mutableStateOf(null) }

    LaunchedEffect(Unit) {
        homeViewModel.getSearch("")

        homeViewModel.searchList.collectLatest {
            when (it) {
                is State.Data -> {
                    uiState.searchData = it.data
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

    Column(
        modifier =
        modifier
            .fillMaxSize()
            .background(Color.Black),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        SearchField(
            modifier = modifier.padding(top = 16.dp),
            uiState,
        )

        if (showSuccess) {
            LazyColumn(
                contentPadding = PaddingValues(top = 16.dp),
            ) {
                uiState.searchData?.let { data ->
                    items(data.items.size) { index ->
                        val item = data.items.elementAt(index)
                        VideoItem(
                            modifier,
                            title = item.snippet.title,
                            false,
                            item.snippet.thumbnails.high.url,
                        )
                    }
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
            ).indicatorLine(
                enabled = false,
                isError = false,
                interactionSource =
                remember {
                    MutableInteractionSource()
                },
                colors =
                TextFieldDefaults.colors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                ),
                focusedIndicatorLineThickness = 0.dp,
                unfocusedIndicatorLineThickness = 0.dp,
            ),
        placeholder = { Text(text = "Buscar vídeos", color = Color.Gray) },
        leadingIcon = {
            Icon(
                imageVector = Icons.Rounded.Search,
                contentDescription = "Buscar vídeos",
                tint = Color.Gray,
            )
        },
        colors =
        TextFieldDefaults.colors(
            focusedContainerColor = Color.DarkGray,
            focusedTextColor = Color.White,
            unfocusedContainerColor = Color.DarkGray,
            unfocusedTextColor = Color.White,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            cursorColor = Color.Red,
        ),
    )
}

@Preview
@Composable
private fun HomeScreenPreview() {
    HomeScreen()
}
