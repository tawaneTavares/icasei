package com.example.icasei.presentation

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink
import com.example.icasei.common.Constants.DEEPLINK_DOMAIN
import com.example.icasei.presentation.navigation.ScreensDestinations
import com.example.icasei.presentation.navigation.favoritesScreen
import com.example.icasei.presentation.navigation.homeScreen
import com.example.icasei.presentation.navigation.navigateToFavorites
import com.example.icasei.presentation.navigation.navigateToHome
import com.example.icasei.presentation.navigation.navigateToPlayer
import com.example.icasei.presentation.navigation.navigateToProfileScreen
import com.example.icasei.presentation.navigation.navigateToVideo
import com.example.icasei.presentation.navigation.playerScreen
import com.example.icasei.presentation.navigation.playlistsScreen
import com.example.icasei.presentation.navigation.profileScreen
import com.example.icasei.presentation.navigation.videoScreen
import com.example.icasei.ui.theme.Black
import com.example.icasei.ui.theme.Grey
import com.example.icasei.ui.theme.IcaseiTheme
import com.squareup.moshi.Moshi
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var moshi: Moshi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            IcaseiTheme {
                val navController = rememberNavController()
                val selected =
                    remember {
                        mutableStateOf(Icons.Default.AccountCircle)
                    }
                val modifier = Modifier
                Scaffold(
                    modifier = modifier
                        .fillMaxSize(),
                    bottomBar = {
                        BottomAppBar {
                            BottomMenuIcon(
                                modifier = modifier.weight(1f),
                                icon = Icons.Default.Home,
                                contentDescription = "Home",
                                isSelected = (selected.value == Icons.Default.Home),
                            ) { imageVector ->
                                selected.value = imageVector
                                navController.navigateToHome()
                            }

                            BottomMenuIcon(
                                modifier = modifier.weight(1f),
                                icon = Icons.Default.Favorite,
                                contentDescription = "Favorite",
                                isSelected = (selected.value == Icons.Default.Favorite),
                            ) { imageVector ->
                                selected.value = imageVector
                                navController.navigateToFavorites()
                            }

                            BottomMenuIcon(
                                modifier = modifier.weight(1f),
                                icon = Icons.Default.AccountCircle,
                                contentDescription = "Profile",
                                isSelected = (selected.value == Icons.Default.AccountCircle),
                            ) { imageVector ->
                                selected.value = imageVector
                                navController.navigateToProfileScreen()
                            }
                        }
                    },
                ) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = ScreensDestinations.ProfileScreen.route,
                        modifier = Modifier.padding(innerPadding),
                    ) {
                        composable(
                            route = "notification_data?videoID={videoID}",
                            deepLinks = listOf(
                                navDeepLink {
                                    uriPattern = "https://$DEEPLINK_DOMAIN/?videoID={videoID}"
                                },
                            ),
                            arguments = listOf(navArgument("videoID") { type = NavType.StringType }),
                        ) { backStackEntry ->
                            val videoId = backStackEntry.arguments?.getString("videoID")
                            if (videoId != null) {
                                navController.navigateToPlayer(videoId)
                            }
                        }
                        homeScreen(
                            onClickVideo = { videoItem ->
                                navController.navigateToVideo(videoItem, moshi)
                            },
                        )
                        favoritesScreen(
                            onClickVideo = { videoItem ->
                                navController.navigateToVideo(videoItem, moshi)
                            },
                        )
                        playlistsScreen()
                        videoScreen(moshi)
                        profileScreen()
                        playerScreen()
                    }
                }
            }
        }
    }
}

@Composable
fun BottomMenuIcon(modifier: Modifier, icon: ImageVector, contentDescription: String, isSelected: Boolean, onClick: (ImageVector) -> Unit) {
    IconButton(
        onClick = {
            onClick.invoke(icon)
        },
        modifier = modifier,
    ) {
        Icon(
            imageVector = icon,
            contentDescription = contentDescription,
            modifier = Modifier.size(26.dp),
            tint = if (isSelected) Black else Grey,
        )
    }
}
