package com.codeliner.habits

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.codeliner.habits.model.Habit
import com.codeliner.habits.ui.habits.CreateOrEditHabitScreen
import com.codeliner.habits.ui.habits.HabitsScreen


@ExperimentalMaterialApi
@Composable
fun NavigationComponent(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.HabitsScreen.route) {
        composable(route = Screen.HabitsScreen.route) {
            HabitsScreen(navController = navController)
        }
        composable(route = Screen.CreateOrAddHabitScreen.route) {
            val habit = navController.previousBackStackEntry?.savedStateHandle?.get<Habit>(Constant.ADD_EDIT_HABIT_KEY)
            CreateOrEditHabitScreen(navController = navController, habit = habit)
        }
    }
}

sealed class Screen(val route: String) {
    object HabitsScreen : Screen("habits_screen")
    object CreateOrAddHabitScreen : Screen("create_or_edit_habit_screen/{${Constant.ADD_EDIT_HABIT_KEY}}")
}

object Constant {
    const val ADD_EDIT_HABIT_KEY = "add_edit_habit_key"
}