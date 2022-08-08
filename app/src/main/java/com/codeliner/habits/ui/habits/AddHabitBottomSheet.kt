package com.codeliner.habits.ui.habits

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.codeliner.habits.R
import com.codeliner.habits.ui.theme.LightGrayBackground

@Composable
fun AddHabitBottomSheet() {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                text = "Add Habit",
                fontSize = 20.sp,
                color = Color.Black,
                fontWeight = FontWeight.Bold
            )

            Icon(
                painter = painterResource(id = R.drawable.ic_close),
                contentDescription = "close"
            )
        }

        Box( // background
            modifier = Modifier
                .fillMaxWidth()
                .background(color = LightGrayBackground, shape = RoundedCornerShape(12.dp))
                .padding(20.dp)
        ) {

            Text(
                text = "Habit Name",
                style = TextStyle(fontSize = 18.sp),
                color = Color.LightGray,
                textAlign = TextAlign.Center
            )

        }

        Button(
            onClick = {
                TODO("Переход на экран привычек")
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

@Preview(showBackground = true)
@Composable
fun AddHabitBottomSheetPreview() {
    AddHabitBottomSheet()
}