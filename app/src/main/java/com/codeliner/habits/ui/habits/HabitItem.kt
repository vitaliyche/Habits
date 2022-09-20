package com.codeliner.habits.ui.habits

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.codeliner.habits.model.Habit
import com.codeliner.habits.ui.theme.GreenBar
import com.codeliner.habits.ui.theme.LightGrayBackground

@Composable
fun HabitItem( // SOLID - S
    habit: Habit,
    modifier: Modifier = Modifier, // SOLID - O
    checkboxClickCallback:(Boolean) -> Unit
) {
    Column(
        modifier = modifier
    ) {
        
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {

            val checkedState = remember { mutableStateOf(habit.checked) }
            val countCheckedDay = remember { mutableStateOf(habit.countCheckedDay) }

            Checkbox(
                checked = checkedState.value,
                onCheckedChange = {
                        if(it) ++countCheckedDay.value else --countCheckedDay.value
                        checkedState.value = it
                        checkboxClickCallback.invoke(it)
                },
                colors = CheckboxDefaults.colors(checkedColor = GreenBar)
            )

            Column(modifier = Modifier.padding(start = 8.dp)) {
                Text(
                    text = habit.habitName,
                    fontSize = 15.sp
                )

                /*Text(
                    *//*text = habit.lastCheckedDate,*//*
                    fontSize = 12.sp,
                    color = GrayText
                )*/
            }

            Spacer(modifier = Modifier.weight(1f))

            Counter(
                lastWeekCheckCount = countCheckedDay.value,
                targetWeekCheckCount = habit.targetWeekCheckCount
            )
        }

        // TODO: Linear Progress Indicator instead dividers
        Box(
            modifier = Modifier.padding(top = 2.dp, bottom = 8.dp),
        ) {

            Divider(
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
                modifier = Modifier
                    .fillMaxWidth(0.4f)
                    .background(
                        color = GreenBar,
                        shape = RoundedCornerShape(20.dp)
                    )
            )
        }
    }
}

@Composable
private fun Counter(
    lastWeekCheckCount: Int,
    targetWeekCheckCount: Int
) {
    Box(
        modifier = Modifier
            .background(LightGrayBackground, shape = RoundedCornerShape(12.dp))
            .padding(8.dp)
    ) {

        Text(text = "${lastWeekCheckCount}/${targetWeekCheckCount}")

    }
}

@Preview(showBackground = true)
@Composable
private fun HabitItemPreview() {
    HabitItem(previewHabitItem) {}
}

private val previewHabitItem = Habit(
    id = 0,
    checked = false,
    habitName = "Name",
    targetWeekCheckCount = 0,
    countCheckedDay = 0,
    /*lastCheckedDate = "sd",
    lastMonthCheckCount = 0*/
)