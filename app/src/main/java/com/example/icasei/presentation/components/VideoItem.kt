package com.example.icasei.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.icasei.R

@Composable
fun VideoItem(modifier: Modifier, title: String, isFavorite: Boolean, imageUrl: String) {
    Column(
        modifier = modifier.padding(top = 20.dp),
        horizontalAlignment = Alignment.Start,
    ) {
        Box {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(imageUrl)
                    .placeholder(R.drawable.ic_default_thumbnail)
                    .build(),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = modifier.fillMaxWidth()
                    .heightIn(max = 150.dp),
            )

            Icon(
                imageVector = if (isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                contentDescription = "favorite",
                modifier =
                Modifier
                    .size(20.dp)
                    .align(Alignment.TopEnd),
                tint = Color.White,
            )
        }

        Text(
            text = title,
            modifier = modifier.padding(top = 5.dp),
            color = Color.White,
            fontSize = 15.sp,
        )
    }
}

@Preview
@Composable
fun VideoItemPreview() {
    VideoItem(modifier = Modifier, "bla", false, "")
}
