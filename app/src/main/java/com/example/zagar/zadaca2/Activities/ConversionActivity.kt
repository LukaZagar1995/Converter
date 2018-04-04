package com.example.zagar.zadaca2.Activities

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.support.v7.app.AppCompatActivity
import android.support.v7.app.ActionBar
import android.os.Bundle
import android.view.MenuItem
import android.widget.ArrayAdapter
import com.example.zagar.zadaca2.Constants.ButtonKeys.*
import com.example.zagar.zadaca2.Constants.DistanceConstants.*
import com.example.zagar.zadaca2.Constants.SpeedConstants.*
import com.example.zagar.zadaca2.Constants.TemperatureConstants.*
import com.example.zagar.zadaca2.Constants.MassConstants.*
import com.example.zagar.zadaca2.R
import kotlinx.android.synthetic.main.activity_conversion.*
import kotlinx.android.synthetic.main.custom_bar.*
import android.app.Activity
import android.content.Intent
import android.view.inputmethod.InputMethodManager
import android.view.View
import android.widget.EditText

class ConversionActivity : AppCompatActivity(){

    private val KEY_BUTTON_IDENTIFIER: String? = "button"
    private val KEY_INPUT_UNIT: String? = "input type"
    private val KEY_OUTPUT_UNIT: String? = "output type"
    private val KEY_INPUT_VALUE: String? = "input value"
    private val KEY_OUTPUT_VALUE: String? = "output value"
    private var speedConversionList = arrayOf(UNIT_KILOMETERS_PER_HOUR_LIST_ITEM, UNIT_METERS_PER_SECOND_LIST_ITEM, UNIT_MILES_PER_HOUR_LIST_ITEM, UNIT_KNOTS_LIST_ITEM)
    private val temperatureConversionList = arrayOf(UNIT_CELSIUS_LIST_ITEM, UNIT_FAHRENHEIT_LIST_ITEM, UNIT_KELVIN_LIST_ITEM)
    private val distanceConversionList = arrayOf(UNIT_METERS_LIST_ITEM, UNIT_INCHES_LIST_ITEM, UNIT_NAUTICAL_MILES_LIST_ITEM, UNIT_MILES_LIST_ITEM, UNIT_FEET_LIST_ITEM)
    private val massConversionList = arrayOf(UNIT_KILOGRAMS_LIST_ITEM, UNIT_POUND_LIST_ITEM, UNIT_OUNCE_LIST_ITEM, UNIT_TON_LIST_ITEM)
    private var conversionUnit:Double = 0.0


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_conversion)
        setUpUI(clConversionActivity)
        btnConvert.setOnClickListener{
            if(etValue.text.isEmpty())
                return@setOnClickListener
            val value:Double = etValue.text.toString().toDouble()
            var resultIntent = Intent ( this, ResultActivity::class.java)
            val startingIntent = intent
            when(startingIntent.getStringExtra(KEY_BUTTON_IDENTIFIER))
            {
                KEY_DISTANCE -> {
                    conversionUnit = convertToMeters()
                    conversionUnit = covertToDistanceUnitMeasurement(conversionUnit)
                    resultIntent = setUpIntent(conversionUnit, KEY_DISTANCE, value)
                }

                KEY_SPEED ->{
                    conversionUnit = convertToKilometersPerHour()
                    conversionUnit = convertToSpeedUnitMeasurement(conversionUnit)
                    resultIntent = setUpIntent(conversionUnit, KEY_SPEED, value)
                }

                KEY_MASS ->{
                    conversionUnit = convertToKilograms()
                    conversionUnit = convertToMassUnitMeasurement(conversionUnit)
                    resultIntent = setUpIntent(conversionUnit, KEY_MASS,value)
                }

                KEY_TEMPERATURE ->{
                    conversionUnit = convertToCelsius()
                    conversionUnit = convertToTemperatureUnitMeasurement(conversionUnit)
                    resultIntent = setUpIntent(conversionUnit, KEY_TEMPERATURE,value)
                }
            }
            startActivity(resultIntent)
        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                overridePendingTransition(R.transition.left_to_right, R.transition.right_to_left)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setUpUI(view: View)
    {
        supportActionBar!!.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
        supportActionBar!!.setCustomView(R.layout.custom_bar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)
        val startingIntent = intent
        when(startingIntent.getStringExtra(KEY_BUTTON_IDENTIFIER))
        {

            KEY_TEMPERATURE -> {
                val adapter = ArrayAdapter<String>(this, R.layout.spinner_item, temperatureConversionList)
                tvConversionTitle.text = KEY_TEMPERATURE
                btnConvert.setTextColor(Color.parseColor(TEMPERATURE_COLOR))
                clConversionActivity.setBackgroundResource(R.color.temperature)
                spConversionUnit.adapter = adapter
                spConversionUnit.setPopupBackgroundResource(R.color.temperature)
                spConvertedUnit.adapter = adapter
                spConvertedUnit.setPopupBackgroundResource(R.color.temperature)
                spConvertedUnit.setSelection(1)
                toolbar_title.setImageResource(R.drawable.temperature_image)
                supportActionBar!!.setBackgroundDrawable(ColorDrawable(Color.parseColor(TEMPERATURE_COLOR)))

            }

            KEY_SPEED -> {
                val adapter = ArrayAdapter<String>(this, R.layout.spinner_item, speedConversionList)
                tvConversionTitle.text = KEY_SPEED
                btnConvert.setTextColor(Color.parseColor(SPEED_COLOR))
                clConversionActivity.setBackgroundResource(R.color.speed)
                spConversionUnit.adapter = adapter
                spConversionUnit.setPopupBackgroundResource(R.color.speed)
                spConvertedUnit.adapter = adapter
                spConvertedUnit.setPopupBackgroundResource(R.color.speed)
                spConvertedUnit.setSelection(1)
                toolbar_title.setImageResource(R.drawable.speed_image)
                supportActionBar!!.setBackgroundDrawable(ColorDrawable(Color.parseColor(SPEED_COLOR)))

            }

            KEY_DISTANCE -> {
                val adapter = ArrayAdapter<String>(this, R.layout.spinner_item, distanceConversionList)
                tvConversionTitle.text = KEY_DISTANCE
                btnConvert.setTextColor(Color.parseColor(DISTANCE_COLOR))
                clConversionActivity.setBackgroundResource(R.color.distance)
                spConversionUnit.adapter = adapter
                spConversionUnit.setPopupBackgroundResource(R.color.distance)
                spConvertedUnit.adapter = adapter
                spConvertedUnit.setPopupBackgroundResource(R.color.distance)
                spConvertedUnit.setSelection(1)
                toolbar_title.setImageResource(R.drawable.distance_image)
                supportActionBar!!.setBackgroundDrawable(ColorDrawable(Color.parseColor(DISTANCE_COLOR)))

            }

            KEY_MASS -> {
                val adapter = ArrayAdapter<String>(this, R.layout.spinner_item, massConversionList)
                tvConversionTitle.text = KEY_MASS
                btnConvert.setTextColor(Color.parseColor(MASS_COLOR))
                clConversionActivity.setBackgroundResource(R.color.mass)
                spConversionUnit.adapter = adapter
                spConversionUnit.setPopupBackgroundResource(R.color.mass)
                spConvertedUnit.adapter = adapter
                spConvertedUnit.setPopupBackgroundResource(R.color.mass)
                spConvertedUnit.setSelection(1)
                toolbar_title.setImageResource(R.drawable.mass_image)
                supportActionBar!!.setBackgroundDrawable(ColorDrawable(Color.parseColor(MASS_COLOR)))

            }
        }

        if (view !is EditText) {
            view.setOnTouchListener({ _, _ ->
                hideSoftKeyboard(this@ConversionActivity)
                false
            })
        }

    }

    private fun convertToMeters():Double
    {

        when(spConversionUnit.selectedItem)
        {
            UNIT_INCHES_LIST_ITEM ->  return etValue.text.toString().toDouble() * RATIO_INCHES_TO_METERS

            UNIT_MILES_LIST_ITEM ->   return etValue.text.toString().toDouble() * RATIO_MILES_TO_METERS

            UNIT_FEET_LIST_ITEM  ->  return etValue.text.toString().toDouble() * RATIO_FEET_TO_METERS

            UNIT_NAUTICAL_MILES_LIST_ITEM ->  return etValue.text.toString().toDouble() * RATIO_NAUTICAL_MILES_TO_METERS
        }

        return  etValue.text.toString().toDouble()
    }

    private fun covertToDistanceUnitMeasurement(conversionUnit: Double):Double
    {
        when(spConvertedUnit.selectedItem)
        {
            UNIT_INCHES_LIST_ITEM -> return    conversionUnit / RATIO_INCHES_TO_METERS

            UNIT_MILES_LIST_ITEM ->  return   conversionUnit / RATIO_MILES_TO_METERS

            UNIT_FEET_LIST_ITEM  ->  return   conversionUnit / RATIO_FEET_TO_METERS

            UNIT_NAUTICAL_MILES_LIST_ITEM ->  return   conversionUnit / RATIO_NAUTICAL_MILES_TO_METERS
        }

        return conversionUnit
    }

    private fun convertToKilometersPerHour():Double
    {
        when(spConversionUnit.selectedItem)
        {
            UNIT_METERS_PER_SECOND_LIST_ITEM -> return etValue.text.toString().toDouble() * RATIO_METERS_PER_SECOND_TO_KILOMETERS_PER_HOUR

            UNIT_MILES_PER_HOUR_LIST_ITEM -> return etValue.text.toString().toDouble() * RATIO_MILES_PER_HOUR_TO_KILOMETERS_PER_HOUR

            UNIT_KNOTS_LIST_ITEM -> return etValue.text.toString().toDouble() * RATIO_KNOTS_TO_KILOMETERS_PER_HOUR

        }

        return  etValue.text.toString().toDouble()
    }

    private fun convertToSpeedUnitMeasurement(conversionUnit: Double):Double
    {
        when(spConvertedUnit.selectedItem)
        {
            UNIT_METERS_PER_SECOND_LIST_ITEM -> return    conversionUnit / RATIO_METERS_PER_SECOND_TO_KILOMETERS_PER_HOUR

            UNIT_MILES_PER_HOUR_LIST_ITEM ->  return   conversionUnit / RATIO_MILES_PER_HOUR_TO_KILOMETERS_PER_HOUR

            UNIT_KNOTS_LIST_ITEM -> return   conversionUnit / RATIO_KNOTS_TO_KILOMETERS_PER_HOUR
        }

        return conversionUnit
    }

    private fun convertToKilograms():Double
    {
        when(spConversionUnit.selectedItem)
        {
            UNIT_POUND_LIST_ITEM -> return etValue.text.toString().toDouble() * RATIO_POUND_TO_KILOGRAMS

            UNIT_OUNCE_LIST_ITEM -> return etValue.text.toString().toDouble() * RATIO_OUNCE_TO_KILOGRAMS

            UNIT_TON_LIST_ITEM -> return etValue.text.toString().toDouble() * RATIO_TON_TO_KILOGRAMS

        }

        return  etValue.text.toString().toDouble()
    }

    private fun convertToMassUnitMeasurement(conversionUnit: Double):Double
    {
        when(spConvertedUnit.selectedItem)
        {
            UNIT_POUND_LIST_ITEM -> return    conversionUnit / RATIO_POUND_TO_KILOGRAMS

            UNIT_OUNCE_LIST_ITEM ->  return   conversionUnit / RATIO_OUNCE_TO_KILOGRAMS

            UNIT_TON_LIST_ITEM -> return   conversionUnit / RATIO_TON_TO_KILOGRAMS
        }

        return conversionUnit
    }

    private fun convertToCelsius():Double
    {
        when(spConversionUnit.selectedItem)
        {
            UNIT_KELVIN_LIST_ITEM -> return etValue.text.toString().toDouble() + ZERO_KELVIN_TO_CELSIUS

            UNIT_FAHRENHEIT_LIST_ITEM -> return (etValue.text.toString().toDouble() - FAHRENHEIT_FORMULA_VALUE2) / FAHRENHEIT_FORMULA_VALUE1

        }

        return  etValue.text.toString().toDouble()
    }

    private fun convertToTemperatureUnitMeasurement(conversionUnit: Double):Double
    {
        when(spConvertedUnit.selectedItem)
        {
            UNIT_KELVIN_LIST_ITEM -> return    conversionUnit - ZERO_KELVIN_TO_CELSIUS

            UNIT_FAHRENHEIT_LIST_ITEM ->  return   conversionUnit * FAHRENHEIT_FORMULA_VALUE1 + FAHRENHEIT_FORMULA_VALUE2

        }

        return conversionUnit
    }

    private fun hideSoftKeyboard(activity: Activity) {
        val inputMethodManager = activity.getSystemService(
                Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(
                activity.currentFocus!!.windowToken, 0)
    }

    private fun setUpIntent(convertedUnit:Double, key:String, value:Double):Intent {

        val resultIntent = Intent (this, ResultActivity::class.java)
        resultIntent.putExtra(KEY_BUTTON_IDENTIFIER,key)
        resultIntent.putExtra(KEY_INPUT_UNIT, spConversionUnit.selectedItem.toString())
        resultIntent.putExtra(KEY_OUTPUT_UNIT, spConvertedUnit.selectedItem.toString())
        resultIntent.putExtra(KEY_INPUT_VALUE, value)
        resultIntent.putExtra(KEY_OUTPUT_VALUE, convertedUnit)
        return resultIntent
    }

}
