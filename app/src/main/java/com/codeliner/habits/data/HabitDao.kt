package com.codeliner.habits.data


import androidx.room.*
import com.codeliner.habits.model.Habit
import kotlinx.coroutines.flow.Flow

@Dao
interface HabitDao {

    @Insert (onConflict =  OnConflictStrategy.IGNORE)
    suspend fun insertHabit(habit: Habit)

    @Query("SELECT * FROM habit ORDER BY id ASC")
    fun getAllData(): Flow<List<Habit>>

    @Query("UPDATE habit SET " +
            "checked=:checked, " +
            "countCheckedDay=:countCheckedDay, " +
            "lastCheckedDate=:lastCheckedDate " +
            "WHERE id=:id"
    )
    suspend fun updateCheckedHabit(id:Int, checked:Boolean, countCheckedDay:Int, lastCheckedDate: String)

    @Update
    suspend fun updateHabit(habit: Habit)

    @Delete
    suspend fun deleteHabit(habit: Habit)
}