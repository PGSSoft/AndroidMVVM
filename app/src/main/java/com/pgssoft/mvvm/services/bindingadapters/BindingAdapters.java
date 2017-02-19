package com.pgssoft.mvvm.services.bindingadapters;

import android.databinding.BindingAdapter;
import android.databinding.InverseBindingAdapter;
import android.databinding.InverseBindingListener;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.pgssoft.mvvm.views.widgets.PrecisionPicker;

import java.text.NumberFormat;
import java.text.ParseException;

/**
 * Created by bstokrocki on 01.02.2017.
 */
public class BindingAdapters {

    @BindingAdapter("android:currencyText")
    public static void setCurrencyText(TextView view, Double value) {
        try {
            view.setText(String.format("%.02f", value));
        } catch (NumberFormatException ex) {
            //Parsing failed, do nothing
        }
    }

    @BindingAdapter("bind:image")
    public static void setImage(ImageView view, String imageUrl) {
        ImageLoader.getInstance().displayImage(imageUrl, view);
    }

    @BindingAdapter("precisionLevelAttrChanged")
    public static void addPrecisionLevelListener(PrecisionPicker view,
                                                 final InverseBindingListener listener) {
        if (listener != null) {
            view.addListener(new PrecisionPicker.PrecisionLevelChangeListener() {
                @Override
                public void precisionLevelChanged(int precisionLevel) {
                    listener.onChange();
                }
            });
        }
    }
}
