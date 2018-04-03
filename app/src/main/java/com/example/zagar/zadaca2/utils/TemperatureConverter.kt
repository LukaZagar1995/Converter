package com.example.zagar.zadaca2.utils

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.example.zagar.zadaca2.Constants.TemperatureConstants.*
import com.example.zagar.zadaca2.models.Input
import com.example.zagar.zadaca2.models.Output

class TemperatureConverter {

    fun celsiusToKelvin(celsius: Double): Double = celsius - ZERO_KELVIN_TO_CELSIUS

    fun celsiusToFahrenheit(celsius: Double): Double = celsius * FAHRENHEIT_FORMULA_VALUE1 + FAHRENHEIT_FORMULA_VALUE2

    fun kelvinToCelsius(kelvin: Double): Double = kelvin + ZERO_KELVIN_TO_CELSIUS

    fun kelvinToFahrenheit(kelvin: Double): Double = kelvin * FAHRENHEIT_FORMULA_VALUE1 - 459.67

    fun fahrenheitToCelsius(fahrenheit: Double): Double = (fahrenheit - FAHRENHEIT_FORMULA_VALUE2) / FAHRENHEIT_FORMULA_VALUE1

    fun fahrenheitToKelvin(fahrenheit: Double): Double = (fahrenheit - 459.67) / FAHRENHEIT_FORMULA_VALUE1

//    fun convert(input: Input, output: Output) : LiveData<Output> {
//
//        val data = LiveData<Output>()
//
//        when(input.type){
//            "celsius" -> when(output.type) {
//                "celsius" -> output.value = input.value
//                "kelvin" -> output.value = celsiusToKelvin(input.value)
//                "fahrenheit" -> output.value = celsiusToFahrenheit(input.value)
//            }
//        }
//        return output
//    }
}
