package com.example.zagar.zadaca2.Activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import com.example.zagar.zadaca2.Classes.BtnKeys
import com.example.zagar.zadaca2.Classes.BtnKeys.*
import com.example.zagar.zadaca2.Classes.DistanceConstants.*
import com.example.zagar.zadaca2.Classes.SpeedConstants.*
import com.example.zagar.zadaca2.Classes.TemperatureConstants.*
import com.example.zagar.zadaca2.R
import kotlinx.android.synthetic.main.activity_conversion.*

class ConversionActivity : AppCompatActivity(){

    private val KEY_BUTTON_IDENTIFIER: String? = "button"

    private var speedConversionList = arrayOf(UNIT_KILOMETERS_PER_HOUR, UNIT_MILES_PER_HOUR, UNIT_METERS_PER_SECOND, UNIT_KNOTS)
    private var temperatureConversionList = arrayOf(UNIT_CELSIUS, UNIT_KELVIN, UNIT_FARENHEIT)
    private var distanceConversionList = arrayOf(UNIT_METERS, UNIT_MILES, UNIT_NAUTICAL_MILES, UNIT_FEET, UNIT_INCHES)
   // private var weightConversionList = arrayOf()



    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_conversion)
        setUpUI()

    }

    private fun setUpUI()
    {
        val startingIntent = intent
        when(startingIntent.getStringExtra(KEY_BUTTON_IDENTIFIER))
        {
            KEY_TEMPERATURE -> {
                val adapter = ArrayAdapter<String>(this, R.layout.spinner_item, temperatureConversionList)
                tvConversionTitle.text = KEY_TEMPERATURE
                clConversionActivity.setBackgroundResource(R.color.temperature)
                spConversionUnit!!.adapter = adapter
                spConversionUnit.setPopupBackgroundResource(R.color.temperature)
                spConvertedUnit!!.adapter = adapter
                spConvertedUnit.setPopupBackgroundResource(R.color.temperature)
            }

            KEY_SPEED -> {
                val adapter = ArrayAdapter<String>(this, R.layout.spinner_item, speedConversionList)
                tvConversionTitle.text = KEY_SPEED
                clConversionActivity.setBackgroundResource(R.color.speed)
                spConversionUnit!!.adapter = adapter
                spConversionUnit.setPopupBackgroundResource(R.color.speed)
                spConvertedUnit!!.adapter = adapter
                spConvertedUnit.setPopupBackgroundResource(R.color.speed)
            }

            KEY_DISTANCE -> {
                val adapter = ArrayAdapter<String>(this, R.layout.spinner_item, distanceConversionList)
                tvConversionTitle.text = KEY_DISTANCE
                clConversionActivity.setBackgroundResource(R.color.distance)
                spConversionUnit!!.adapter = adapter
                spConversionUnit.setPopupBackgroundResource(R.color.distance)
                spConvertedUnit!!.adapter = adapter
                spConvertedUnit.setPopupBackgroundResource(R.color.distance)
            }
        }
    }


}
