package com.codeliner.habits.ui.habits

import android.app.Application
import androidx.lifecycle.*
import com.codeliner.habits.data.HabitRepository
import com.codeliner.habits.model.Habit
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class HabitViewModel(
    private val habitRepository: HabitRepository,
    application: Application
) : AndroidViewModel(application) {

    private val _habitsData = MutableLiveData<List<Habit>>()
    val habitsData: LiveData<List<Habit>> = _habitsData

    init {
        loadData()
    } // каждый раз при создании viewmodel when load data

    fun loadData() {
        viewModelScope.launch {
            habitRepository.getAllData().collect {
                _habitsData.postValue(it)
            }
        }
    }

    fun insertHabit(habit: Habit) {
        viewModelScope.launch(Dispatchers.IO) {
            habitRepository.insertHabit(habit)
        }
    }
}

class HabitViewModelFactory(
    private val habitRepository: HabitRepository
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return HabitViewModel(habitRepository = habitRepository, application = Application()) as T
    }
}

