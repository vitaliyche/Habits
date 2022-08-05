package com.codeliner.habits.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Checkbox
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.codeliner.habits.model.Habit
import com.codeliner.habits.ui.theme.GrayText
import com.codeliner.habits.ui.theme.GreenBar
import com.codeliner.habits.ui.theme.LightGrayBackground

@Composable
fun HabitItem(habit: Habit) {

    Column() {
        
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {

            val checkedState = remember { mutableStateOf(true) }
            Checkbox(
                checked = checkedState.value,
                onCheckedChange = {checkedState.value = it}
            )

            Column(modifier = Modifier.padding(start = 8.dp)) {
                Text(
                    text = habit.habitName,
                    fontSize = 15.sp
                )

                Text(
                    text = habit.lastCheckedDate,
                    fontSize = 12.sp,
                    color = GrayText
                )

            }

            Spacer(modifier = Modifier.weight(1f))

            Box(
                modifier = Modifier
                    .background(LightGrayBackground, shape = RoundedCornerShape(12.dp))
                    .padding(8.dp)
            ) {

                Text(text = "${habit.lastWeekCheckCount}/${habit.targetWeekCheckCount}")

            }
        }

        Box(
            modifier = Modifier.padding(top = 1.dp, bottom = 8.dp),
        ) {

            Divider(
                thickness = 4.dp,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Gray, shape = RoundedCornerShape(20.dp))
            )

            // TODO: Ширина цветного прогрессбара равна доле выполненных привычек за месяц
            // val lastMonthCheckPercent = habit.lastMonthCheckCount/(30*(habit.targetWeekCheckCount/7)).toFloat()
            Divider(
                thickness = 4.dp,
                modifier = Modifier
                    .fillMaxWidth(0.4f)
                    .background(GreenBar, shape = RoundedCornerShape(20.dp))
            )

        }

    }

}

/*
@Preview (showBackground = true)
@Composable
fun HabitItemPreview() {
    HabitItem()
}*/
