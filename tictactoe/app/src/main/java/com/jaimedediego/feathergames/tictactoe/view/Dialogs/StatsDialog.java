package com.jaimedediego.feathergames.tictactoe.view.Dialogs;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.RelativeLayout;

import com.jaimedediego.feathergames.tictactoe.R;
import com.jaimedediego.feathergames.tictactoe.config.PrefsConfig;
import com.jaimedediego.feathergames.tictactoe.methods.PrefsMethods;
import com.jaimedediego.feathergames.tictactoe.utils.Session;
import com.jaimedediego.feathergames.tictactoe.view.Adapters.ColorsAdapter;

public class StatsDialog extends Dialog {

    public StatsDialog(@NonNull final Context context) {
        super(context);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_stats);

        findViewById(R.id.title_container).setBackgroundColor(Session.getInstance().darkColorTheme);
    }
}

