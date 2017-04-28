package com.pgssoft.kotlinplayground.view.widgets

import android.content.Context
import android.databinding.InverseBindingMethod
import android.databinding.InverseBindingMethods
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.pgssoft.kotlinplayground.R
import java.util.*

/**
 * Created by bstokrocki on 18.02.2017.
 */

/**
 * Custom view with 2-way data binding. To make 2-way binding work we need:
 * - getter and setter for bindable field ([.precisionLevel] in our case),
 * - InverseBindingMethod annotation for view class defining class of the view and bindable attribute name,
 * - BindingAdapter for view class, which tells Data Binding library how to register it's listener
 * (implementation: [com.pgssoft.kotlinplayground.service.bindingadapters.BindingAdapters.addPrecisionLevelListener])
 */

@InverseBindingMethods(InverseBindingMethod(type = PrecisionPicker::class, attribute = "precisionLevel"))
class PrecisionPicker : LinearLayout {
    private var previousSelectedLevel: View? = null
    private var maxPrecisionLevel = 1
    private val listeners = HashSet<(Int) -> Unit>()

    var precisionLevel = 1
        set(value) {
            if (value != field) {
                field = value
                setSelection(field)
            }
        }

    init {
        initializeLayout()
    }

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    private fun initializeLayout() {
        layoutParams = LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)

        val padding = resources.getDimension(R.dimen.precision_picker_padding).toInt()
        setPadding(padding, padding, padding, padding)

        orientation = LinearLayout.HORIZONTAL
        setBackgroundColor(resources.getColor(R.color.precisionPickerItemNormal))
    }

    private fun render() {
        removeAllViews()

        val layoutInflater = LayoutInflater.from(context)
        val itemSize = resources.getDimension(R.dimen.precision_picker_item_size).toInt()

        for (i in 1..maxPrecisionLevel) {
            val item = layoutInflater.inflate(R.layout.item_precision, null) as TextView

            val layoutParams = LinearLayout.LayoutParams(0, itemSize)
            layoutParams.weight = 1f
            item.layoutParams = layoutParams

            item.text = i.toString()
            item.tag = i

            item.setOnClickListener { view -> setSelection(view) }

            addView(item)
        }
    }

    private fun setSelection(precisionLevel: Int) {
        val selectedView = getChildAt(precisionLevel - 1)

        if (selectedView != null) {
            setSelection(selectedView)
        }
    }

    private fun setSelection(view: View) {
        view.isSelected = true
        precisionLevel = view.tag as Int

        for (listener in listeners) {
            listener.invoke(precisionLevel)
        }

        if (previousSelectedLevel !== view) {
            previousSelectedLevel?.isSelected = false
        }

        previousSelectedLevel = view
    }

    fun setMaxPrecisionLevel(maxPrecisionLevel: Int?) {
        if (maxPrecisionLevel != null) {
            this.maxPrecisionLevel = maxPrecisionLevel

            render()

            getChildAt(0).performClick()
        }
    }

    fun addListener(listener: (Int) -> Unit) {
        listeners.add(listener)
    }
}
