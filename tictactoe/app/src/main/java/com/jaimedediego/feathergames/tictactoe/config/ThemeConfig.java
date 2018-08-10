package com.jaimedediego.feathergames.tictactoe.config;

import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.StateListDrawable;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;

import com.jaimedediego.feathergames.tictactoe.R;
import com.jaimedediego.feathergames.tictactoe.utils.Session;

import java.util.Arrays;
import java.util.List;

public class ThemeConfig {

    private final List<Integer> colors = Arrays.asList(
            R.drawable.colors_red,
            R.drawable.colors_pink,
            R.drawable.colors_purple,
            R.drawable.colors_deep_purple,
            R.drawable.colors_indigo,
            R.drawable.colors_blue,
            R.drawable.colors_light_blue,
            R.drawable.colors_cyan,
            R.drawable.colors_green,
            R.drawable.colors_teal,
            R.drawable.colors_light_green,
            R.drawable.colors_lime,
            R.drawable.colors_yellow,
            R.drawable.colors_orange,
            R.drawable.colors_deep_orange,
            R.drawable.colors_brown,
            R.drawable.colors_grey,
            R.drawable.colors_blue_grey
    );

    private static ThemeConfig instance;

    private ThemeConfig() {
    }

    public static ThemeConfig getInstance() {
        if (instance == null) {
            instance = new ThemeConfig();
        }
        return instance;
    }

    private AppCompatActivity activity;
    private Session session = Session.getInstance();

    public void setActivity(AppCompatActivity activity){
        this.activity = activity;
    }

    public void initConfig(){
        session.black=ResourcesCompat.getColor(activity.getResources(), R.color.md_black_1000, null);
        switch(PrefsConfig.getInstance().prefs.getInt("colorAccent", 0)){
            case 0:
                activity.setTheme(R.style.AppTheme_CustomBarRed);
                session.lighterColorTheme=ResourcesCompat.getColor(activity.getResources(), R.color.md_red_200, null);
                break;
            case 1:
                activity.setTheme(R.style.AppTheme_CustomBarPink);
                session.lighterColorTheme=ResourcesCompat.getColor(activity.getResources(), R.color.md_pink_200, null);
                break;
            case 2:
                activity.setTheme(R.style.AppTheme_CustomBarPurple);
                session.lighterColorTheme=ResourcesCompat.getColor(activity.getResources(), R.color.md_purple_200, null);
                break;
            case 3:
                activity.setTheme(R.style.AppTheme_CustomBarDeepPurple);
                session.lighterColorTheme=ResourcesCompat.getColor(activity.getResources(), R.color.md_deep_purple_200, null);
                break;
            case 4:
                activity.setTheme(R.style.AppTheme_CustomBarIndigo);
                session.lighterColorTheme=ResourcesCompat.getColor(activity.getResources(), R.color.md_indigo_200, null);
                break;
            case 5:
                activity.setTheme(R.style.AppTheme_CustomBarBlue);
                session.lighterColorTheme=ResourcesCompat.getColor(activity.getResources(), R.color.md_blue_200, null);
                break;
            case 6:
                activity.setTheme(R.style.AppTheme_CustomBarLightBlue);
                session.lighterColorTheme=ResourcesCompat.getColor(activity.getResources(), R.color.md_light_blue_200, null);
                break;
            case 7:
                activity.setTheme(R.style.AppTheme_CustomBarCyan);
                session.lighterColorTheme=ResourcesCompat.getColor(activity.getResources(), R.color.md_cyan_200, null);
                break;
            case 8:
                activity.setTheme(R.style.AppTheme_CustomBarGreen);
                session.lighterColorTheme=ResourcesCompat.getColor(activity.getResources(), R.color.md_green_200, null);
                break;
            case 9:
                activity.setTheme(R.style.AppTheme_CustomBarTeal);
                session.lighterColorTheme=ResourcesCompat.getColor(activity.getResources(), R.color.md_teal_200, null);
                break;
            case 10:
                activity.setTheme(R.style.AppTheme_CustomBarLightGreen);
                session.lighterColorTheme=ResourcesCompat.getColor(activity.getResources(), R.color.md_light_green_200, null);
                break;
            case 11:
                activity.setTheme(R.style.AppTheme_CustomBarLime);
                session.lighterColorTheme=ResourcesCompat.getColor(activity.getResources(), R.color.md_lime_200, null);
                break;
            case 12:
                activity.setTheme(R.style.AppTheme_CustomBarYellow);
                session.lighterColorTheme=ResourcesCompat.getColor(activity.getResources(), R.color.md_yellow_200, null);
                break;
            case 14:
                activity.setTheme(R.style.AppTheme_CustomBarDeepOrange);
                session.lighterColorTheme=ResourcesCompat.getColor(activity.getResources(), R.color.md_deep_orange_200, null);
                break;
            case 15:
                activity.setTheme(R.style.AppTheme_CustomBarBrown);
                session.lighterColorTheme=ResourcesCompat.getColor(activity.getResources(), R.color.md_brown_200, null);
                break;
            case 16:
                activity.setTheme(R.style.AppTheme_CustomBarGrey);
                session.lighterColorTheme=ResourcesCompat.getColor(activity.getResources(), R.color.md_grey_500, null);
                break;
            case 17:
                activity.setTheme(R.style.AppTheme_CustomBarBlueGrey);
                session.lighterColorTheme=ResourcesCompat.getColor(activity.getResources(), R.color.md_blue_grey_200, null);
                break;
            default:
                activity.setTheme(R.style.AppTheme_CustomBarOrange);
                session.lighterColorTheme=ResourcesCompat.getColor(activity.getResources(), R.color.md_orange_200, null);
                break;
        }

        TypedValue typedValue = new TypedValue();
        TypedArray a = activity.obtainStyledAttributes(typedValue.data, new int[] { R.attr.colorAccent, R.attr.colorPrimary });
        session.lightColorTheme = a.getColor(0, 0);
        session.darkColorTheme = a.getColor(1, 0);
        a.recycle();
    }

    public void themeSearchView(SearchView searchView){
        searchView.setIconifiedByDefault(false);

        SearchView.SearchAutoComplete searchAutoComplete = searchView.findViewById(android.support.v7.appcompat.R.id.search_src_text);
        searchAutoComplete.setHintTextColor(Color.WHITE);
        searchAutoComplete.setTextColor(Color.WHITE);

        ImageView searchIcon = searchView.findViewById(android.support.v7.appcompat.R.id.search_mag_icon);
        searchIcon.setImageResource(R.drawable.ic_search_white_24dp);

        ImageView voiceIcon = searchView.findViewById(android.support.v7.appcompat.R.id.search_close_btn);
        voiceIcon.setImageResource(R.drawable.ic_close_white_24dp);

        View searchPlate = searchView.findViewById(android.support.v7.appcompat.R.id.search_plate);
        searchPlate.getBackground().setColorFilter(Color.WHITE, PorterDuff.Mode.MULTIPLY);
    }

    public List<Integer> colors(){
        return colors;
    }

    public StateListDrawable getMenuAnimation(){
        StateListDrawable menuAnimation = new StateListDrawable();
        menuAnimation.addState(new int[] {android.R.attr.state_checked}, new ColorDrawable(session.lightColorTheme));
        menuAnimation.addState(new int[]{}, new ColorDrawable(session.darkColorTheme));
        return menuAnimation;
    }
}
