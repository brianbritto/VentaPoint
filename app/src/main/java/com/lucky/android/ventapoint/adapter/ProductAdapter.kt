package com.lucky.android.ventapoint.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.lucky.android.ventapoint.db.entity.Product
import com.lucky.android.ventapoint.R
import com.lucky.android.ventapoint.util.inflate
import kotlinx.android.synthetic.main.item_product.view.*

class ProductAdapter (val context: Context,
                      val listener: (Product) -> Unit): RecyclerView.Adapter<ProductAdapter.ViewHolder>() {

    private var items: List<Product>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(parent.inflate(R.layout.item_product))

    override fun onBindViewHolder(holder: ViewHolder, position: Int){
        if (items != null){
            holder.bind(items!![position],listener)
        } else {
            holder.bind(Product(),listener)
        }
    }

    override fun getItemCount(): Int{
        if (items != null){
            return items!!.size
        } else {
            return 0
        }
    }

    fun setProducts(products: List<Product>?){
        items = products
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: Product, listener: (Product) -> Unit) {
            with(itemView) {
                txtProductName.text = item.nombre
                txtProductPrecio.text = "S/.${item.precio.toString()}0"
                txtProductStock.text = item.stock.toString()
                setOnClickListener { listener(item) }
            }
        }
    }
}