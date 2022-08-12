package com.codeliner.habits.ui.habits

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.codeliner.habits.data.HabitRepository
import com.codeliner.habits.model.Habit

class HabitViewModel : ViewModel() {

    private val habitRepository = HabitRepository()

    fun getAllData(): List<Habit> {
        return habitRepository.getAllData()
    }

    /*private val readAllData: LiveData<List<Habit>>
    private var habitRepository: HabitRepository

    init {
        val habitDao = HabitDatabase.getDataBase(application).habitDao()
        habitRepository = HabitRepository(habitDao)
        readAllData = habitRepository.readAllData()
    }

    fun insertHabit(habit: Habit) {
        viewModelScope.launch(Dispatchers.IO) {
            habitRepository.insertHabit(habit)
        }
    }

    fun getAllData(): LiveData<List<Habit>> {
        return habitRepository.readAllData()
    }*/
}

