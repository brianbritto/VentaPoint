package com.lucky.android.ventapoint.ui

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v7.app.AppCompatActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.lucky.android.ventapoint.R
import com.lucky.android.ventapoint.db.entity.Point

class MapActivity : AppCompatActivity(), OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    private lateinit var mMap: GoogleMap
    private var mPoint: Point? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)


        val bundle = intent.extras
        if (bundle!=null){
            mPoint = Point( bundle.getLong("id"),
                            bundle.getString("nombre"),
                            bundle.getDouble("latitud"),
                            bundle.getDouble("longitud"))
        }

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        if (ActivityCompat.checkSelfPermission(this,
                        Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                        ActivityCompat.checkSelfPermission(this,
                        Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return
        }

        if (mPoint!=null){
            val marker = LatLng(mPoint!!.latitud, mPoint!!.longitud)
            mMap.addMarker(MarkerOptions()
                    .position(marker)
                    .title(mPoint!!.nombre))
            mMap.moveCamera(CameraUpdateFactory.newLatLng(marker))
            mMap.setMyLocationEnabled(true)
            mMap.setOnMarkerClickListener(this)
            /*mMap.setOnMarkerClickListener(object: GoogleMap.OnMarkerClickListener{
                override fun onMarkerClick(mark: Marker?): Boolean {
                    val intent = Intent(application, ProductsActivity::class.java)
                    intent.putExtra("id", mPoint!!.id)
                    intent.putExtra("nombre", mPoint!!.nombre)
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    application.startActivity(intent)
                    return true
                }
            })*/
        }
    }

    override fun onMarkerClick(marker: Marker?): Boolean {
        val intent = Intent(application, ProductsActivity::class.java)
        intent.putExtra("id", mPoint!!.id)
        intent.putExtra("nombre", mPoint!!.nombre)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        application.startActivity(intent)
        finish()
        return true
    }

}
