package com.example.coffeeshop.data.roomDone.employee.room.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.coffeeshop.data.room.users.work_schedule.Shift
import com.example.coffeeshop.data.roomDone.employee.entities.SignUpData
import com.example.coffeeshop.data.roomDone.employee.entities.Employee
import com.example.coffeeshop.data.roomDone.position.entities.Position

@Entity(
    tableName = "employees",
    indices = [
        Index("email", unique = true)
    ],
    foreignKeys = [
        ForeignKey(
            entity = Position::class,
            parentColumns = ["id"],
            childColumns = ["position_id"],
            onDelete = ForeignKey.SET_NULL
        ),
        ForeignKey(
            entity = Shift::class,
            parentColumns = ["id"],
            childColumns = ["work_schedule_id"],
            onDelete = ForeignKey.SET_NULL
        )
    ]
)
data class EmployeeDbEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "first_name") val firstName: String,
    @ColumnInfo(name = "last_name") val lastName: String,
    val email: String,
    val password:String,
    @ColumnInfo(name = "position_id")val positionId: Int?,
    val phone: String,
    @ColumnInfo(name = "birth_date") val birthDate: Long,
    @ColumnInfo(name = "hire_date") val hireDate: Long,
    @ColumnInfo(name = "work_schedule_id") val workScheduleId: Int?,
    val status: Int
){
    fun toEmployee(): Employee = Employee(
        id = id,
        firstName = firstName,
        lastName = lastName,
        email = email,
        phone = phone,
        password = password,
        positionId = positionId,
        birthDate = birthDate,
        hireDate = hireDate,
        workScheduleId = workScheduleId,
        status = status,
    )

    companion object {
        fun fromSignUpData(signUpData: SignUpData): EmployeeDbEntity = EmployeeDbEntity(
            id = 0,
            firstName = signUpData.firstName,
            lastName = signUpData.lastName,
            email = signUpData.email,
            phone = signUpData.phone,
            password = signUpData.password,
            positionId = signUpData.positionId,
            birthDate = signUpData.birthDate,
            hireDate = signUpData.hireDate,
            workScheduleId = signUpData.workScheduleId,
            status = 1,
        )
    }
}