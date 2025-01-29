package com.example.icasei.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.icasei.R

@Composable
fun VideoItem(modifier: Modifier, title: String) {
    Column(
        modifier = modifier.padding(top = 16.dp),
        horizontalAlignment = Alignment.Start,
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_default_thumbnail),
            contentDescription = "video thumbnail",
            contentScale = ContentScale.FillWidth,
            modifier = modifier.fillMaxWidth()
        )

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
    VideoItem(modifier = Modifier, "bla")
}