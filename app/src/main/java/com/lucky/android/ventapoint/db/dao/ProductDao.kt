package com.lucky.android.ventapoint.db.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import android.arch.persistence.room.Query
import android.arch.persistence.room.Update
import com.lucky.android.ventapoint.db.entity.Product
import com.lucky.android.ventapoint.db.entity.ProductPoint

@Dao
interface ProductDao {

    @Insert(onConflict = REPLACE)
    fun insert(product: Product)

    @Query("SELECT p.id, p.nombre, p.precio, pp.stock FROM products p INNER JOIN products_points pp ON pp.product_id = p.id where pp.point_id=:id")
    fun getAllProductsOfPoint(id:Long): LiveData<List<Product>>

    @Query("SELECT * FROM products p WHERE p.id = :id")
    fun getProductById(id: Long): Product

    @Update(onConflict = REPLACE)
    fun updateProduct(product: Product)

    @Query("UPDATE products_points SET stock = :newStock WHERE product_id = :product_id AND point_id = :point_id")
    fun updateStock(product_id: Long, point_id:Long, newStock: Int)

    /*@Query("INSERT INTO products_points VALUES (:productId, :pointId, :stock)")
    fun insertStock(productId: Long, pointId:Long, stock: Int)*/

    @Insert(onConflict = REPLACE)
    fun insertStock(productPoint: ProductPoint)
}