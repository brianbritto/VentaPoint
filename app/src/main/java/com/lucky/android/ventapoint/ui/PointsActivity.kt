package com.lucky.android.ventapoint.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.lucky.android.ventapoint.R
import com.lucky.android.ventapoint.adapter.PointAdapter
import com.lucky.android.ventapoint.db.entity.Point
import com.lucky.android.ventapoint.viewmodel.PointViewModel

class PointsActivity : AppCompatActivity() {

    private lateinit var mPointViewModel: PointViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_points)

        val recyclerview = findViewById<RecyclerView>(R.id.rcyPoints)
        val adapter = PointAdapter(this)
        recyclerview.adapter = adapter
        recyclerview.layoutManager = LinearLayoutManager(this)

        mPointViewModel = ViewModelProviders.of(this).get(PointViewModel::class.java)
        mPointViewModel.getAllPoints()?.observe(this, Observer<List<Point>> { points -> adapter.setPoints(points) })
    }
}
