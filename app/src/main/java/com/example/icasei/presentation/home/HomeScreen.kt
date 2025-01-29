package com.example.icasei.presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.icasei.R


@Composable
fun HomeScreen() {
    val modifier = Modifier
    Box(
       modifier = modifier.fillMaxSize()
           .background(Color.Black),
        contentAlignment = Alignment.TopCenter
    ) {
        SearchField(
            modifier = modifier.padding(top = 16.dp),
            searchQuery = "",
            onQueryChanged = {}
        )

        LazyColumn(
            contentPadding = PaddingValues(top = 16.dp),
            verticalArrangement = Arrangement.SpaceAround,
        ) {

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SearchField(
    searchQuery: String,
    onQueryChanged: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    TextField(
        value = searchQuery,
        onValueChange = onQueryChanged,
        modifier = modifier
            .clip(
                RoundedCornerShape(50)
            )
            .indicatorLine(
                enabled = false,
                isError = false,
                interactionSource = remember {
                    MutableInteractionSource()
                },
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                ),
                focusedIndicatorLineThickness = 0.dp,
                unfocusedIndicatorLineThickness = 0.dp
            ),
        placeholder = { Text(text = "Buscar vídeos", color = Color.Gray) },
        leadingIcon = {
            Icon(
                imageVector = Icons.Rounded.Search,
                contentDescription = "Buscar vídeos",
                tint = Color.Gray
            )
        },
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.DarkGray,
            focusedTextColor = Color.White,
            unfocusedContainerColor = Color.DarkGray,
            unfocusedTextColor = Color.White,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            cursorColor = Color.Red,
        )
    )
}

@Composable
private fun VideoItem(modifier: Modifier, title: String) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.Start,
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_default_thumbnail),
            contentDescription = "video thumbnail"
        )

        Text(
            text = title,
            modifier = modifier,
            color = Color.White,
            fontSize = 12.sp,
        )
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    VideoItem(modifier = Modifier, "bla")
}