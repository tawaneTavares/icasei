package com.example.icasei.presentation.video

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.example.icasei.presentation.components.VideoPlayer

@Composable
fun PlayerScreen(modifier: Modifier = Modifier, videoId: String) {
    Box(
        modifier =
        modifier
            .fillMaxSize(),
    ) {
        VideoPlayer(videoId = videoId, LocalLifecycleOwner.current)
    }
}
