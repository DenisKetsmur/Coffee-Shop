package com.example.coffeeshop.data.user

import androidx.room.PrimaryKey
import com.example.coffeeshop.R
import com.example.coffeeshop.data.HasId
import com.example.coffeeshop.data.ItemViewModel
import com.example.coffeeshop.data.RepositoryImpl
import com.example.coffeeshop.data.filled.getRandomDate
import com.example.coffeeshop.data.goods.entities.Goods
import com.example.coffeeshop.data.goods.entities.goodsList
import com.example.coffeeshop.data.roomDone.position.entities.Position
import com.example.coffeeshop.data.supplier.Order

sealed class User(
    @PrimaryKey(autoGenerate = true) override val id:Int,
    open val name: String,
    open val surname: String,
    open val email: String,
    open val password: String,
    open val position: Position,
    open val phoneNumber: String,

    ):HasId {
    companion object {
        private var idCounter = 0
        private fun generateId(): Int {
            return ++idCounter
        }
    }
    data class Employee(
        override val id: Int = generateId(),
        override val name: String = "",
        override val surname: String = "",
        override val email: String = "",
        override val password: String = "",
        override val position: Position = Position.MANAGER,
        override val phoneNumber: String = "+380345345",
        val age: Int? = null,
        val startJob: Long = 0,
        val photo: Int = R.mipmap.face_photo,
        val isActive: Boolean = true,
        var workSchedule: WorkSchedule = WorkSchedule.NONE,
    ) : User(id, name, surname, email, password, position, phoneNumber){
        companion object {
            private var idCounter = 0
            private fun generateId(): Int {
                return ++idCounter
            }
        }
    }
    data class Client(
        override val id: Int = generateId(),
        override val name: String = "",
        override val surname: String = "",
        override val email: String = "",
        override val password: String = "",
        override val position: Position = Position.CLIENT,
        override val phoneNumber: String = "+380345345",

        val orders:List<Order<Goods>> = listOf()
    ) : User(id, name, surname, email, password, position, phoneNumber)
}


val employees = listOf(
    User.Employee(name = "Олександр", surname = "Коваль", email = "alex.koval@example.com", phoneNumber = "+380671234567", age = 30, position = Position.MANAGER),
    User.Employee(name = "Марина", surname = "Іваненко", email = "marina.ivanenko@example.com", phoneNumber = "+380931112233", age = 28, position = Position.MANAGER),
    User.Employee(name = "Володимир", surname = "Петренко", email = "v.petrenko@example.com", phoneNumber = "+380503332211", age = 35, position = Position.MANAGER),
    User.Employee(name = "Андрій", surname = "Сидоренко", email = "andriy.sydorenko@example.com", phoneNumber = "+380987654321", age = 27, position = Position.MANAGER),
    User.Employee(name = "Тетяна", surname = "Мельник", email = "t.melnyk@example.com", phoneNumber = "+380661223344", age = 32, position = Position.MANAGER),
    User.Employee(name = "Ігор", surname = "Дяченко", email = "i.dyachenko@example.com", phoneNumber = "+380952341567", age = 29, position = Position.MANAGER),
    User.Employee(name = "Олена", surname = "Шевченко", email = "olena.shevchenko@example.com", phoneNumber = "+380732349876", age = 31, position = Position.MANAGER),
    User.Employee(name = "Максим", surname = "Гаврилюк", email = "maksym.gavrylyuk@example.com", phoneNumber = "+380662349876", age = 26, position = Position.ADMINISTRATOR),
    User.Employee(name = "Світлана", surname = "Литвин", email = "svitlana.lytvyn@example.com", phoneNumber = "+380682345678", age = 33, position = Position.MANAGER),
    User.Employee(name = "Роман", surname = "Захарченко", email = "r.zakharchenko@example.com", phoneNumber = "+380502345678", age = 29, position = Position.ADMINISTRATOR)
)

val clients = listOf(
    User.Client(name = "Артем", surname = "Кузьменко", email = "artem.kuzmenko@example.com", phoneNumber = "+380671234111", orders = listOf(
        Order(
            date = getRandomDate(),
            items = goodsList,
            total = 10000.0
        ),
        Order(
            date = getRandomDate(),
            items = goodsList,
            total = 103453.0
        ),
        Order(
            date = getRandomDate(),
            items = goodsList,
            total = 34535.0
        ),
        Order(
            date = getRandomDate(),
            items = goodsList,
            total = 436.0
        ),
        Order(
            date = getRandomDate(),
            items = goodsList,
            total = 434.0
        ),
        Order(
            date = getRandomDate(),
            items = goodsList,
            total = 10000.0
        )
    )),
    User.Client(name = "Наталія", surname = "Романенко", email = "nataliya.romanenko@example.com", phoneNumber = "+380931112999"),
    User.Client(name = "Дмитро", surname = "Поляков", email = "dmytro.polyakov@example.com", phoneNumber = "+380503332888"),
    User.Client(name = "Оксана", surname = "Григоренко", email = "oksana.hryhorenko@example.com", phoneNumber = "+380987654777"),
    User.Client(name = "Євген", surname = "Семенов", email = "yevhen.semenov@example.com", phoneNumber = "+380661223666"),
    User.Client(name = "Ірина", surname = "Федоренко", email = "iryna.fedorenko@example.com", phoneNumber = "+380952341555"),
    User.Client(name = "Сергій", surname = "Кравченко", email = "serhii.kravchenko@example.com", phoneNumber = "+380732349444"),
    User.Client(name = "Вікторія", surname = "Ковальчук", email = "viktoriya.kovalchuk@example.com", phoneNumber = "+380662349333"),
    User.Client(name = "Павло", surname = "Тимошенко", email = "pavlo.tymoshenko@example.com", phoneNumber = "+380682345222"),
    User.Client(name = "Юлія", surname = "Гончаренко", email = "yulia.honcharenko@example.com", phoneNumber = "+380502345111")
)


class ClientViewModel : ItemViewModel<User.Client>(
    RepositoryImpl(clients)
)

class EmployeeViewModel : ItemViewModel<User.Employee>(
    RepositoryImpl(employees)
)





