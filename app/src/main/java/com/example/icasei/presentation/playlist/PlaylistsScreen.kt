package com.example.icasei.presentation.playlist

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.icasei.R
import com.example.icasei.ui.theme.White

@Composable
fun PlaylistScreen(modifier: Modifier = Modifier) {
    LazyColumn {
        items(2) { index ->
            PlayListItem(
                modifier,
                title = index.toString(),
                imageUrl = "https://i.ytimg.com/vi/PVLb5U10-TM/hqdefault.jpg",
            ) // TODO: adicionar listagem
        }
    }
}

@Composable
fun PlayListItem(modifier: Modifier = Modifier, title: String, imageUrl: String) {
    Row(
        modifier = modifier.padding(horizontal = 10.dp, vertical = 5.dp),
    ) {
        // todo: todas essas images devem ser substituidas por asyncImage

        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(imageUrl)
                .placeholder(R.drawable.ic_default_thumbnail)
                .build(),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = modifier
                .weight(1f)
                .clip(RoundedCornerShape(5))
                .height(70.dp),
        )

        Text(
            text = title,
            modifier = modifier
                .padding(start = 5.dp)
                .weight(2f),
            color = White,
            fontSize = 13.sp,
        )
    }
}

@Preview
@Composable
private fun PlaylistScreenPreview() {
    PlaylistScreen()
}
