package com.codeliner.habits.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class Habit(

    @PrimaryKey(autoGenerate = true)
    val id: Int,
    var checked: Boolean = false,
    val habitName: String,
    var countCheckedDay: Int,
    var targetWeekCheckCount: Int,
    var lastCheckedDate: String = "",
    /*val lastMonthCheckCount: Int*/
): Parcelable
