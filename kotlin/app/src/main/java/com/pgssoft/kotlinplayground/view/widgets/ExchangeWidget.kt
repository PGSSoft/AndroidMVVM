package com.pgssoft.kotlinplayground.view.widgets

import android.content.Context
import android.databinding.DataBindingUtil
import android.databinding.Observable
import android.databinding.ObservableBoolean
import android.databinding.ObservableField
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import com.pgssoft.kotlinplayground.R
import com.pgssoft.kotlinplayground.databinding.WidgetExchangeBinding

/**
 * Created by bstokrocki on 15.02.2017.
 */

class ExchangeWidget : FrameLayout {
    private val binding: WidgetExchangeBinding
    private var rate = 1.0
    private var precisionLevel = 0

    private val firstInputCallback = object: Observable.OnPropertyChangedCallback() {
        override fun onPropertyChanged(observable: Observable, i: Int) {
            if (firstInputValue.get() != null) {
                calculateSecondInput()
            }
        }
    }

    private val secondInputCallback = object : Observable.OnPropertyChangedCallback() {
        override fun onPropertyChanged(observable: Observable, i: Int) {
            if (secondInputValue.get() != null) {
                calculateFirstInput()
            }
        }
    }

    val firstInputActive = ObservableBoolean(true)
    var firstInputValue = ObservableField("1.00")
    var secondInputValue = ObservableField("1.00")

    init {
        binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.widget_exchange, this, true)
        binding.view = this

        firstInputValue.addOnPropertyChangedCallback(firstInputCallback)
        calculateSecondInput()
    }

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    private fun calculateFirstInput() {
        try {
            val newValue = java.lang.Double.parseDouble(secondInputValue.get()) * rate
            firstInputValue.set(String.format("%.0$precisionLevel" + "f", newValue).replace(",", "."))
        } catch (ex: NumberFormatException) {
            //Parsing failed, do nothing
        }
    }

    private fun calculateSecondInput() {
        try {
            val newValue = java.lang.Double.parseDouble(firstInputValue.get()) / rate
            secondInputValue.set(String.format("%.0$precisionLevel" + "f", newValue).replace(",", "."))
        } catch (ex: NumberFormatException) {
            //Parsing failed, do nothing
        }
    }

    fun setRate(rate: Double?) {
        if (rate != null) {
            this.rate = rate

            if (firstInputActive.get()) {
                calculateSecondInput()
            } else {
                calculateFirstInput()
            }
        }
    }

    fun firstInputActivated(view: View) {
        firstInputActive.set(true)
        binding.editableView1.requestFocus()

        secondInputValue.removeOnPropertyChangedCallback(secondInputCallback)
        firstInputValue.addOnPropertyChangedCallback(firstInputCallback)

        calculateSecondInput()
    }

    fun secondInputActivated(view: View) {
        firstInputActive.set(false)
        binding.editableView2.requestFocus()

        firstInputValue.removeOnPropertyChangedCallback(firstInputCallback)
        secondInputValue.addOnPropertyChangedCallback(secondInputCallback)

        calculateFirstInput()
    }

    fun setPrecisionLevel(precisionLevel: Int?) {
        if (precisionLevel != null) {
            this.precisionLevel = precisionLevel

            if (firstInputActive.get()) {
                calculateSecondInput()
            } else {
                calculateFirstInput()
            }
        }
    }
}
