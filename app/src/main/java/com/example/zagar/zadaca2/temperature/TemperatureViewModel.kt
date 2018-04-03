package com.example.zagar.zadaca2.temperature

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import android.arch.lifecycle.ViewModel
import com.example.zagar.zadaca2.models.Input
import com.example.zagar.zadaca2.models.Output
import com.example.zagar.zadaca2.utils.TemperatureConverter

class TemperatureViewModel(private val temperatureConverter: TemperatureConverter) : ViewModel() {
    private val output: LiveData<Output>
    private var input: MutableLiveData<Input> = MutableLiveData<Input>()

    init {
        output = Transformations.switchMap(input) { input ->
            temperatureConverter.convert(input)
        }
    }

    fun setInput(input: Input): TemperatureViewModel {
        this.input.value = input
        return this
    }

    fun output(): LiveData<Output> {
        return output
    }
}
