package com.example.coffeeshop.data.roomDone.workScheduleEntity.entities

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface WorkScheduleDao {
    @Query("SELECT * FROM work_schedule WHERE employees_id = :employeeId LIMIT 1")
    fun getWorkSchedulesForEmployee(employeeId: Long): Flow<WorkScheduleDbEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWorkSchedule(schedule: WorkScheduleDbEntity)

    @Update
    suspend fun updateWorkSchedule(schedule: WorkScheduleDbEntity)

    @Delete
    suspend fun deleteWorkSchedule(schedule: WorkScheduleDbEntity)
}
