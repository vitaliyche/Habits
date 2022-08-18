package com.codeliner.habits.ui.habits

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.codeliner.habits.data.HabitRepository
import com.codeliner.habits.model.Habit

class HabitViewModel(
    private val habitRepository: HabitRepository
) : ViewModel() {

    val habitsData: MutableLiveData<List<Habit>> = MutableLiveData()

    init {
        loadData()
    } // каждый раз при создании viewmodel when load data

    private fun loadData() {
        habitsData.postValue(habitRepository.getAllData()) // change threat automatically
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
        return HabitViewModel(habitRepository) as T
    }
}

