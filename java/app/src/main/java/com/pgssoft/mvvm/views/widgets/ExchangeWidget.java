package com.pgssoft.mvvm.views.widgets;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.Observable;
import android.databinding.ObservableField;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import com.pgssoft.mvvm.R;
import com.pgssoft.mvvm.databinding.WidgetExchangeBinding;

/**
 * Created by bstokrocki on 15.02.2017.
 */

public class ExchangeWidget extends FrameLayout {
    private WidgetExchangeBinding binding;

    private ObservableField<Boolean> firstInputActive;
    private ObservableField<String> firstInputValue;
    private ObservableField<String> secondInputValue;

    private double rate;
    private int precisionLevel;

    private Observable.OnPropertyChangedCallback firstInputCallback = new Observable.OnPropertyChangedCallback() {
        @Override
        public void onPropertyChanged(Observable observable, int i) {
            if (firstInputValue.get() != null) {
                calculateSecondInput();
            }
        }
    };

    private Observable.OnPropertyChangedCallback secondInputCallback = new Observable.OnPropertyChangedCallback() {
        @Override
        public void onPropertyChanged(Observable observable, int i) {
            if (secondInputValue.get() != null) {
                calculateFirstInput();
            }
        }
    };

    public ExchangeWidget(Context context) {
        super(context);
        init();
    }

    public ExchangeWidget(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ExchangeWidget(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        firstInputActive = new ObservableField<>(true);
        firstInputValue = new ObservableField<>("1.00");
        secondInputValue = new ObservableField<>("1.00");
        rate = 1;

        binding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.widget_exchange, this, true);
        binding.setView(this);

        firstInputValue.addOnPropertyChangedCallback(firstInputCallback);
        calculateSecondInput();
    }

    private void calculateFirstInput() {
        try {
            Double newValue = Double.parseDouble(secondInputValue.get()) * rate;
            firstInputValue.set(String.format("%.0" + precisionLevel + "f", newValue).replace(",", "."));
        } catch (NumberFormatException ex) {
            //Parsing failed, do nothing
        }
    }

    private void calculateSecondInput() {
        try {
            Double newValue = Double.parseDouble(firstInputValue.get()) / rate;
            secondInputValue.set(String.format("%.0" + precisionLevel + "f", newValue).replace(",", "."));
        } catch (NumberFormatException ex) {
            //Parsing failed, do nothing
        }
    }

    public void setRate(Double rate) {
        if (rate != null) {
            this.rate = rate;

            if (firstInputActive.get()) {
                calculateSecondInput();
            } else {
                calculateFirstInput();
            }
        }
    }

    public void firstInputActivated(View view) {
        firstInputActive.set(true);
        binding.editableView1.requestFocus();

        secondInputValue.removeOnPropertyChangedCallback(secondInputCallback);
        firstInputValue.addOnPropertyChangedCallback(firstInputCallback);

        calculateSecondInput();
    }

    public void secondInputActivated(View view) {
        firstInputActive.set(false);
        binding.editableView2.requestFocus();

        firstInputValue.removeOnPropertyChangedCallback(firstInputCallback);
        secondInputValue.addOnPropertyChangedCallback(secondInputCallback);

        calculateFirstInput();
    }

    public ObservableField<Boolean> getFirstInputActive() {
        return firstInputActive;
    }

    public ObservableField<String> getFirstInputValue() {
        return firstInputValue;
    }

    public ObservableField<String> getSecondInputValue() {
        return secondInputValue;
    }

    public void setPrecisionLevel(Integer precisionLevel) {
        if (precisionLevel != null) {
            this.precisionLevel = precisionLevel;

            if (firstInputActive.get()) {
                calculateSecondInput();
            } else {
                calculateFirstInput();
            }
        }
    }
}
