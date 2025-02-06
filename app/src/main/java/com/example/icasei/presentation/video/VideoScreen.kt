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
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.example.icasei.common.State
import com.example.icasei.domain.model.VideoModel
import com.example.icasei.presentation.components.VideoPlayer
import com.example.icasei.ui.theme.Black
import com.example.icasei.ui.theme.Red
import kotlinx.coroutines.flow.collectLatest

@Composable
fun VideoScreen(modifier: Modifier = Modifier, videoItem: VideoModel) {
    val viewModel = hiltViewModel<VideoViewModel>()
    var favoredChange by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(Unit) {
        viewModel.checkFavorite(videoItem.id)

        viewModel.isFavorite.collectLatest {
            when (it) {
                is State.Data -> {
                    favoredChange = it.data ?: false
                }

                is State.Error -> {
                    favoredChange = false
                }

                else -> Unit
            }
        }
    }

    Column(
        modifier =
        modifier
            .fillMaxSize(),
    ) {
        VideoPlayer(videoId = videoItem.id, LocalLifecycleOwner.current)

        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(start = 10.dp, end = 10.dp, top = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(
                text = videoItem.title,
                modifier = modifier
                    .weight(1f),
                color = Black,
                fontSize = 15.sp,
            )

            Icon(
                imageVector = if (favoredChange) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                contentDescription = "favorite",
                modifier =
                modifier
                    .size(20.dp)
                    .clickable {
                        favoredChange = !favoredChange
                        viewModel.updateFavorite(favoredChange, videoItem)
                    },
                tint = Red,
            )
        }
    }
}

@Preview
@Composable
private fun VideoScreenPreview() {
    VideoScreen(
        videoItem = VideoModel(
            id = "FgAL6T_KILw",
            title = "ah ahs",
            thumbnail = "",
        ),
    )
}
