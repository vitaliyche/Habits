package com.codeliner.habits.data

import com.codeliner.habits.model.Habit
import javax.inject.Inject

class HabitRepository @Inject constructor(private val habitDao: HabitDao) {

    fun getAllData() = habitDao.getAllData()

    suspend fun insertHabit(habit: Habit) {
        habitDao.insertHabit(habit)
    }

    suspend fun updateCheckedHabit(habit: Habit) {
        habitDao.updateCheckedHabit(habit.id, habit.checked, habit.countCheckedDay, habit.lastCheckedDate)
    }

    suspend fun updateHabit(habit: Habit) {
        habitDao.updateHabit(habit)
    }

    suspend fun deleteHabit(habit: Habit) {
        habitDao.deleteHabit(habit)
    }
}