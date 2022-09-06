package com.codeliner.habits

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.rememberNavController
import com.codeliner.habits.data.HabitDao
import com.codeliner.habits.data.HabitDatabase
import com.codeliner.habits.data.HabitRepository
import com.codeliner.habits.ui.habits.HabitViewModel
import com.codeliner.habits.ui.habits.HabitsScreen

class MainActivity : ComponentActivity() {

    /*private lateinit var mHabitViewModel: HabitViewModel*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            val habitDao = HabitDatabase.getDataBase(application).habitDao()
            HabitsScreen(habitRepository = HabitRepository(habitDao), navController = navController)

            //ViewModelProvider(this).get(HabitViewModel::class.java)

            /*mHabitViewModel = ViewModelProvider(this).get(HabitViewModel::class.java)*/
        }
    }
}