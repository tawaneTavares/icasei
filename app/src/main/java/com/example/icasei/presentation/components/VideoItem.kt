package com.example.icasei.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.icasei.R
import com.example.icasei.ui.theme.Black
import com.example.icasei.ui.theme.Red

@Composable
fun VideoItem(
    modifier: Modifier,
    title: String,
    isFavorite: Boolean = false,
    isFromFavoriteScreen: Boolean,
    imageUrl: String,
    onFavoriteClick: ((Boolean) -> Unit)? = null,
) {
    Column(
        modifier = modifier.padding(top = 20.dp),
        horizontalAlignment = Alignment.Start,
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(imageUrl)
                .placeholder(R.drawable.ic_default_thumbnail)
                .error(R.drawable.ic_default_thumbnail)
                .build(),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = modifier
                .fillMaxWidth()
                .heightIn(max = 170.dp),
        )

        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(start = 10.dp, end = 10.dp, top = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            val newTitle = if (isFromFavoriteScreen && title.length > 40) {
                title.substring(0, 40 - 3) + "..."
            } else {
                title
            }
            Text(
                text = newTitle,
                modifier = modifier
                    .weight(1f),
                color = Black,
                fontSize = 15.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
            if (isFromFavoriteScreen) {
                Icon(
                    imageVector = if (isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                    contentDescription = "favorite",
                    modifier =
                    modifier
                        .size(20.dp)
                        .clickable {
                            onFavoriteClick?.let { it(!isFavorite) }
                        },
                    tint = Red,
                )
            }
        }
    }
}

@Preview
@Composable
fun VideoItemPreview() {
    VideoItem(
        modifier = Modifier,
        title = "bdadawddwdwdawdadadaddadadaddaadadaddaadadaddaadawdala",
        isFavorite = false,
        isFromFavoriteScreen = true,
        imageUrl = "",
    )
}
