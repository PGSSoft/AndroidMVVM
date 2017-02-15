package com.pgssoft.mvvm.services;

import android.databinding.BindingAdapter;
import android.databinding.InverseBindingAdapter;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.text.NumberFormat;
import java.text.ParseException;

/**
 * Created by bstokrocki on 01.02.2017.
 */
public class BindingAdapters {

    @BindingAdapter("android:currencyText")
    public static void setCurrencyText(TextView view, Double value) {
        try {
            view.setText(Double.toString(value));
        } catch (NumberFormatException ex) {
            view.setText("0.00");
        }
    }

    @BindingAdapter("android:text")
    public static void setText(EditText view, Integer value) {
        try {
            int oldValue = Integer.parseInt(view.getText().toString().trim().replace(".", ""));
            if (!value.equals(oldValue)) {
                view.setText(String.format("%d.%d", value / 100, value % 100));
            }
        } catch (NumberFormatException ex) {
            view.setText("0.00");
        }
    }

    @InverseBindingAdapter(attribute = "android:text")
    public static int getText(EditText view) {
        try {
            return Integer.parseInt(view.getText().toString().trim().replace(".", ""));
        } catch (NumberFormatException ex) {
            return 0;
        }
    }
}
