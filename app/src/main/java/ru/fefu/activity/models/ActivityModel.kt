package ru.fefu.activity.models

import java.time.LocalDateTime

data class ActivityModel(
    val distance: String,
    val activityType: String,
    val startTime: LocalDateTime,
    val endTime: LocalDateTime,
)

