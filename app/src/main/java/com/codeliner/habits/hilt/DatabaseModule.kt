package com.codeliner.habits.hilt

import android.content.Context
import com.codeliner.habits.data.HabitDao
import com.codeliner.habits.data.HabitDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

    @Provides
    @Singleton
    fun provideHabitDatabase(@ApplicationContext context: Context): HabitDatabase {
        return HabitDatabase.getDataBase(context)
    }

    @Provides
    fun provideHabitDao(habitDatabase: HabitDatabase): HabitDao {
        return habitDatabase.habitDao()
    }
}