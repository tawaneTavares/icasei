package com.example.icasei.presentation.playlist

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.icasei.R

@Composable
fun PlaylistScreen(modifier: Modifier = Modifier) {
    LazyColumn {
        items(2) { index ->
            PlayListItem(modifier, thumbnail = "", title = index.toString()) // TODO: adicionar listagem
        }
    }
}

@Composable
fun PlayListItem(modifier: Modifier = Modifier, thumbnail: String, title: String) {
    Row(
        modifier = modifier.padding(horizontal = 10.dp, vertical = 5.dp),
    ) {
        // todo: todas essas images devem ser substituidas por asyncImage
        Image(
            painter = painterResource(id = R.drawable.ic_default_thumbnail),
            contentDescription = "video thumbnail",
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
            color = Color.White,
            fontSize = 13.sp,
        )
    }
}

@Preview
@Composable
private fun PlaylistScreenPreview() {
    PlaylistScreen()
}
