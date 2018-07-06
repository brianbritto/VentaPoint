package com.lucky.android.ventapoint.adapter

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.lucky.android.ventapoint.db.entity.Point
import com.lucky.android.ventapoint.R
import com.lucky.android.ventapoint.ui.MapActivity
import com.lucky.android.ventapoint.util.DataCache
import com.lucky.android.ventapoint.util.inflate
import kotlinx.android.synthetic.main.item_point.view.*

class PointAdapter (val context: Context): RecyclerView.Adapter<PointAdapter.ViewHolder>() {

    private var items: List<Point>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(parent.inflate(R.layout.item_point))

    override fun onBindViewHolder(holder: ViewHolder, position: Int){
        if (items != null){
            holder.bind(items!![position])
        } else {
            holder.bind(Point(0, "No Point", 0.0, 0.0))
        }
    }

    override fun getItemCount(): Int{
        if (items != null){
            return items!!.size
        } else {
            return 0
        }
    }

    fun setPoints(points: List<Point>?){
        items = points
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: Point) {
            with(itemView) {
                txtPointName.text = item.nombre
                setOnClickListener {
                    DataCache.pointCurrent = item
                    val intent = Intent(context, MapActivity::class.java)
                    intent.putExtra("id", item.id)
                    intent.putExtra("nombre", item.nombre)
                    intent.putExtra("latitud", item.latitud)
                    intent.putExtra("longitud", item.longitud)
                    context.startActivity(intent)
                }
            }
        }

    }
}