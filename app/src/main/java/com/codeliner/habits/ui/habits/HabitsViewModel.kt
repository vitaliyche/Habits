package com.codeliner.habits.ui.habits

import androidx.lifecycle.ViewModel
import com.codeliner.habits.data.HabitRepository
import com.codeliner.habits.model.Habit

class HabitsViewModel : ViewModel() {

    private val habitRepository = HabitRepository()

    fun getAllData(): List<Habit> {
        return habitRepository.getAllData()
    }
}