package com.codeliner.habits

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.ViewModelProvider
import com.codeliner.habits.data.HabitDao
import com.codeliner.habits.ui.habits.HabitViewModel
import com.codeliner.habits.ui.habits.HabitsScreen

class MainActivity : ComponentActivity() {

    /*private lateinit var mHabitViewModel: HabitViewModel*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HabitsScreen()

            //ViewModelProvider(this).get(HabitViewModel::class.java)

            /*mHabitViewModel = ViewModelProvider(this).get(HabitViewModel::class.java)*/
        }
    }
}