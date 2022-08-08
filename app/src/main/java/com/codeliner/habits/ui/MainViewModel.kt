package com.codeliner.habits.ui

import androidx.lifecycle.ViewModel
import com.codeliner.habits.data.HabitRepository
import com.codeliner.habits.model.Habit

class MainViewModel : ViewModel() {

    private val habitRepository = HabitRepository()

    fun getAllData(): List<Habit> {
        return habitRepository.getAllData()
    }
}