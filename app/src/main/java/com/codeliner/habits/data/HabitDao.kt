package com.codeliner.habits.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.codeliner.habits.model.CheckedItem
import com.codeliner.habits.model.Habit
import com.codeliner.habits.model.relations.HabitWithCheckedItem

@Dao
interface HabitDao {

    /*@Insert (onConflict =  OnConflictStrategy.IGNORE)
    suspend fun insertHabit(habit: Habit)

    @Query("SELECT * FROM habit ORDER BY id ASC")
    fun readAllData(): LiveData<List<Habit>>

    @Insert
    suspend fun insertCheckedItem(checkedItem: CheckedItem)

    @Transaction // для потокобезопасности
    @Query("SELECT * FROM habit WHERE habitName = :habitName")
    suspend fun getCheckedHabitWithHabitName(habitName: String): List<HabitWithCheckedItem>*/


}