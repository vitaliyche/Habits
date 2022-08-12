package com.codeliner.habits

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.codeliner.habits.ui.HabitsScreen

class MainActivity : ComponentActivity() {

    /*private lateinit var mHabitViewModel: HabitViewModel*/


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HabitsScreen()

            /*mHabitViewModel = ViewModelProvider(this).get(HabitViewModel::class.java)*/
        }
    }
}