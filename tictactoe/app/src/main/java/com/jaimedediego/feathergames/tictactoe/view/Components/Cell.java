package com.jaimedediego.feathergames.tictactoe.view.Components;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;
import android.util.TypedValue;

import com.jaimedediego.feathergames.tictactoe.utils.Session;

public class Cell extends AppCompatButton {

    public Cell(Context context) {
        super(context);
    }

    public Cell(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.setBackgroundColor(Color.TRANSPARENT);
        this.setTextColor(Session.getInstance().lightColorTheme);
        this.setTextSize(TypedValue.COMPLEX_UNIT_SP, 32);
    }

    public Cell(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}
