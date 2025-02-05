package com.example.icasei.presentation.video

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.example.icasei.data.remote.dto.SearchIdItem
import com.example.icasei.data.remote.dto.SnippetItem
import com.example.icasei.data.remote.dto.ThumbnailItem
import com.example.icasei.data.remote.dto.ThumbnailQualityItem
import com.example.icasei.domain.model.SearchItem
import com.example.icasei.presentation.components.VideoPlayer
import com.example.icasei.ui.theme.Black

@Composable
fun VideoScreen(modifier: Modifier = Modifier, videoItem: SearchItem) {
    Column(
        modifier =
        modifier
            .fillMaxSize(),
    ) {
        VideoPlayer(videoId = videoItem.id.videoId, LocalLifecycleOwner.current)

        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(start = 10.dp, end = 10.dp, top = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(
                text = videoItem.snippet.title,
                modifier = modifier
                    .weight(1f),
                color = Black,
                fontSize = 15.sp,
            )

            Icon(
//            imageVector = if (favoredChange) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                imageVector = Icons.Default.FavoriteBorder,
                contentDescription = "favorite",
                modifier =
                modifier
                    .size(20.dp)
                    .clickable {
//                    favoredChange = !favoredChange
//                    onFavoriteClick?.let { it(favoredChange) }
                    },
                tint = Black,
            )
        }
    }
}

@Preview
@Composable
private fun VideoScreenPreview() {
    VideoScreen(
        videoItem = SearchItem(
            id = SearchIdItem(videoId = "FgAL6T_KILw"),
            SnippetItem(
                publishedAt = "",
                title = "ah ahs",
                thumbnails = ThumbnailItem(high = ThumbnailQualityItem("")),
                description = "",
                channelTitle = "",
            ),
        ),
    )
}
