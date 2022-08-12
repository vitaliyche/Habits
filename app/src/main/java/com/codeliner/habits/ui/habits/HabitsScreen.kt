package com.codeliner.habits.ui

import android.app.Application
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.codeliner.habits.R
import com.codeliner.habits.ui.habits.AddHabitBottomSheet
import com.codeliner.habits.ui.habits.HabitViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class, ExperimentalAnimationApi::class)
@Composable
fun HabitsScreen(
    modifier: Modifier = Modifier,
    viewModel: HabitViewModel = viewModel()
) {

    val bottomSheetState =
        rememberBottomSheetState(initialValue = BottomSheetValue.Collapsed)
    val scaffoldState =
        rememberBottomSheetScaffoldState(bottomSheetState = bottomSheetState)
    val scope = rememberCoroutineScope()

    BottomSheetScaffold(
        sheetPeekHeight = 0.dp,
        scaffoldState = scaffoldState,
        sheetContent = {
                AddHabitBottomSheet(viewModel)
        }
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
                        .clickable {
                            scope.launch {
                                if (bottomSheetState.isCollapsed) {
                                    bottomSheetState.expand()
                                } else {
                                    bottomSheetState.collapse()
                                }
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

            //TODO: вывести значения из базы в список
            LazyColumn() {
                items(items = viewModel.getAllData()) { habit ->
                    HabitItem(habit = habit)
                }
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun HabitsScreenPreview() {
    HabitsScreen(
        modifier = Modifier,
        viewModel = HabitViewModel()
    )
}