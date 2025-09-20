package com.app.common.manager.location

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.os.Build
import android.provider.Settings
import androidx.annotation.RequiresPermission
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.tasks.OnSuccessListener
import com.app.common.manager.API.AppError

class LocationService {
    companion object {
        @Volatile
        private var instance: LocationService? = null

        fun getInstance() = instance ?: synchronized(this) {
            instance ?: LocationService().also { instance = it }
        }

        private var MY_PERMISSIONS_REQUEST_LOCATION = 99
        private var MY_PERMISSIONS_REQUEST_BACKGROUND_LOCATION = 66
    }

    private lateinit var activity: Activity

    private lateinit var locationManager: LocationManager

    // declare a global variable FusedLocationProviderClient
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    // globally declare LocationRequest
    private lateinit var locationRequest: LocationRequest

    // globally declare LocationCallback
    private var locationCallback: LocationCallback? = null

    var completionLocationManager: ((AppError?, String?, String?) -> Unit)? = null


    @SuppressLint("MissingPermission")
    fun getLocationUpdates(activity: Activity, completion: (AppError?, String?, String?)-> Unit) {
        this.activity = activity
        this.completionLocationManager = completion
        locationManager = activity.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        if (grantedPermission()) {
            fusedLocationClient = LocationServices.getFusedLocationProviderClient(activity)
            locationRequest = LocationRequest.create().apply {
                interval = 9999999999999999  // Interval pembaruan lokasi (10 detik)
                fastestInterval = 99999999999999999  // Interval tercepat untuk pembaruan (5 detik)
                priority = LocationRequest.PRIORITY_HIGH_ACCURACY  // Akurasi tinggi
            }
            locationCallback = object : LocationCallback() {
                override fun onLocationResult(locationResult: LocationResult) {
                    locationResult ?: return
                    if (locationResult.locations.isNotEmpty()) {
                        val location = locationResult.locations.last()

                        if (location != null) {
//                            fusedLocationClient.removeLocationUpdates(locationCallback)
                            fusedLocationClient.removeLocationUpdates {  }
                            if (!checkLocationIsMock(location)) {
                                completionLocationManager?.invoke(null, location?.latitude.toString(), location?.longitude.toString())
                                fusedLocationClient.removeLocationUpdates {  }
                                locationCallback = null
                            } else {
                                completionLocationManager?.invoke(AppError("Your location is not valid!"), null, null)
                            }
//                            Log.d("fused=====", "${location?.latitude}=======${location?.longitude}")
                        }

                    }
                }
            }

            fusedLocationClient.requestLocationUpdates(locationRequest, locationCallback!!, null)
//            startLocationUpdates()
//            getLastLocation()
        }
    }

    // Mendapatkan lokasi terakhir
    @RequiresPermission(allOf = [Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION])
    private fun getLastLocation() {
        fusedLocationClient.lastLocation
            .addOnSuccessListener(activity, OnSuccessListener<Location> { location ->
                if (location != null) {
                    // Menampilkan lokasi yang didapatkan
                    val latitude = location.latitude
                    val longitude = location.longitude
                    completionLocationManager?.invoke(null, latitude.toString(), longitude.toString())
                } else {
                    completionLocationManager?.invoke(AppError("dont have permission"), null, null)
                }
            })
    }

    //start location updates
    @SuppressLint("MissingPermission")
    fun startLocationUpdates() {
//        fusedLocationClient.lastLocation.addOnCompleteListener { task ->
//
//            if (task.isSuccessful && task.result != null) {
//                task.result?.let {
//                    completionLocationService?.invoke(null, it.latitude.toString(), it.longitude.toString())
//                }
//            } else {
//                task.exception?.message?.let {
//                    completionLocationService?.invoke(AppError(it), null, null)
//                }
//            }
//        }
    }


    private fun checkLocationIsMock(location: Location): Boolean {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            return location.isMock
        } else {
            return location.isFromMockProvider
        }
    }

    private fun grantedPermission() : Boolean {
        if (ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            requestLocationPermission()
            completionLocationManager?.invoke(AppError("dont have permission"), null, null)
            return false
        } else if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            showAlert { activity.startActivity(Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)) }
            completionLocationManager?.invoke(AppError(""), null, null)
            return false
        } else {
            return true
        }
    }

    private fun showAlert(completion: ()-> Unit) {
        AlertDialog.Builder(activity)
            .setTitle("Location Permission Needed")
            .setMessage("This app needs the Location permission, please accept to use location functionality")
            .setPositiveButton("OK") { _, _ ->
                completion()
            }
            .create()
            .show()
    }

    private fun requestLocationPermission() {
        ActivityCompat.requestPermissions(activity, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION,), MY_PERMISSIONS_REQUEST_LOCATION)
    }
}
