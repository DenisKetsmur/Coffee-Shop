package com.example.coffeeshop.data.roomDone.employee.room.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.coffeeshop.data.roomDone.clients.room.entities.Position
import com.example.coffeeshop.data.roomDone.employee.entities.SignUpData
import com.example.coffeeshop.data.roomDone.employee.entities.Employee
import com.example.coffeeshop.data.roomDone.workScheduleEntity.entities.WorkScheduleDbEntity

@Entity(
    tableName = "employees",
    indices = [
        Index("email", unique = true)
    ],
    foreignKeys = [
        ForeignKey(
            entity = WorkScheduleDbEntity::class,
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
    val position: Position,
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
        position = position,
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
            position = signUpData.position,
            birthDate = signUpData.birthDate,
            hireDate = signUpData.hireDate,
            workScheduleId = signUpData.workScheduleId,
            status = 1,
        )
    }
}