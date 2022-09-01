package com.codeliner.habits.ui.habits

import android.app.Application
import androidx.lifecycle.*
import com.codeliner.habits.data.HabitDatabase
import com.codeliner.habits.data.HabitRepository
import com.codeliner.habits.model.Habit
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HabitViewModel(
    private var habitRepository: HabitRepository,
    application: Application
) : AndroidViewModel(application) {

    var habitsData: List<Habit>

    init {
        //val habitDao = HabitDatabase.getDataBase(application).habitDao()
        habitRepository = HabitRepository()
        habitsData = habitRepository.getAllData()
        //loadData(application)
    } // каждый раз при создании viewmodel when load data

    private fun loadData(application: Application) {
        /*habitsData.postValue(habitRepository.getAllData()) // change threat automatically*/
    }

    fun insertHabit(habit: Habit) {
        viewModelScope.launch(Dispatchers.IO) {
            habitRepository.insertHabit(habit)
        }
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

class HabitViewModelFactory(
    private val habitRepository: HabitRepository
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return HabitViewModel(habitRepository, application = Application()) as T
    }
}

