package com.codeliner.habits.ui.habits

import android.app.Application
import android.content.Context
import android.text.TextUtils
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import com.codeliner.habits.MainActivity
import com.codeliner.habits.NavigationComponent
import com.codeliner.habits.R
import com.codeliner.habits.data.HabitRepository
import com.codeliner.habits.model.Habit
import com.codeliner.habits.ui.theme.GrayText
import com.codeliner.habits.ui.theme.LightGrayBackground
import kotlin.math.round

@Composable
fun AddHabitBottomSheet(mHabitViewModel: HabitViewModel) {


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
                text = "Create Habit",
                fontSize = 20.sp,
                color = Color.Black,
                fontWeight = FontWeight.Bold
            )

            Icon(
                painter = painterResource(id = R.drawable.ic_close),
                contentDescription = "close"
            )
        }

        Text(
            text = "Enter Habit Name",
            style = TextStyle(fontSize = 15.sp),
            color = GrayText,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = 24.dp, bottom = 12.dp)
        )

        var textFieldState by remember { mutableStateOf("") }
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

        var sliderPosition by remember { mutableStateOf(3f) }
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

        Slider(
            value = sliderPosition,
            valueRange = 1F..7F,
            steps = 5,
            onValueChange = { sliderPosition = it }
        )

        Button(
            onClick = {

                val habitName = textFieldState
                val targetDaysPerWeek = sliderPosition.toInt()

                if(inputCheck(habitName)) {
                    //Create Habit Object
                    val habit = Habit(0, habitName, targetDaysPerWeek)
                    // Add Data to Database
                    //mHabitViewModel.addHabit(habit)
                    /*Toast.makeText(this, "Succesfully added", Toast.LENGTH_LONG).show()*/
                    /*navController.navigate(popBackStack)*/
                } else {
                    /*Toast.makeText(this, "Please fill Habit Name field", Toast.LENGTH_LONG).show()*/
                }
            },
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.Black),
            shape = RoundedCornerShape(20.dp),
            modifier = Modifier
                .padding(top = 24.dp)
                .fillMaxWidth()
        ) {

            Text(
                text = "Create",
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


@Preview(showBackground = true)
@Composable
fun AddHabitBottomSheetPreview() {
    AddHabitBottomSheet(mHabitViewModel = HabitViewModel(HabitRepository()))
}