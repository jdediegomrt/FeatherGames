package com.jaimedediego.feathergames.tictactoe.methods;

import com.jaimedediego.feathergames.tictactoe.R;
import com.jaimedediego.feathergames.tictactoe.config.PrefsConfig;

public class PrefsMethods {

    private static PrefsMethods instance;

    private PrefsMethods() {}

    public static PrefsMethods getInstance() {
        if (instance == null) {
            instance = new PrefsMethods();
        }
        return instance;
    }

    private PrefsConfig prefsConfig = PrefsConfig.getInstance();

    public int getColorAccent(){
        return prefsConfig.prefs.getInt("colorAccent", 0);
    }

    public void setColorAccent(int position){
        prefsConfig.editor.putInt("colorAccent", position);
        prefsConfig.editor.apply();
    }

    public int getDifficulty(){
        return prefsConfig.prefs.getInt("difficulty", 0);
    }

    public void setDifficulty(int difficulty){
        prefsConfig.editor.putInt("difficulty", difficulty);
        prefsConfig.editor.apply();
    }

    public int getPlayerSide(){
        return prefsConfig.prefs.getInt("playerSide", 0);
    }

    public void switchPlayerSide(){
        if(getPlayerSide() == R.id.player_side_O){
            prefsConfig.editor.putInt("playerSide", R.id.player_side_X);
        } else {
            prefsConfig.editor.putInt("playerSide", R.id.player_side_O);
        }
        prefsConfig.editor.apply();
    }
}
