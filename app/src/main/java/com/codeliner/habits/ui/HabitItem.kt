package com.codeliner.habits.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.codeliner.habits.model.Habit
import com.codeliner.habits.ui.theme.GrayText
import com.codeliner.habits.ui.theme.GreenBar
import com.codeliner.habits.ui.theme.LightGrayBackground

@Composable
fun HabitItem( // SOLID - S
    habit: Habit,
    modifier: Modifier = Modifier, // SOLID - O
) {

    Column(
        modifier = modifier
    ) {
        
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {

            val checkedState = remember { mutableStateOf(true) }

            Checkbox(
                checked = checkedState.value,
                onCheckedChange = {checkedState.value = it},
                colors = CheckboxDefaults.colors(GreenBar)
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

            Counter(
                lastWeekCheckCount = habit.lastWeekCheckCount,
                targetWeekCheckCount = habit.targetWeekCheckCount
            )
        }

        // TODO: Linear Progress Indicator instead Boxes
        Box(
            modifier = Modifier.padding(top = 2.dp, bottom = 8.dp),
        ) {

            Divider(
                thickness = 1.dp,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = LightGrayBackground,
                        shape = RoundedCornerShape(20.dp)
                    )
            )

            // TODO: длина цветного прогрессбара равна доле выполненных привычек за месяц
            // val lastMonthCheckPercent = habit.lastMonthCheckCount/(30*(habit.targetWeekCheckCount/7)).toFloat()
            Divider(
                thickness = 1.dp,
                modifier = Modifier
                    .fillMaxWidth(0.4f)
                    .background(GreenBar, shape = RoundedCornerShape(20.dp))
            )
        }
    }
}

@Composable
private fun Counter(
    lastWeekCheckCount: Int,
    targetWeekCheckCount: Int,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .background(LightGrayBackground, shape = RoundedCornerShape(12.dp))
            .padding(8.dp)
    ) {

        Text(text = "${lastWeekCheckCount}/${targetWeekCheckCount}")

    }
}

@Preview(showBackground = true)
@Composable
private fun HabitItemPreview() {
    HabitItem(previewHabitItem)
}

private val previewHabitItem = Habit(
    id =  0,
    checked = false,
    habitName = "Name",
    lastCheckedDate = "sd",
    lastWeekCheckCount = 0,
    targetWeekCheckCount = 0,
    lastMonthCheckCount = 0
)