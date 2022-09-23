package com.codeliner.habits.ui.habits

import android.text.TextUtils
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.codeliner.habits.Constant
import com.codeliner.habits.R
import com.codeliner.habits.model.Habit
import com.codeliner.habits.ui.theme.GrayText
import com.codeliner.habits.ui.theme.GreenBar
import com.codeliner.habits.ui.theme.LightGrayBackground
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

const val patternDate = "dd/MM/yyyy"

@Composable
fun CreateOrEditHabitScreen(
    navController: NavController,
    habit: Habit? = null
) {
    val viewModel: HabitViewModel = hiltViewModel()
    val title = if (habit == null) "Create Habit" else "Edit Habit"
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 24.dp)
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                text = title,
                fontSize = 20.sp,
                color = Color.Black,
                fontWeight = FontWeight.Bold
            )

            Row() {
                AnimatedVisibility(visible = habit != null) {
                    IconButton(onClick = {
                        viewModel.deleteHabit(habit!!)
                        navController.popBackStack()
                    }) {
                        Icon(
                            imageVector = Icons.Filled.Delete,
                            contentDescription = "close"
                        )
                    }
                }

                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_close),
                        contentDescription = "close"
                    )
                }
            }
        }

        Text(
            text = "Enter Habit Name",
            style = TextStyle(fontSize = 15.sp),
            color = GrayText,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(vertical = 12.dp)
        )

        var textFieldState by remember { mutableStateOf(habit?.habitName ?: "") }
        val focusManager = LocalFocusManager.current

        BasicTextField(
            value = textFieldState,
            onValueChange = { newText ->
                textFieldState = newText
            },
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .background(color = LightGrayBackground, shape = RoundedCornerShape(12.dp))
                .padding(20.dp),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(
                onDone = { focusManager.clearFocus() },
            )
        )

        Text(
            text = "Select Days per Week",
            style = TextStyle(fontSize = 15.sp),
            color = GrayText,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = 24.dp, bottom = 12.dp)
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Text(text = "1", fontSize = 15.sp)
            Text(text = "2", fontSize = 15.sp)
            Text(text = "3", fontSize = 15.sp)
            Text(text = "4", fontSize = 15.sp)
            Text(text = "5", fontSize = 15.sp)
            Text(text = "6", fontSize = 15.sp)
            Text(text = "7", fontSize = 15.sp)
        }
        var sliderPosition by remember { mutableStateOf(habit?.targetWeekCheckCount?.toFloat() ?: 3f) }

        Slider(
            value = sliderPosition,
            valueRange = 1F..7F,
            steps = 5,
            onValueChange = {
                navController.previousBackStackEntry?.savedStateHandle?.get<Habit>(Constant.ADD_EDIT_HABIT_KEY)?.targetWeekCheckCount = it.toInt()
                sliderPosition = it
            },
            onValueChangeFinished = { },
            colors = SliderDefaults.colors(
                thumbColor = GreenBar,
                activeTrackColor = GreenBar,
                inactiveTrackColor = GreenBar,
                activeTickColor = Color.White,
                inactiveTickColor = Color.White
            )
        )

        Button(
            onClick = {
                val habitName = textFieldState
                val targetDaysPerWeek = sliderPosition.toInt()
                val countCheckedDay = habit?.countCheckedDay ?: 0
                val checked = habit?.checked ?: false
                val habitId = habit?.id ?: 0
                if (inputCheck(habitName)) {
                    //Create or Edit Habit Object
                    val currentDate = getCurrentDate()
                    val hab = Habit(habitId, checked, habitName, countCheckedDay, targetDaysPerWeek.toInt(), currentDate)
                    // Add Data to Database
                    if (habit == null) {
                        viewModel.insertHabit(hab)
                    } else {
                        viewModel.updateHabit(hab)
                    }
                    navController.popBackStack()
                } else {

                    Toast.makeText(context, "Please fill Habit Name field", Toast.LENGTH_LONG).show()
                }
            },
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.Black),
            shape = RoundedCornerShape(20.dp),
            modifier = Modifier
                .padding(top = 24.dp)
                .fillMaxWidth()
        ) {

            Text(
                text = if (habit == null) "Create" else "Edit",
                modifier = Modifier.padding(vertical = 20.dp),
                style = TextStyle(fontSize = 16.sp),
                color = Color.White,
                textAlign = TextAlign.Center
            )

        }
    }
}

fun inputCheck(habitName: String): Boolean {
    return !(TextUtils.isEmpty(habitName))
}

fun getCurrentDate(): String {
    val formatter: DateFormat = SimpleDateFormat(patternDate, Locale.ENGLISH)
    val today = Date()
    return formatter.format(today)
}


@Preview(showBackground = true)
@Composable
fun CreateOrEditHabitScreenPreview() {
    CreateOrEditHabitScreen(navController = rememberNavController())
}
