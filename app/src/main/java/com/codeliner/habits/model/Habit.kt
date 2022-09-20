package com.codeliner.habits.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Habit(

    @PrimaryKey(autoGenerate = true)
    val id: Int,
    var checked: Boolean = false,
    val habitName: String,
    var countCheckedDay: Int,
    val targetWeekCheckCount: Int,
    var lastCheckedDate: String = "",
    /*val lastMonthCheckCount: Int*/
)
