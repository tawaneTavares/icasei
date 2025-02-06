package com.example.icasei.presentation.navigation

import android.net.Uri
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.icasei.domain.model.VideoModel
import com.example.icasei.presentation.favorites.FavoritesScreen
import com.example.icasei.presentation.home.HomeScreen
import com.example.icasei.presentation.playlist.PlaylistScreen
import com.example.icasei.presentation.profile.ProfileScreen
import com.example.icasei.presentation.video.VideoScreen
import com.squareup.moshi.Moshi

fun NavGraphBuilder.homeScreen(onClickVideo: (VideoModel) -> Unit) {
    composable(
        route = ScreensDestinations.HomeScreen.route,
    ) {
        HomeScreen(
            onClickVideo = onClickVideo,
        )
    }
}

fun NavHostController.navigateToHome() {
    navigate(ScreensDestinations.HomeScreen.route)
}

fun NavGraphBuilder.favoritesScreen() {
    composable(
        route = ScreensDestinations.FavoritesScreen.route,
    ) {
        FavoritesScreen()
    }
}

fun NavHostController.navigateToFavorites() {
    navigate(ScreensDestinations.FavoritesScreen.route)
}

fun NavGraphBuilder.playlistsScreen() {
    composable(
        route = ScreensDestinations.PlaylistsScreen.route,
    ) {
        PlaylistScreen()
    }
}

fun NavHostController.navigateToPlaylists() {
    navigate(ScreensDestinations.PlaylistsScreen.route)
}

private const val VIDEO_ITEM = "videoITem"

fun NavGraphBuilder.videoScreen(moshi: Moshi) {
    composable(
        route = "${ScreensDestinations.VideoScreen.route}/{$VIDEO_ITEM}",
        arguments = listOf(
            navArgument(VIDEO_ITEM) { type = NavType.StringType },
        ),
    ) { backStackEntry ->

        val videoJson = backStackEntry.arguments?.getString(VIDEO_ITEM)
        videoJson?.let { json ->
            val decodedJson = Uri.decode(json)
            val videoItem = moshi.adapter(VideoModel::class.java).fromJson(decodedJson)
            videoItem?.let {
                VideoScreen(videoItem = it)
            }
        }
    }
}

fun NavHostController.navigateToVideo(videoItem: VideoModel, moshi: Moshi) {
    val videoJson = moshi.adapter(VideoModel::class.java).toJson(videoItem)
    val encodedJson = Uri.encode(videoJson)
    navigate("${ScreensDestinations.VideoScreen.route}/$encodedJson")
}

fun NavGraphBuilder.profileScreen() {
    composable(
        route = ScreensDestinations.ProfileScreen.route,
    ) {
        ProfileScreen()
    }
}

fun NavHostController.navigateToProfileScreen() {
    navigate(ScreensDestinations.ProfileScreen.route)
}
