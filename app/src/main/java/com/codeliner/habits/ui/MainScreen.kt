package com.codeliner.habits.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.codeliner.habits.R

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel = viewModel()
) {

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                text = "Habits",
                style = TextStyle(fontSize = 20.sp),
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.weight(1f))


            Icon(
                painter = painterResource(id = R.drawable.ic_add),
                contentDescription = "add habit",
                tint = Color.DarkGray
            )

            Icon(
                painter = painterResource(id = R.drawable.ic_filter),
                contentDescription = "add habit",
                Modifier.padding(start = 16.dp),
                tint = Color.DarkGray
            )

        }

        Divider(Modifier.padding(vertical = 16.dp))

        LazyColumn {
            items(items = viewModel.getAllData()) { habit ->
                HabitItem(
                    habit = habit
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    MainScreen(
        modifier = Modifier,
        viewModel = MainViewModel()
    )
}