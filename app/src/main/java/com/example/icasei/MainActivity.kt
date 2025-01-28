package com.example.icasei

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.example.icasei.ui.theme.IcaseiTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            IcaseiTheme {
                val navController = rememberNavController()
                val selected = remember {
                    mutableStateOf(Icons.Default.Home)
                }
                val modifier = Modifier
                Scaffold(modifier = modifier.fillMaxSize(),
                    bottomBar = {
                        BottomAppBar {
                            BottomMenuIcon(
                                modifier = modifier.weight(1f),
                                icon = Icons.Default.Home,
                                contentDescription = "Home",
                                isSelected = (selected.value == Icons.Default.Home)
                            ) { imageVector ->
                                selected.value = imageVector
                                /*todo: add navigation navController.navigate(~screen~) {
                                //popUpTo(0)
                                }*/
                            }

                            BottomMenuIcon(
                                modifier = modifier.weight(1f),
                                icon = Icons.Default.Favorite,
                                contentDescription = "Favorite",
                                isSelected = (selected.value == Icons.Default.Favorite)
                            ) { imageVector ->
                                selected.value = imageVector
                                /*todo: add navigation navController.navigate(~screen~) {
                                //popUpTo(0)
                                }*/
                            }

                            BottomMenuIcon(
                                modifier = modifier.weight(1f),
                                icon = Icons.Default.PlayArrow,
                                contentDescription = "My Lists",
                                isSelected = (selected.value == Icons.Default.PlayArrow)
                            ) { imageVector ->
                                selected.value = imageVector
                                /*todo: add navigation navController.navigate(~screen~) {
                                //popUpTo(0)
                                }*/
                            }
                        }
                    }
                ) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
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
            modifier = modifier
        ) {
            Icon(
                imageVector = icon,
                contentDescription = contentDescription,
                modifier = Modifier.size(26.dp),
                tint = if (isSelected) Color.Black else Color.Gray,
            )
        }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    IcaseiTheme {
        Greeting("Android")
    }
}