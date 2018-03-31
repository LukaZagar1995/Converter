package com.example.zagar.zadaca2.Activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.zagar.zadaca2.Constants.ButtonKeys.*
import com.example.zagar.zadaca2.R
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private val KEY_BUTTON_IDENTIFIER: String? = "button"

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnSpeedConversion.setOnClickListener{
            val intent = Intent (this, ConversionActivity::class.java)
            intent.putExtra(KEY_BUTTON_IDENTIFIER, KEY_SPEED)
            startActivity(intent)
        }

        btnTemperatureConversion.setOnClickListener{
            val intent = Intent (this, ConversionActivity::class.java)
            intent.putExtra(KEY_BUTTON_IDENTIFIER, KEY_TEMPERATURE)
            startActivity(intent)
        }

        btnDistanceConversion.setOnClickListener{
            val intent = Intent (this, ConversionActivity::class.java)
            intent.putExtra(KEY_BUTTON_IDENTIFIER, KEY_DISTANCE)
            startActivity(intent)
        }

        btnWeightConversion.setOnClickListener{
            val intent = Intent (this, ConversionActivity::class.java)
            intent.putExtra(KEY_BUTTON_IDENTIFIER, KEY_MASS)
            startActivity(intent)
        }

    }


}
