package com.pgssoft.kotlinplayground.service.bindingadapters

import android.databinding.BindingAdapter
import android.databinding.InverseBindingListener
import android.widget.ImageView
import android.widget.TextView
import com.nostra13.universalimageloader.core.ImageLoader
import com.pgssoft.kotlinplayground.view.widgets.PrecisionPicker

/**
 * Created by bstokrocki on 01.02.2017.
 */
@BindingAdapter("android:currencyText")
fun setCurrencyText(view: TextView, value: Double?) {
    try {
        view.text = String.format("%.02f", value)
    } catch (ex: NumberFormatException) {
        //Parsing failed, do nothing
    }

}

@BindingAdapter("image")
fun setImage(view: ImageView, imageUrl: String) {
    ImageLoader.getInstance().displayImage(imageUrl, view)
}

/**
 * This binding adapter allows Data Binding framework to register it's listener in PrecisionPicker.
 * Adapter is required to "translate" InverseBindingListener to PrecisionPicker.PrecisionLevelChangeListener,
 * which is used by PrecisionPicker.
 */
@BindingAdapter("precisionLevelAttrChanged")
fun addPrecisionLevelListener(view: PrecisionPicker, listener: InverseBindingListener?) {
    if (listener != null) {
        view.addListener( { listener.onChange() } )
    }
}
