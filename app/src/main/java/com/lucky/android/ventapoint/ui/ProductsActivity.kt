package com.lucky.android.ventapoint.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.EditText
import com.lucky.android.ventapoint.R
import com.lucky.android.ventapoint.adapter.ProductAdapter
import com.lucky.android.ventapoint.db.entity.Product
import com.lucky.android.ventapoint.util.DataCache
import com.lucky.android.ventapoint.util.toast
import com.lucky.android.ventapoint.viewmodel.ProductViewModel

class ProductsActivity : AppCompatActivity() {

    private lateinit var mProductViewModel: ProductViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_products)

        mProductViewModel = ViewModelProviders.of(this).get(ProductViewModel::class.java)

        val bundle = intent.extras
        if (bundle != null){
            val id_point = bundle.getLong("id")
            val nombre_point = bundle.getString("nombre")

            val recyclerview = findViewById<RecyclerView>(R.id.rcyProducts)
            val adapter = ProductAdapter(this,{ product ->
                /*val dialog = ProductDialogFragment()
                dialog.show(fragmentManager,"stockChange")
                dialog.
                toast(product.stock.toString())*/
                if (product.stock == 0) {toast("Producto agotado.") }
                else {
                    val builder = AlertDialog.Builder(this)
                    val inflater = layoutInflater
                    val view = inflater.inflate(R.layout.dialog_product, null)
                    builder.setView(view)
                            .setMessage("¿Deseas ordenar? Ingrese una cantidad menor al stock actual.")
                            .setPositiveButton("GUARDAR") { dialog, id ->
                                val edtCantidad = view.findViewById<EditText>(R.id.edtCantidad)
                                if (edtCantidad.text.toString() != ""){
                                    val cantidad = edtCantidad.text.toString().toInt()
                                    if (cantidad > 0 && cantidad <= product.stock) {
                                        mProductViewModel.updateStock(product.id, DataCache.pointCurrent!!.id, product.stock - cantidad)
                                        toast("Stock actualizado.")
                                    } else if (cantidad <= 0) {
                                        toast("Ingresa una cantidad válida.")
                                    } else {
                                        toast("La cantidad debe ser menor a ${product.stock} unidad(es).")
                                    }
                                } else {toast("Ingrese una cantidad.")}
                            }
                            .setNegativeButton("CANCELAR") { dialog, id -> }
                            .create()
                            .show()
                }
            })
            recyclerview.adapter = adapter
            recyclerview.layoutManager = LinearLayoutManager(this)

            mProductViewModel = ViewModelProviders.of(this).get(ProductViewModel::class.java)
            mProductViewModel.getAllProductsOfPoint(id_point).observe(this, Observer<List<Product>> { products -> adapter.setProducts(products) })
        }
    }


}
