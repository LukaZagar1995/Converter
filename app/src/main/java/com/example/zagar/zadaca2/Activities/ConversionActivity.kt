package com.example.zagar.zadaca2.Activities

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.support.v7.app.AppCompatActivity
import android.support.v7.app.ActionBar;
import android.os.Bundle
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.zagar.zadaca2.Constants.ButtonKeys.*
import com.example.zagar.zadaca2.Constants.DistanceConstants.*
import com.example.zagar.zadaca2.Constants.SpeedConstants.*
import com.example.zagar.zadaca2.Constants.TemperatureConstants.*
import com.example.zagar.zadaca2.Constants.MassConstants.*
import com.example.zagar.zadaca2.R
import kotlinx.android.synthetic.main.activity_conversion.*
import kotlinx.android.synthetic.main.custom_bar.*
import android.app.Activity
import android.arch.lifecycle.Observer
import android.util.Log
import android.view.inputmethod.InputMethodManager
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import com.example.zagar.zadaca2.models.Input
import com.example.zagar.zadaca2.models.Output
import com.example.zagar.zadaca2.temperature.TemperatureViewModel
import com.example.zagar.zadaca2.utils.TemperatureConverter


class ConversionActivity : AppCompatActivity(){

    private val TAG = "ConversionActivity"

    private val KEY_BUTTON_IDENTIFIER: String? = "button"
    private val FORMAT_LIST: String? = " - "
    private val speedConversionList = arrayOf(UNIT_KILOMETERS_PER_HOUR_SHORT + FORMAT_LIST + UNIT_KILOMETERS_PER_HOUR,
                                        UNIT_MILES_PER_HOUR_SHORT + FORMAT_LIST + UNIT_MILES_PER_HOUR, UNIT_METERS_PER_SECOND_SHORT + FORMAT_LIST + UNIT_METERS_PER_SECOND,
                                        UNIT_KNOTS_SHORT + FORMAT_LIST + UNIT_KNOTS)
    private val temperatureConversionList = arrayOf(UNIT_CELSIUS_SHORT + FORMAT_LIST + UNIT_CELSIUS, UNIT_KELVIN_SHORT + FORMAT_LIST + UNIT_KELVIN,
                                        UNIT_FAHRENHEIT_SHORT + FORMAT_LIST + UNIT_FAHRENHEIT)
    private val distanceConversionList = arrayOf(UNIT_METERS_SHORT + FORMAT_LIST + UNIT_METERS, UNIT_MILES_SHORT + FORMAT_LIST + UNIT_MILES,
                                        UNIT_NAUTICAL_MILES_SHORT + FORMAT_LIST + UNIT_NAUTICAL_MILES, UNIT_FEET_SHORT + FORMAT_LIST + UNIT_FEET ,
                                        UNIT_INCHES_SHORT + FORMAT_LIST + UNIT_INCHES)
    private val massConversionList = arrayOf(UNIT_KILOGRAMS_SHORT + FORMAT_LIST + UNIT_KILOGRAMS, UNIT_TON_SHORT + FORMAT_LIST + UNIT_TON,
                                        UNIT_POUND_SHORT + FORMAT_LIST + UNIT_POUND, UNIT_OUNCE_SHORT + FORMAT_LIST + UNIT_OUNCE)
    private var conversionUnit:Double = 0.0
    private val tempConverter = TemperatureConverter()
    private val temperatureViewModel = TemperatureViewModel(tempConverter)
    private val testInput = Input("Tip1", 10.0)


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_conversion)
        setUpUI(clConversionActivity)

//        temperatureViewModel.output().observe(this, Observer<Output> { output: Output? ->
//            updateUI(output)
//        })

        btnConvert.setOnClickListener{
            if(etValue.text.isEmpty())
                return@setOnClickListener
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

//        if (view is ViewGroup) {
//            for (i in 0 until view.childCount) {
//                val innerView = view.getChildAt(i)
//                setUpUI(innerView)
//            }
//        }
    }



    private fun convertToMeters():Double
    {
        when(spConversionUnit.selectedItem)
        {
            UNIT_INCHES_SHORT + FORMAT_LIST + UNIT_INCHES ->  return etValue.text.toString().toDouble() * RATIO_INCHES_TO_METERS

            UNIT_MILES_SHORT + FORMAT_LIST + UNIT_MILES ->   return etValue.text.toString().toDouble() * RATIO_MILES_TO_METERS

            UNIT_FEET_SHORT + FORMAT_LIST + UNIT_FEET  ->  return etValue.text.toString().toDouble() * RATIO_FEET_TO_METERS

            UNIT_NAUTICAL_MILES_SHORT + FORMAT_LIST + UNIT_NAUTICAL_MILES ->  return etValue.text.toString().toDouble() * RATIO_NAUTICAL_MILES_TO_METERS
        }

        return  etValue.text.toString().toDouble()
    }

    private fun covertToDistanceUnitMeasurement(conversionUnit: Double):Double
    {
        when(spConvertedUnit.selectedItem)
        {
            UNIT_INCHES_SHORT + FORMAT_LIST + UNIT_INCHES -> return    conversionUnit / RATIO_INCHES_TO_METERS

            UNIT_MILES_SHORT + FORMAT_LIST + UNIT_MILES ->  return   conversionUnit / RATIO_MILES_TO_METERS

            UNIT_FEET_SHORT + FORMAT_LIST + UNIT_FEET  ->  return   conversionUnit / RATIO_FEET_TO_METERS

            UNIT_NAUTICAL_MILES_SHORT + FORMAT_LIST + UNIT_NAUTICAL_MILES ->  return   conversionUnit / RATIO_NAUTICAL_MILES_TO_METERS
        }

        return conversionUnit
    }

    private fun convertToKilometersPerHour():Double
    {
        when(spConversionUnit.selectedItem)
        {
            UNIT_METERS_PER_SECOND_SHORT + FORMAT_LIST + UNIT_METERS_PER_SECOND -> return etValue.text.toString().toDouble() * RATIO_METERS_PER_SECOND_TO_KILOMETERS_PER_HOUR

            UNIT_MILES_PER_HOUR_SHORT + FORMAT_LIST + UNIT_MILES_PER_HOUR -> return etValue.text.toString().toDouble() * RATIO_MILES_PER_HOUR_TO_KILOMETERS_PER_HOUR

            UNIT_KNOTS_SHORT + FORMAT_LIST + UNIT_KNOTS -> return etValue.text.toString().toDouble() * RATIO_KNOTS_TO_KILOMETERS_PER_HOUR

        }

        return  etValue.text.toString().toDouble()
    }

    private fun convertToSpeedUnitMeasurement(conversionUnit: Double):Double
    {
        when(spConvertedUnit.selectedItem)
        {
            UNIT_METERS_PER_SECOND_SHORT + FORMAT_LIST + UNIT_METERS_PER_SECOND -> return    conversionUnit / RATIO_METERS_PER_SECOND_TO_KILOMETERS_PER_HOUR

            UNIT_MILES_PER_HOUR_SHORT + FORMAT_LIST + UNIT_MILES_PER_HOUR ->  return   conversionUnit / RATIO_MILES_PER_HOUR_TO_KILOMETERS_PER_HOUR

            UNIT_KNOTS_SHORT + FORMAT_LIST + UNIT_KNOTS -> return   conversionUnit / RATIO_KNOTS_TO_KILOMETERS_PER_HOUR
        }

        return conversionUnit
    }

    private fun convertToKilograms():Double
    {
        when(spConversionUnit.selectedItem)
        {
            UNIT_POUND_SHORT + FORMAT_LIST + UNIT_POUND -> return etValue.text.toString().toDouble() * RATIO_POUND_TO_KILOGRAMS

            UNIT_OUNCE_SHORT + FORMAT_LIST + UNIT_OUNCE -> return etValue.text.toString().toDouble() * RATIO_OUNCE_TO_KILOGRAMS

            UNIT_TON_SHORT + FORMAT_LIST + UNIT_TON -> return etValue.text.toString().toDouble() * RATIO_TON_TO_KILOGRAMS

        }

        return  etValue.text.toString().toDouble()
    }

    private fun convertToMassUnitMeasurement(conversionUnit: Double):Double
    {
        when(spConvertedUnit.selectedItem)
        {
            UNIT_POUND_SHORT + FORMAT_LIST + UNIT_POUND -> return    conversionUnit / RATIO_POUND_TO_KILOGRAMS

            UNIT_OUNCE_SHORT + FORMAT_LIST + UNIT_OUNCE ->  return   conversionUnit / RATIO_OUNCE_TO_KILOGRAMS

            UNIT_TON_SHORT + FORMAT_LIST + UNIT_TON -> return   conversionUnit / RATIO_TON_TO_KILOGRAMS
        }

        return conversionUnit
    }

    private fun convertToCelsius():Double
    {
        when(spConversionUnit.selectedItem)
        {
            UNIT_KELVIN_SHORT + FORMAT_LIST + UNIT_KELVIN -> return etValue.text.toString().toDouble() + ZERO_KELVIN_TO_CELSIUS

            UNIT_FAHRENHEIT_SHORT + FORMAT_LIST + UNIT_FAHRENHEIT -> return (etValue.text.toString().toDouble() - FAHRENHEIT_FORMULA_VALUE2) / FAHRENHEIT_FORMULA_VALUE1

        }

        return  etValue.text.toString().toDouble()
    }

    private fun convertToTemperatureUnitMeasurement(conversionUnit: Double):Double
    {
        when(spConvertedUnit.selectedItem)
        {
            UNIT_KELVIN_SHORT + FORMAT_LIST + UNIT_KELVIN -> tempConverter.celsiusToKelvin(conversionUnit)

            UNIT_FAHRENHEIT_SHORT + FORMAT_LIST + UNIT_FAHRENHEIT ->  tempConverter.celsiusToFahrenheit(conversionUnit)

        }

        return conversionUnit
    }

    private fun hideSoftKeyboard(activity: Activity) {
        val inputMethodManager = activity.getSystemService(
                Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(
                activity.currentFocus!!.windowToken, 0)
    }

}
