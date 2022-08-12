package com.codeliner.habits.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.codeliner.habits.model.CheckedItem
import com.codeliner.habits.model.Habit

@Database(
    entities = [
        Habit::class,
        /*CheckedItem::class*/
               ],
    version = 1,
)
abstract class HabitDatabase: RoomDatabase() {

    /*abstract fun habitDao(): HabitDao

    companion object {

        @Volatile // предотвратить гонку потоков
        private var INSTANCE: HabitDatabase? = null

        fun getDataBase(context: Context): HabitDatabase{

            // предотвращает создание одной БД двумя потоками одновременно
            synchronized(this) {

                return INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    HabitDatabase::class.java,
                    "habit_database"
                ).build().also {
                    INSTANCE = it
                }

            *//*val tempInstance = INSTANCE

            if (tempInstance != null) {
                return tempInstance
            }*//*

                *//*val instance = Room.databaseBuilder(
                    context.applicationContext,
                    HabitDatabase::class.java,
                    "habit_database"
                ).build()

                INSTANCE = instance
                return instance*//*
            }

        }
    }*/
}