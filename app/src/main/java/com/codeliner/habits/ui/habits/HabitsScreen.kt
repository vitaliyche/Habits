package com.codeliner.habits.ui.habits

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.codeliner.habits.Constant
import com.codeliner.habits.R
import com.codeliner.habits.Screen
import com.codeliner.habits.model.Habit
import com.codeliner.habits.noRippleClickable
import kotlinx.coroutines.launch

@Composable
fun HabitsScreen(
    navController: NavController,
) {
    val scope = rememberCoroutineScope()
    val viewModel: HabitViewModel = hiltViewModel()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(
                    text = "Habits",
                    style = TextStyle(fontSize = 24.sp),
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.Cursive
                )

                Spacer(modifier = Modifier.weight(1f))

                Icon(
                    painter = painterResource(id = R.drawable.ic_add),
                    contentDescription = "add habit",
                    tint = Color.DarkGray,
                    modifier = Modifier
                        .size(28.dp)
                        .noRippleClickable {
                            scope.launch {
                                openAddEditScreen(habit = null, navController = navController)
                            }
                        }
                )

                Icon(
                    modifier = Modifier.padding(start = 16.dp),
                    painter = painterResource(id = R.drawable.ic_filter),
                    contentDescription = "add habit",
                    tint = Color.DarkGray
                )
            }

            Divider(Modifier.padding(vertical = 16.dp))

            val habits by viewModel.habitsData.observeAsState()
            LazyColumn {
                items(items = habits ?: arrayListOf()) { habit ->
                    HabitItem(habit = habit, checkboxClickCallback = { value ->
                        if (value) {
                            ++habit.countCheckedDay
                            habit.lastCheckedDate = getCurrentDate()
                        } else {
                            --habit.countCheckedDay
                            habit.lastCheckedDate = ""
                        }
                        habit.checked = value
                        viewModel.updateCheckedHabit(habit)

                    }, clickItem = {
                        scope.launch {
                            openAddEditScreen(habit = habit, navController = navController)
                        }
                    })
                }
            }
    }
}


fun openAddEditScreen(habit: Habit?, navController: NavController) {
    navController.currentBackStackEntry?.savedStateHandle?.set(Constant.ADD_EDIT_HABIT_KEY, habit)
    navController.navigate(Screen.CreateOrAddHabitScreen.route)
}
