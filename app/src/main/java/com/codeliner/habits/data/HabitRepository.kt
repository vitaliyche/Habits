package com.codeliner.habits.data

import com.codeliner.habits.model.Habit

class HabitRepository(private val habitDao: HabitDao) {

    fun getAllData() = habitDao.getAllData()

    suspend fun insertHabit(habit: Habit) {
        habitDao.insertHabit(habit)
    }
}