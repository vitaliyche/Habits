package com.codeliner.habits.model.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.codeliner.habits.model.CheckedItem
import com.codeliner.habits.model.Habit

data class HabitWithCheckedItem (

    @Embedded val habit: Habit,
    @Relation(
        parentColumn = "habitName",
        entityColumn = "habitName"
    )
    val checkedItems: List<CheckedItem>
)