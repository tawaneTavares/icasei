package com.example.icasei.presentation.localPush

import kotlinx.serialization.Serializable

@Serializable
data class NotificationData(
    var videoID: String = "",
)
