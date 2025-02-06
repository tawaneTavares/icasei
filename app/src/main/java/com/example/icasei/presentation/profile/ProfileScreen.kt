package com.example.icasei.presentation.profile

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.icasei.R
import com.example.icasei.common.GoogleAuthClient
import com.example.icasei.presentation.localPush.LocalNotificationService
import com.example.icasei.presentation.localPush.NotificationData
import com.example.icasei.ui.theme.White
import kotlinx.coroutines.launch

@Composable
fun ProfileScreen(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val googleAuthClient = GoogleAuthClient(context)
    val service = LocalNotificationService(context)

    var isSignIn by rememberSaveable {
        mutableStateOf(googleAuthClient.isSingedIn())
    }

    val user = googleAuthClient.getFirebaseAuth()

    var showNotificationDialog by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Box(
            modifier = modifier
                .size(120.dp)
                .clip(CircleShape),
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(user?.photoUrl)
                    .placeholder(R.drawable.ic_empty_profile)
                    .error(R.drawable.ic_empty_profile)
                    .build(),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = modifier,
            )
        }

        Spacer(modifier = modifier.height(16.dp))

        Text(
            text = user?.displayName ?: "",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
        )

        Spacer(modifier = modifier.height(8.dp))

        Text(
            text = user?.email ?: "",
            style = MaterialTheme.typography.bodyMedium,
            fontSize = 16.sp,
        )

        Spacer(modifier = modifier.weight(1f))

        Button(
            onClick = {
                showNotificationDialog = true
            },
        ) {
            Text(text = "Mandar notificação")
        }

        if (showNotificationDialog) {
            ButtonNotification(modifier, {
                service.showNotification(
                    NotificationData(
                        "https://www.youtube.com/watch?v=dQw4w9WgXcQ",
                    ),
                )
                showNotificationDialog = false
            }, context)
        }

        if (isSignIn) {
            OutlinedButton(
                onClick = {
                    scope.launch {
                        googleAuthClient.signOut()
                        isSignIn = false
                    }
                },
            ) {
                Text(
                    text = "Sair",
                    fontSize = 16.sp,
                    modifier = modifier.padding(
                        horizontal = 24.dp,
                        vertical = 4.dp,
                    ),
                )
            }
        } else {
            OutlinedButton(
                onClick = {
                    scope.launch {
                        isSignIn = googleAuthClient.signIn()
                    }
                },
            ) {
                Text(
                    text = "Entrar com o Google",
                    fontSize = 16.sp,
                    modifier = modifier.padding(
                        horizontal = 24.dp,
                        vertical = 4.dp,
                    ),
                )
            }
        }
    }
}

@Composable
fun ButtonNotification(modifier: Modifier = Modifier, onClickDialogListener: () -> Unit, context: Context) {
    var hasPermission by remember {
        mutableStateOf(
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                ContextCompat.checkSelfPermission(
                    context,
                    Manifest.permission.POST_NOTIFICATIONS,
                ) == PackageManager.PERMISSION_GRANTED
            } else {
                true
            },
        )
    }

    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
    ) { isGranted ->
        hasPermission = isGranted
    }

    if (!hasPermission && Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        LaunchedEffect(Unit) {
            permissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
        }
    } else {
        onClickDialogListener()
    }
}

@Composable
fun AskForPermission(modifier: Modifier = Modifier, onClickDialogListener: () -> Unit) {
    AlertDialog(
        modifier = modifier,
        onDismissRequest = onClickDialogListener,
        title = {
            Text(modifier = modifier, text = "Permissão de notificação")
        },
        text = {
            Text(modifier = modifier, text = "Precisamos da sua permissão para enviar notificações")
        },
        confirmButton = {
            TextButton(
                onClick = onClickDialogListener,
                modifier = modifier,
            ) {
                Text(modifier = modifier, text = "Permitir")
            }
        },
        containerColor = White,
    )
}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    ProfileScreen()
}
