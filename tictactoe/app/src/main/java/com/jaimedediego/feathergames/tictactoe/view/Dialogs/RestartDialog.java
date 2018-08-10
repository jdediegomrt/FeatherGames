package com.jaimedediego.feathergames.tictactoe.view.Dialogs;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.jaimedediego.feathergames.tictactoe.methods.PrefsMethods;
import com.jaimedediego.feathergames.tictactoe.R;
import com.jaimedediego.feathergames.tictactoe.utils.Controller;
import com.jaimedediego.feathergames.tictactoe.view.MainActivity;

public class RestartDialog extends Dialog implements View.OnClickListener{

    private Context context;
    private int position;
    private String text = "";
    private boolean accepted = false;

    public RestartDialog(@NonNull final Context context, final int position) {
        super(context);
        this.position=position;
        this.context=context;

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_restart);

        final Button accept = findViewById(R.id.accept);
        final Button cancel = findViewById(R.id.cancel);

        accept.setOnClickListener(this);
        cancel.setOnClickListener(this);
    }

    public RestartDialog(@NonNull final Context context, String text) {
        super(context);
        this.context=context;
        this.text = text;

        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.dialog_restart);

        final TextView alert = findViewById(R.id.alert);
        final Button accept = findViewById(R.id.accept);
        final Button cancel = findViewById(R.id.cancel);

        alert.setText(text);

        accept.setOnClickListener(this);
        cancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.accept:
                if(text.equals("")) {
                    PrefsMethods.getInstance().setColorAccent(position);
                    Intent i = context.getPackageManager().
                            getLaunchIntentForPackage(context.getPackageName());
                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(i);
                } else {
                    accepted = true;
                    dismiss();
                }
                break;
            case R.id.cancel:
                dismiss();
                break;
            default:
                break;
        }
    }

    public boolean wasAccepted(){
        return accepted;
    }
}

