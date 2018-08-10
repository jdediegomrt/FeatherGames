package com.jaimedediego.feathergames.tictactoe.view.Components;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RadioGroup;

public class CustomRadioGroup extends RadioGroup {
    private OnCheckedChangeListener listener;
    private OnCheckedChangeListener secondListener = null;

    public CustomRadioGroup(final Context context) {
        super(context);
    }

    public CustomRadioGroup(final Context context, final AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void setOnCheckedChangeListener(final OnCheckedChangeListener listener) {
        this.listener = listener;
        super.setOnCheckedChangeListener(listener);
    }

    public void switchListener(){
        OnCheckedChangeListener aux = listener;
        listener = secondListener;
        secondListener = aux;
        this.setOnCheckedChangeListener(listener);
    }
}