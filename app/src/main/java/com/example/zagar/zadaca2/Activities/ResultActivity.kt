package com.example.zagar.zadaca2.Activities

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.ActionBar
import android.view.MenuItem
import android.widget.TextView
import com.example.zagar.zadaca2.Constants.*
import com.example.zagar.zadaca2.Constants.ButtonKeys.*
import com.example.zagar.zadaca2.Constants.DistanceConstants.*
import com.example.zagar.zadaca2.Constants.MassConstants.*
import com.example.zagar.zadaca2.Constants.SpeedConstants.*
import com.example.zagar.zadaca2.Constants.TemperatureConstants.*
import com.example.zagar.zadaca2.R
import kotlinx.android.synthetic.main.activity_result.*
import kotlinx.android.synthetic.main.custom_bar.*
import java.text.DecimalFormat

class ResultActivity : AppCompatActivity() {

    private val KEY_BUTTON_IDENTIFIER: String? = "button"
    private val KEY_INPUT_UNIT: String? = "input type"
    private val KEY_OUTPUT_UNIT: String? = "output type"
    private val KEY_INPUT_VALUE: String? = "input value"
    private val KEY_OUTPUT_VALUE: String? = "output value"
    private val FORMAT_VIEW: String? = " "
    private val formatValue = DecimalFormat(".######")

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        setUI()
    }

    private fun setUI(){
        supportActionBar!!.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
        supportActionBar!!.setCustomView(R.layout.custom_bar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)
        val startingIntent = intent
        when(startingIntent.getStringExtra(KEY_BUTTON_IDENTIFIER))
        {

            KEY_TEMPERATURE -> {
                clResultActivity.setBackgroundResource(R.color.temperature)
                toolbar_title.setImageResource(R.drawable.temperature_image)
                supportActionBar!!.setBackgroundDrawable(ColorDrawable(Color.parseColor(TemperatureConstants.TEMPERATURE_COLOR)))
                setTitle(startingIntent.getStringExtra(KEY_INPUT_UNIT), tvConversionUnitTitle, tvConversionUnitValue, startingIntent.getDoubleExtra(KEY_INPUT_VALUE, 0.0))
                setTitle(startingIntent.getStringExtra(KEY_OUTPUT_UNIT), tvConvertedUnitTitle, tvConvertedUnitValue, startingIntent.getDoubleExtra(KEY_OUTPUT_VALUE, 0.0))

            }

            KEY_SPEED -> {
                clResultActivity.setBackgroundResource(R.color.speed)
                toolbar_title.setImageResource(R.drawable.speed_image)
                supportActionBar!!.setBackgroundDrawable(ColorDrawable(Color.parseColor(SpeedConstants.SPEED_COLOR)))
                setTitle(startingIntent.getStringExtra(KEY_INPUT_UNIT), tvConversionUnitTitle, tvConversionUnitValue, startingIntent.getDoubleExtra(KEY_INPUT_VALUE, 0.0))
                setTitle(startingIntent.getStringExtra(KEY_OUTPUT_UNIT), tvConvertedUnitTitle, tvConvertedUnitValue, startingIntent.getDoubleExtra(KEY_OUTPUT_VALUE, 0.0))

            }

            KEY_DISTANCE -> {
                clResultActivity.setBackgroundResource(R.color.distance)
                toolbar_title.setImageResource(R.drawable.distance_image)
                supportActionBar!!.setBackgroundDrawable(ColorDrawable(Color.parseColor(DistanceConstants.DISTANCE_COLOR)))
                setTitle(startingIntent.getStringExtra(KEY_INPUT_UNIT), tvConversionUnitTitle, tvConversionUnitValue, startingIntent.getDoubleExtra(KEY_INPUT_VALUE, 0.0))
                setTitle(startingIntent.getStringExtra(KEY_OUTPUT_UNIT), tvConvertedUnitTitle, tvConvertedUnitValue, startingIntent.getDoubleExtra(KEY_OUTPUT_VALUE, 0.0))

            }

            KEY_MASS -> {
                clResultActivity.setBackgroundResource(R.color.mass)
                toolbar_title.setImageResource(R.drawable.mass_image)
                supportActionBar!!.setBackgroundDrawable(ColorDrawable(Color.parseColor(MassConstants.MASS_COLOR)))
                setTitle(startingIntent.getStringExtra(KEY_INPUT_UNIT), tvConversionUnitTitle, tvConversionUnitValue, startingIntent.getDoubleExtra(KEY_INPUT_VALUE, 0.0))
                setTitle(startingIntent.getStringExtra(KEY_OUTPUT_UNIT), tvConvertedUnitTitle, tvConvertedUnitValue, startingIntent.getDoubleExtra(KEY_OUTPUT_VALUE, 0.0))
            }
        }
    }

    private fun setTitle(key: String, tvConversionTitle: TextView, tvUnitValue: TextView, value: Double){

        when(key)
        {
            UNIT_CELSIUS_LIST_ITEM -> {
                tvConversionTitle.text = UNIT_CELSIUS
                tvUnitValue.text = formatValue.format(value) + FORMAT_VIEW +  UNIT_CELSIUS_SHORT
            }

            UNIT_FAHRENHEIT_LIST_ITEM -> {
                tvConversionTitle.text = UNIT_FAHRENHEIT
                tvUnitValue.text = formatValue.format(value) + FORMAT_VIEW +  UNIT_FAHRENHEIT_SHORT
            }

            UNIT_KELVIN_LIST_ITEM -> {
                tvConversionTitle.text = UNIT_KELVIN
                tvUnitValue.text = formatValue.format(value) + FORMAT_VIEW +  UNIT_KELVIN_SHORT
            }

            UNIT_METERS_LIST_ITEM -> {
                tvConversionTitle.text = UNIT_METERS
                tvUnitValue.text = formatValue.format(value) + FORMAT_VIEW +  UNIT_METERS_SHORT
            }

            UNIT_INCHES_LIST_ITEM-> {
                tvConversionTitle.text = UNIT_INCHES
                tvUnitValue.text = formatValue.format(value) + FORMAT_VIEW +  UNIT_INCHES_SHORT
            }

            UNIT_NAUTICAL_MILES_LIST_ITEM-> {
                tvConversionTitle.text = UNIT_NAUTICAL_MILES
                tvUnitValue.text = formatValue.format(value) + FORMAT_VIEW +  UNIT_NAUTICAL_MILES_SHORT
            }

            UNIT_MILES_LIST_ITEM-> {
                tvConversionTitle.text = UNIT_MILES
                tvUnitValue.text = formatValue.format(value) + FORMAT_VIEW +  UNIT_MILES_SHORT
            }

            UNIT_FEET_LIST_ITEM-> {
                tvConversionTitle.text = UNIT_FEET
                tvUnitValue.text = formatValue.format(value) + FORMAT_VIEW +  UNIT_FEET_SHORT
            }

            UNIT_KILOMETERS_PER_HOUR_LIST_ITEM -> {
                tvConversionTitle.text = UNIT_KILOMETERS_PER_HOUR
                tvUnitValue.text = formatValue.format(value) + FORMAT_VIEW +  UNIT_KILOMETERS_PER_HOUR_SHORT
            }

            UNIT_METERS_PER_SECOND_LIST_ITEM -> {
                tvConversionTitle.text = UNIT_METERS_PER_SECOND
                tvUnitValue.text = formatValue.format(value) + FORMAT_VIEW +  UNIT_METERS_PER_SECOND_SHORT
            }

            UNIT_MILES_PER_HOUR_LIST_ITEM -> {
                tvConversionTitle.text = UNIT_MILES_PER_HOUR
                tvUnitValue.text = formatValue.format(value) + FORMAT_VIEW +  UNIT_MILES_PER_HOUR_SHORT
            }

            UNIT_KNOTS_LIST_ITEM -> {
                tvConversionTitle.text = UNIT_KNOTS
                tvUnitValue.text = formatValue.format(value) + FORMAT_VIEW +  UNIT_KNOTS_SHORT
            }

            UNIT_KILOGRAMS_LIST_ITEM -> {
                tvConversionTitle.text = UNIT_KILOGRAMS
                tvUnitValue.text = formatValue.format(value) + FORMAT_VIEW +  UNIT_KILOGRAMS_SHORT
            }

            UNIT_POUND_LIST_ITEM -> {
                tvConversionTitle.text = UNIT_POUND
                tvUnitValue.text = formatValue.format(value) + FORMAT_VIEW +  UNIT_POUND_SHORT
            }

            UNIT_OUNCE_LIST_ITEM -> {
                tvConversionTitle.text = UNIT_OUNCE
                tvUnitValue.text = formatValue.format(value) + FORMAT_VIEW +  UNIT_OUNCE_SHORT
            }
            UNIT_TON_LIST_ITEM  -> {
                tvConversionTitle.text = UNIT_TON
                tvUnitValue.text = formatValue.format(value) + FORMAT_VIEW +  UNIT_TON_SHORT
            }

        }
    }
}
