package com.codeliner.habits.ui.habits

import android.app.Application
import androidx.lifecycle.*
import com.codeliner.habits.data.HabitRepository
import com.codeliner.habits.model.Habit
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

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
                val list:List<Habit> = it.map { habit ->
                    if(habit.lastCheckedDate != "") {
                        val formatter: DateFormat = SimpleDateFormat(patternDate, Locale.ENGLISH)
                        val currentDateS = getCurrentDate()
                        val savedDateS = habit.lastCheckedDate
                        val savedDate = formatter.parse(savedDateS)
                        val currentDate = formatter.parse(currentDateS)
                        habit.checked = currentDate == savedDate && habit.countCheckedDay > 0
                        // 0 - currentDate is equal to savedDate, >0 - currentDate is after savedDate, <0 currentDate is before savedDate
                    }
                    habit
                }
                _habitsData.postValue(list)
            }
        }
    }

    fun insertHabit(habit: Habit) {
        viewModelScope.launch(Dispatchers.IO) {
            habitRepository.insertHabit(habit)
        }
    }

    fun updateCheckedHabit(habit: Habit) {
        viewModelScope.launch(Dispatchers.IO) {
            habitRepository.updateCheckedHabit(habit)
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

