package com.jaimedediego.feathergames.tictactoe.view.Components;

import android.content.Context;
import android.support.v7.widget.AppCompatCheckBox;
import android.util.AttributeSet;

public class CustomCheckBox extends AppCompatCheckBox {
    private OnCheckedChangeListener listener;
    private OnCheckedChangeListener secondListener;

    public CustomCheckBox(final Context context) {
        super(context);
    }

    public CustomCheckBox(final Context context, final AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomCheckBox(final Context context, final AttributeSet attrs, final int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void setOnCheckedChangeListener(final OnCheckedChangeListener listener) {
        this.listener = listener;
        super.setOnCheckedChangeListener(listener);
    }

    public void setSecondListener(final OnCheckedChangeListener listener){
        secondListener = listener;
    }

    public void toggle(boolean useSecondListener) {
        if (useSecondListener) {
            super.setOnCheckedChangeListener(secondListener);
            super.toggle();
            super.setOnCheckedChangeListener(listener);
        } else {
            super.toggle();
        }
    }
}