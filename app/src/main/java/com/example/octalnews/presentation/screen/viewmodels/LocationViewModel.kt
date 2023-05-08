package com.example.octalnews.presentation.screen.viewmodels

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import kotlinx.coroutines.launch

class LocationViewModel : ViewModel() {
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    private var _lat by mutableStateOf(0.0)
    private var _lon by mutableStateOf(0.0)

    val lat get() = _lat
    val lon get() = _lon

    fun getUserLocation(context: Activity) {
        if (ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)

            fusedLocationClient.lastLocation
                .addOnSuccessListener { location: Location? ->
                    _lat = if (location?.latitude != null) location.latitude else 60.049293 // error
                    _lon = if (location?.longitude != null) location.longitude else 30.326304 // detected
                }
        } else {
            Log.d("AAA", "ERROR_LOCATION")
            val permissions = arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            )
            ActivityCompat.requestPermissions(context, permissions, 0)
        }
    }
}