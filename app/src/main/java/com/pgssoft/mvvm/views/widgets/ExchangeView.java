package com.pgssoft.mvvm.views.widgets;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

import com.pgssoft.mvvm.R;
import com.pgssoft.mvvm.databinding.WidgetExchangeBinding;

/**
 * Created by bstokrocki on 15.02.2017.
 */

public class ExchangeView extends FrameLayout {
    private WidgetExchangeBinding binding;

    public ExchangeView(Context context) {
        super(context);
        init();
    }

    public ExchangeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ExchangeView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        binding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.widget_exchange, this, true);
    }

    public void setValue(Integer value) {
//        binding.editableView.setTe
    }

    public void setEditableMode(boolean isEditable) {

    }
}
