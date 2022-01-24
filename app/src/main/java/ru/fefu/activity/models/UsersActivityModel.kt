package ru.fefu.activity.models

import java.time.LocalDateTime

data class UsersActivityModel(
    val distance: String,
    val activityType: String,
    val startTime: LocalDateTime,
    val endTime: LocalDateTime,
    val username: String,
)
