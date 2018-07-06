package com.lucky.android.ventapoint.db

import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import android.os.AsyncTask
import android.util.Log
import com.lucky.android.ventapoint.db.dao.PointDao
import com.lucky.android.ventapoint.db.dao.ProductDao
import com.lucky.android.ventapoint.db.dao.UserDao
import com.lucky.android.ventapoint.db.entity.*
import java.util.*


@Database(entities = [User::class, Product::class,
                      Point::class, ProductPoint::class,
                      ProductUser::class],
          version = 1)
abstract class AppDatabase: RoomDatabase() {

    abstract fun userDao(): UserDao
    abstract fun productDao(): ProductDao
    abstract fun pointDao(): PointDao

    companion object {

        private var INSTANCE: AppDatabase? = null

        private val sRoomDatabaseCallback = object : RoomDatabase.Callback() {

            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                PopulateDbAsync(INSTANCE!!).execute()
            }
        }

        fun getDatabase(context: Context): AppDatabase? {
            if (INSTANCE == null) {
                synchronized(AppDatabase::class) {
                    Log.v("AppDatabase","")
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                            AppDatabase::class.java, "ventapoint.db")
                            // Wipes and rebuilds instead of migrating
                            // if no Migration object.
                            // Migration is not part of this practical.
                            .allowMainThreadQueries()
                            .fallbackToDestructiveMigration()
                            .addCallback(sRoomDatabaseCallback)
                            .build()
                }
            }
            return INSTANCE
        }
    }

    fun destroyInstance() {
        INSTANCE = null
    }

    /**
     * Populate the database in the background.
     */
    private class PopulateDbAsync internal constructor(db: AppDatabase) : AsyncTask<Void, Void, Void>() {

        private val mPointDao: PointDao
        private val mProductDao: ProductDao
        private val mUserDao: UserDao

        init {
            mPointDao = db.pointDao()
            mProductDao = db.productDao()
            mUserDao = db.userDao()
        }

        override fun doInBackground(vararg params: Void): Void? {

            val rnd = Random()
            val products = generateProducts()
            val points = generatePoints()
            for (i in 0..9) {
                mPointDao.insert(points[i])
                mProductDao.insert(products[i])
            }
            for (i in 0..9) {
                for (j in 0..i){
                    val productPoint = ProductPoint(j.toLong(), i.toLong(), rnd.nextInt(100))
                    mProductDao.insertStock(productPoint)
                }
            }
            mUserDao.insert(User(0,"admin","admin123", "Brian", "Britto"))

            return null
        }

        fun generateProducts(): List<Product>{

            val products = ArrayList<Product>()

            val NOMBRE = arrayOf("Detergente", "Cerveza", "Cigarro", "Leche", "Arroz",
                                             "Menestra", "Fosforo", "Avena", "Sal", "Ajinomoto")
            val PRECIO = arrayOf(2.0, 3.1, 4.2, 5.3, 6.4, 7.5, 8.6, 9.7, 10.8, 11.9)

            for (i in 0..9) {
                val product = Product(i.toLong(), NOMBRE[i], PRECIO[i])
                products.add(product)
            }

            return products
        }

        fun generatePoints(): List<Point>{

            val points = ArrayList<Point>()

            val NOMBRE = arrayOf("Carmelita", "Dolly", "Maruja", "Coral", "Tambo",
                    "JJ Elias", "La Locura", "Regalitos", "San Pedro", "Rogger Express")
            val LATITUD = arrayOf(-12.108793, -12.108206, -12.110199, -12.111468, -12.109999,
                    -12.112066, -12.112108, -12.109297, -12.110430, -12.112413)
            val LONGITUD = arrayOf(-77.050880, -77.048445, -77.052082, -77.051202, -77.047415,
                    -77.045291, -77.046943, -77.044915, -77.044336, -77.046632)

            for (i in 0..9) {
                val point = Point(i.toLong(), NOMBRE[i], LATITUD[i], LONGITUD[i])
                points.add(point)
            }

            return points
        }
    }
}