package com.example.coffeeshop.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.coffeeshop.data.roomDone.category.CategoryDao
import com.example.coffeeshop.data.roomDone.category.CategoryDbEntity
import com.example.coffeeshop.data.roomDone.category.CategoryTypeConverter
import com.example.coffeeshop.data.roomDone.clients.room.ClientDao
import com.example.coffeeshop.data.roomDone.clients.room.entities.ClientDbEntity
import com.example.coffeeshop.data.roomDone.employee.room.EmployeeDao
import com.example.coffeeshop.data.roomDone.employee.room.entities.EmployeeDbEntity
import com.example.coffeeshop.data.roomDone.goods.room.GoodDao
import com.example.coffeeshop.data.roomDone.goods.room.entities.GoodBdEntity
import com.example.coffeeshop.data.roomDone.order.room.OrderDao
import com.example.coffeeshop.data.roomDone.order.room.OrderItemDao
import com.example.coffeeshop.data.roomDone.order.room.entities.OrderDbEntity
import com.example.coffeeshop.data.roomDone.order.room.entities.OrderItemDbEntity
import com.example.coffeeshop.data.roomDone.order.room.entities.OrderItemEntity
import com.example.coffeeshop.data.roomDone.position.room.entities.PositionDbEntity
import com.example.coffeeshop.data.roomDone.position.room.PositionDao
import com.example.coffeeshop.data.roomDone.products.room.ProductDao
import com.example.coffeeshop.data.roomDone.products.room.entities.ProductDbEntity
import com.example.coffeeshop.data.roomDone.supplier.entities.SupplierDao
import com.example.coffeeshop.data.roomDone.supplier.entities.SupplierDbEntity
import com.example.coffeeshop.data.roomDone.unit.UnitDao
import com.example.coffeeshop.data.roomDone.unit.UnitDbEntity
import com.example.coffeeshop.data.roomDone.workScheduleEntity.entities.WorkScheduleDao
import com.example.coffeeshop.data.roomDone.workScheduleEntity.entities.WorkScheduleDbEntity

@Database(
    version = 1,
    entities = [
        ClientDbEntity::class,
        GoodBdEntity::class,
        ProductDbEntity::class,
        EmployeeDbEntity::class,
        PositionDbEntity::class,
        UnitDbEntity::class,
        CategoryDbEntity::class,
        SupplierDbEntity::class,
        WorkScheduleDbEntity::class,
        OrderDbEntity::class,
        OrderItemDbEntity::class,
    ]
)
@TypeConverters(CategoryTypeConverter::class)
abstract class CoffeeShopDatabase : RoomDatabase() {

    abstract fun getClientDao(): ClientDao
    abstract fun getGoodsDao(): GoodDao
    abstract fun getProductDao(): ProductDao
    abstract fun getEmployeesDao(): EmployeeDao
    abstract fun getPosition(): PositionDao
    abstract fun getUnitDao(): UnitDao
    abstract fun categoryDao(): CategoryDao
    abstract fun supplierDao(): SupplierDao
    abstract fun workScheduleDao(): WorkScheduleDao
    abstract fun orderDao(): OrderDao
    abstract fun orderItemDao(): OrderItemDao
}

object AppDatabaseProvider {

    private var INSTANCE: CoffeeShopDatabase? = null

    fun getDatabase(context: Context): CoffeeShopDatabase {
        return INSTANCE ?: synchronized(this) {
            val instance = Room.databaseBuilder(
                context.applicationContext,
                CoffeeShopDatabase::class.java,
                "coffee_shop.db"
            )
                .addCallback(object : RoomDatabase.Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                        db.execSQL(
                            """
                            INSERT INTO units (name) VALUES 
                            ('кілограм'), ('грам'), ('літр'), ('мілілітр'),
                            ('штука'), ('порція'), ('чашка'), ('шматок'), 
                            ('стакан'), ('ложка')
                            """
                        )
                    }
                })
                .build()
            INSTANCE = instance
            instance
        }
    }
}
