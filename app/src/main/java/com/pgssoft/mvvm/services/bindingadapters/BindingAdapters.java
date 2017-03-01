package com.pgssoft.mvvm.services.bindingadapters;

import android.databinding.BindingAdapter;
import android.databinding.InverseBindingListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.pgssoft.mvvm.views.widgets.PrecisionPicker;

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

    /**
     * This binding adapter allows Data Binding framework to register it's listener in PrecisionPicker.
     * Adapter is required to "translate" InverseBindingListener to PrecisionPicker.PrecisionLevelChangeListener,
     * which is used by PrecisionPicker.
     */
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
