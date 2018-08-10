package com.jaimedediego.feathergames.tictactoe.config;

import android.content.Context;
import android.content.SharedPreferences;

import com.jaimedediego.feathergames.tictactoe.R;

public class PrefsConfig {

    private static PrefsConfig instance;

    private PrefsConfig() {
    }

    public static PrefsConfig getInstance() {
        if (instance == null) {
            instance = new PrefsConfig();
        }
        return instance;
    }

    public SharedPreferences prefs;
    public SharedPreferences.Editor editor;
    private Context context;

    public void setContext(Context context){
        this.context = context;
        prefs = this.context.getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);
        editor = prefs.edit();
    }

    public void initConfig(){
        if(!prefs.contains("colorAccent")){
            editor.putInt("colorAccent", 0);
            editor.apply();
        }
        if(!prefs.contains("difficulty")){
            editor.putInt("difficulty", R.id.dummy);
            editor.apply();
        }
        if(!prefs.contains("playerSide")){
            editor.putInt("playerSide", R.id.player_side_O);
            editor.apply();
        }
    }
}
