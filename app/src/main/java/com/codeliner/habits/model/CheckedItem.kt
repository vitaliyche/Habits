package com.codeliner.habits.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CheckedItem(

    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val habitName: String,
    val date: String
)
