package com.example.zagar.zadaca2.Activities

import android.annotation.SuppressLint
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.zagar.zadaca2.Constants.ButtonKeys.*
import com.example.zagar.zadaca2.Constants.DistanceConstants.*
import com.example.zagar.zadaca2.Constants.SpeedConstants.*
import com.example.zagar.zadaca2.Constants.TemperatureConstants.*
import com.example.zagar.zadaca2.Constants.MassConstants.*
import com.example.zagar.zadaca2.R
import kotlinx.android.synthetic.main.activity_conversion.*

class ConversionActivity : AppCompatActivity(){

    private val KEY_BUTTON_IDENTIFIER: String? = "button"

    private var speedConversionList = arrayOf(UNIT_KILOMETERS_PER_HOUR, UNIT_MILES_PER_HOUR, UNIT_METERS_PER_SECOND, UNIT_KNOTS)
    private var temperatureConversionList = arrayOf(UNIT_CELSIUS, UNIT_KELVIN, UNIT_FAHRENHEIT)
    private var distanceConversionList = arrayOf(UNIT_METERS, UNIT_MILES, UNIT_NAUTICAL_MILES, UNIT_FEET, UNIT_INCHES)
    private var weightConversionList = arrayOf(UNIT_KILOGRAMS, UNIT_TON, UNIT_POUND, UNIT_OUNCE)
    private var conversionUnit:Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_conversion)
        actionBar?.setHomeButtonEnabled(true)
        setUpUI()
        btnConvert.setOnClickListener{
            val startingIntent = intent
            when(startingIntent.getStringExtra(KEY_BUTTON_IDENTIFIER))
            {
                KEY_DISTANCE -> {
                    conversionUnit = convertToMeters()
                    conversionUnit = covertToDistanceUnitMeasurement(conversionUnit)
                    Toast.makeText(this, "%.3f".format(conversionUnit), Toast.LENGTH_SHORT).show()
                }

                KEY_SPEED ->{
                    conversionUnit = convertToKilometersPerHour()
                    conversionUnit = convertToSpeedUnitMeasurement(conversionUnit)
                    Toast.makeText(this, "%.3f".format(conversionUnit), Toast.LENGTH_SHORT).show()
                }

                KEY_MASS ->{
                    conversionUnit = convertToKilograms()
                    conversionUnit = convertToMassUnitMeasurement(conversionUnit)
                    Toast.makeText(this, "%.3f".format(conversionUnit), Toast.LENGTH_SHORT).show()
                }

                KEY_TEMPERATURE ->{
                    conversionUnit = convertToCelsius()
                    conversionUnit = convertToTemperatureUnitMeasurement(conversionUnit)
                    Toast.makeText(this, "%.3f".format(conversionUnit), Toast.LENGTH_SHORT).show()
                }
            }
        }

    }

    private fun setUpUI()
    {
        val startingIntent = intent
        when(startingIntent.getStringExtra(KEY_BUTTON_IDENTIFIER))
        {
            KEY_TEMPERATURE -> {
                val adapter = ArrayAdapter<String>(this, R.layout.spinner_item, temperatureConversionList)
                tvConversionTitle.text = KEY_TEMPERATURE
                // TODO: 31.3.2018. Hardkodane boje
                btnConvert.setTextColor(Color.parseColor("#8B0000"))
                clConversionActivity.setBackgroundResource(R.color.temperature)
                spConversionUnit.adapter = adapter
                spConversionUnit.setPopupBackgroundResource(R.color.temperature)
                spConvertedUnit.adapter = adapter
                spConvertedUnit.setPopupBackgroundResource(R.color.temperature)
            }

            KEY_SPEED -> {
                val adapter = ArrayAdapter<String>(this, R.layout.spinner_item, speedConversionList)
                tvConversionTitle.text = KEY_SPEED
                btnConvert.setTextColor(Color.parseColor("#BDB76B"))
                clConversionActivity.setBackgroundResource(R.color.speed)
                spConversionUnit.adapter = adapter
                spConversionUnit.setPopupBackgroundResource(R.color.speed)
                spConvertedUnit.adapter = adapter
                spConvertedUnit.setPopupBackgroundResource(R.color.speed)
            }

            KEY_DISTANCE -> {
                val adapter = ArrayAdapter<String>(this, R.layout.spinner_item, distanceConversionList)
                tvConversionTitle.text = KEY_DISTANCE
                btnConvert.setTextColor(Color.parseColor("#2E8B57"))
                clConversionActivity.setBackgroundResource(R.color.distance)
                spConversionUnit.adapter = adapter
                spConversionUnit.setPopupBackgroundResource(R.color.distance)
                spConvertedUnit.adapter = adapter
                spConvertedUnit.setPopupBackgroundResource(R.color.distance)
            }

            KEY_MASS -> {
                val adapter = ArrayAdapter<String>(this, R.layout.spinner_item, weightConversionList)
                tvConversionTitle.text = KEY_MASS
                btnConvert.setTextColor(Color.parseColor("#4682B4"))
                clConversionActivity.setBackgroundResource(R.color.weight)
                spConversionUnit.adapter = adapter
                spConversionUnit.setPopupBackgroundResource(R.color.weight)
                spConvertedUnit.adapter = adapter
                spConvertedUnit.setPopupBackgroundResource(R.color.weight)
            }
        }
    }

    private fun convertToMeters():Double
    {

        when(spConversionUnit.selectedItem)
        {
            UNIT_INCHES ->  return etValue.text.toString().toDouble() * RATIO_INCHES_TO_METERS

            UNIT_MILES ->   return etValue.text.toString().toDouble() * RATIO_MILES_TO_METERS

            UNIT_FEET ->  return etValue.text.toString().toDouble() * RATIO_FEET_TO_METERS

            UNIT_NAUTICAL_MILES ->  return etValue.text.toString().toDouble() * RATIO_NAUTICAL_MILES_TO_METERS
        }

        return  etValue.text.toString().toDouble()
    }

    private fun covertToDistanceUnitMeasurement(conversionUnit: Double):Double
    {
        when(spConvertedUnit.selectedItem)
        {
            UNIT_INCHES -> return    conversionUnit / RATIO_INCHES_TO_METERS

            UNIT_MILES ->  return   conversionUnit / RATIO_MILES_TO_METERS

            UNIT_FEET ->  return   conversionUnit / RATIO_FEET_TO_METERS

            UNIT_NAUTICAL_MILES ->  return   conversionUnit / RATIO_NAUTICAL_MILES_TO_METERS
        }

        return conversionUnit
    }

    private fun convertToKilometersPerHour():Double
    {
        when(spConversionUnit.selectedItem)
        {
            UNIT_METERS_PER_SECOND -> return etValue.text.toString().toDouble() * RATIO_METERS_PER_SECOND_TO_KILOMETERS_PER_HOUR

            UNIT_MILES_PER_HOUR -> return etValue.text.toString().toDouble() * RATIO_MILES_PER_HOUR_TO_KILOMETERS_PER_HOUR

            UNIT_KNOTS -> return etValue.text.toString().toDouble() * RATIO_KNOTS_TO_KILOMETERS_PER_HOUR

        }

        return  etValue.text.toString().toDouble()
    }

    private fun convertToSpeedUnitMeasurement(conversionUnit: Double):Double
    {
        when(spConvertedUnit.selectedItem)
        {
            UNIT_METERS_PER_SECOND -> return    conversionUnit / RATIO_METERS_PER_SECOND_TO_KILOMETERS_PER_HOUR

            UNIT_MILES_PER_HOUR ->  return   conversionUnit / RATIO_MILES_PER_HOUR_TO_KILOMETERS_PER_HOUR

            UNIT_KNOTS -> return   conversionUnit / RATIO_KNOTS_TO_KILOMETERS_PER_HOUR
        }

        return conversionUnit
    }

    private fun convertToKilograms():Double
    {
        when(spConversionUnit.selectedItem)
        {
            UNIT_POUND -> return etValue.text.toString().toDouble() * RATIO_POUND_TO_KILOGRAMS

            UNIT_OUNCE -> return etValue.text.toString().toDouble() * RATIO_OUNCE_TO_KILOGRAMS

            UNIT_TON -> return etValue.text.toString().toDouble() * RATIO_TON_TO_KILOGRAMS

        }

        return  etValue.text.toString().toDouble()
    }

    private fun convertToMassUnitMeasurement(conversionUnit: Double):Double
    {
        when(spConvertedUnit.selectedItem)
        {
            UNIT_POUND -> return    conversionUnit / RATIO_POUND_TO_KILOGRAMS

            UNIT_OUNCE ->  return   conversionUnit / RATIO_OUNCE_TO_KILOGRAMS

            UNIT_TON -> return   conversionUnit / RATIO_TON_TO_KILOGRAMS
        }

        return conversionUnit
    }

    private fun convertToCelsius():Double
    {
        when(spConversionUnit.selectedItem)
        {
            UNIT_KELVIN -> return etValue.text.toString().toDouble() - ZERO_KELVIN_TO_CELSIUS

            UNIT_FAHRENHEIT -> return (etValue.text.toString().toDouble() - FAHRENHEIT_FORMULA_VALUE2) / FAHRENHEIT_FORMULA_VALUE1


        }

        return  etValue.text.toString().toDouble()
    }

    private fun convertToTemperatureUnitMeasurement(conversionUnit: Double):Double
    {
        when(spConvertedUnit.selectedItem)
        {
            UNIT_KELVIN -> return    conversionUnit + ZERO_KELVIN_TO_CELSIUS

            UNIT_FAHRENHEIT ->  return   conversionUnit * FAHRENHEIT_FORMULA_VALUE1 + FAHRENHEIT_FORMULA_VALUE2

        }

        return conversionUnit
    }

}
