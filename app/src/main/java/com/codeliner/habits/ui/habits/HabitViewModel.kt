package com.codeliner.habits.ui.habits


import androidx.lifecycle.*
import com.codeliner.habits.data.HabitRepository
import com.codeliner.habits.model.Habit
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@HiltViewModel
class HabitViewModel @Inject constructor(
    private val habitRepository: HabitRepository,
) : ViewModel() {

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

    fun updateHabit(habit: Habit) {
        viewModelScope.launch {
            habitRepository.updateHabit(habit)
        }
    }

    fun deleteHabit(habit: Habit) {
        viewModelScope.launch {
            habitRepository.deleteHabit(habit)
        }
    }
}

