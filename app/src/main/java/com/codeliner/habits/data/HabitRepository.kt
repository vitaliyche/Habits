package com.codeliner.habits.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.codeliner.habits.model.Habit

class HabitRepository() {

    fun getAllData(): List<Habit> {
        return listOf(
            Habit(
                id =  0,
                //checked = true,
                habitName = "Встать до 07 утра",
                //lastCheckedDate = "Пятница, 05 Август",
                //lastWeekCheckCount = 2,
                targetWeekCheckCount = 4,
                //lastMonthCheckCount = 10
            ),
            Habit(
                id =  1,
                //checked = false,
                habitName = "Учить английский",
                //lastCheckedDate = "Суббота, 06 Август",
                //lastWeekCheckCount = 3,
                targetWeekCheckCount = 3,
                //lastMonthCheckCount = 1
            )
        )
    }

    //fun getAllData(habitDao: HabitDao): LiveData<List<Habit>> = habitDao.getAllData()

    suspend fun insertHabit(habit: Habit) {
        insertHabit(habit)
    }

}