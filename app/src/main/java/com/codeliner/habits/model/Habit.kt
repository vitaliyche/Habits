package com.codeliner.habits.model

data class Habit(
    val id: Int,
    val checked: Boolean,
    val habitName: String,
    val lastCheckedDate: String,
    val lastWeekCheckCount: Int,
    val targetWeekCheckCount: Int,
    val lastMonthCheckCount: Int
)
