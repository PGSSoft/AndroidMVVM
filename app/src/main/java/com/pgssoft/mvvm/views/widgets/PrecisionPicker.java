package com.pgssoft.mvvm.views.widgets;

import android.content.Context;
import android.databinding.InverseBindingMethod;
import android.databinding.InverseBindingMethods;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.pgssoft.mvvm.R;

import java.util.HashSet;

/**
 * Created by bstokrocki on 18.02.2017.
 */

@InverseBindingMethods({
        @InverseBindingMethod(type = PrecisionPicker.class, attribute = "precisionLevel"),

})
public class PrecisionPicker extends LinearLayout {
    private View previousSelectedLevel;

    private int maxPrecisionLevel;
    private int precisionLevel;

    private HashSet<PrecisionLevelChangeListener> listeners;

    public PrecisionPicker(Context context) {
        super(context);
        init();
    }

    public PrecisionPicker(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PrecisionPicker(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        initializeLayout();

        maxPrecisionLevel = 1;
        precisionLevel = 1;

        listeners = new HashSet<>();
    }

    private void initializeLayout() {
        setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));

        int padding = (int) getResources().getDimension(R.dimen.precision_picker_padding);
        setPadding(padding, padding, padding, padding);

        setOrientation(HORIZONTAL);
        setBackgroundColor(getResources().getColor(R.color.precisionPickerItemNormal));
    }

    private void render() {
        removeAllViews();

        LayoutInflater layoutInflater = LayoutInflater.from(getContext());
        int itemSize = (int) getResources().getDimension(R.dimen.precision_picker_item_size);

        for (int i = 1; i <= maxPrecisionLevel; i++) {
            TextView item = (TextView) layoutInflater.inflate(R.layout.item_precision, null);

            LayoutParams layoutParams = new LayoutParams(0, itemSize);
            layoutParams.weight = 1;
            item.setLayoutParams(layoutParams);

            item.setText(Integer.toString(i));
            item.setTag(i);

            item.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    setSelection(view);
                }
            });

            addView(item);
        }
    }

    private void setSelection(int precisionLevel) {
        View selectedView = getChildAt(precisionLevel - 1);

        if (selectedView != null) {
            setSelection(selectedView);
        }
    }

    private void setSelection(View view) {
        if (view != null) {
            view.setSelected(true);
            precisionLevel = (int) view.getTag();

            for (PrecisionLevelChangeListener listener : listeners) {
                listener.precisionLevelChanged(precisionLevel);
            }

            if (previousSelectedLevel != null && previousSelectedLevel != view) {
                previousSelectedLevel.setSelected(false);
            }

            previousSelectedLevel = view;
        }
    }

    public int getPrecisionLevel() {
        return precisionLevel;
    }

    public void setPrecisionLevel(int precisionLevel) {
        this.precisionLevel = precisionLevel;

        setSelection(precisionLevel);
    }

    public void setMaxPrecisionLevel(Integer maxPrecisionLevel) {
        if (maxPrecisionLevel != null) {
            this.maxPrecisionLevel = maxPrecisionLevel;

            render();

            getChildAt(0).performClick();
        }
    }

    public void addListener(PrecisionLevelChangeListener listener) {
        listeners.add(listener);
    }

    public interface PrecisionLevelChangeListener {
        void precisionLevelChanged(int precisionLevel);
    }
}
