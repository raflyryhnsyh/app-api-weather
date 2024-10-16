package com.main.appweather.ui.current

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.main.appweather.source.data.Response
import com.main.appweather.source.data.WeatherRepository
import com.main.appweather.source.data.WeatherResponse
import com.main.appweather.source.network.RetrofitClient

class WeatherViewModel : ViewModel() {

    // Tambahkan fungsi untuk mengubah lokasi GPS
    private val _locationData = MutableLiveData<String>()
    val locationData: LiveData<String> get() = _locationData
    var lastLocation: String = ""

    private val repository = WeatherRepository(RetrofitClient.getApiService())

    // Fungsi untuk memperbarui lokasi dari Fragment
    fun updateLocation(location: String) {
        _locationData.value = location
        lastLocation = location
    }

    fun fetchWeather(city: String): LiveData<Response<WeatherResponse>> {
        Log.d("WeatherViewModel", "$city")
        Log.d("WeatherViewModel", "${locationData.value}")
        return repository.getFetchWeather(city)
    }
}
