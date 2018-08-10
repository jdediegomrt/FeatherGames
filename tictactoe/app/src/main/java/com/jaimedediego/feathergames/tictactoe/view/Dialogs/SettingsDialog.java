package com.jaimedediego.feathergames.tictactoe.view.Dialogs;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.jaimedediego.feathergames.tictactoe.R;
import com.jaimedediego.feathergames.tictactoe.config.PrefsConfig;
import com.jaimedediego.feathergames.tictactoe.methods.PrefsMethods;
import com.jaimedediego.feathergames.tictactoe.utils.Session;
import com.jaimedediego.feathergames.tictactoe.view.Adapters.ColorsAdapter;

public class SettingsDialog extends Dialog {

    public SettingsDialog(@NonNull final Context context) {
        super(context);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_settings);

        findViewById(R.id.title_container).setBackgroundColor(Session.getInstance().darkColorTheme);

        PrefsConfig.getInstance().setContext(context);

        final GridView gridView = findViewById(R.id.color_gridview);
        final RadioGroup playerSide = findViewById(R.id.player_side);
        final RadioButton playerSideO = findViewById(R.id.player_side_O);
        final RadioButton playerSideX = findViewById(R.id.player_side_X);

        ColorsAdapter adapter = new ColorsAdapter(context);
        gridView.setAdapter(adapter);

        switch (PrefsMethods.getInstance().getPlayerSide()){
            case R.id.player_side_O:
                playerSideO.toggle();
                break;
            case R.id.player_side_X:
                playerSideX.toggle();
                break;
        }

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, final int position, long id) {
                if(position!=PrefsMethods.getInstance().getColorAccent()) {
                    final RestartDialog dialog = new RestartDialog(context, position);
                    dialog.show();
                }
            }
        });

        playerSide.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                PrefsMethods.getInstance().switchPlayerSide();
            }
        });
    }
}

