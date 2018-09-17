package com.jaimedediego.feathergames.tictactoe.view;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.jaimedediego.feathergames.tictactoe.R;
import com.jaimedediego.feathergames.tictactoe.config.PrefsConfig;
import com.jaimedediego.feathergames.tictactoe.config.ThemeConfig;
import com.jaimedediego.feathergames.tictactoe.methods.PrefsMethods;
import com.jaimedediego.feathergames.tictactoe.utils.Session;
import com.jaimedediego.feathergames.tictactoe.view.Components.Cell;
import com.jaimedediego.feathergames.tictactoe.utils.Controller;
import com.jaimedediego.feathergames.tictactoe.view.Components.CustomCheckBox;
import com.jaimedediego.feathergames.tictactoe.view.Components.CustomRadioGroup;
import com.jaimedediego.feathergames.tictactoe.view.Components.CustomToast;
import com.jaimedediego.feathergames.tictactoe.view.Dialogs.RestartDialog;
import com.jaimedediego.feathergames.tictactoe.view.Dialogs.SettingsDialog;
import com.jaimedediego.feathergames.tictactoe.view.Dialogs.StatsDialog;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements View.OnClickListener{

    private Boolean exit = false;

    private List<Cell> cells = new ArrayList<>();
    private List<Integer> ids = Arrays.asList(
            R.id.b1,
            R.id.b2,
            R.id.b3,
            R.id.b4,
            R.id.b5,
            R.id.b6,
            R.id.b7,
            R.id.b8,
            R.id.b9);
    private TextView playerX;
    private TextView playerO;
    private CustomCheckBox twoPlayers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        PrefsConfig.getInstance().setContext(this);
        PrefsConfig.getInstance().initConfig();
        ThemeConfig.getInstance().setActivity(this);
        ThemeConfig.getInstance().initConfig();

        setContentView(R.layout.activity_main);

        PrefsConfig.getInstance().setContext(getApplicationContext());

        final int numCells = 9;

        for(int i = 0; i < numCells; i++){
            Cell cell = findViewById(ids.get(i));
            cell.setOnClickListener(this);
            cells.add(cell);
        }
        playerX= findViewById(R.id.playerX);
        playerO= findViewById(R.id.playerO);
        playerO.setTextColor(Session.getInstance().lightColorTheme);

        FloatingActionButton stats = findViewById(R.id.stats);
        FloatingActionButton settings = findViewById(R.id.settings);

        twoPlayers = findViewById(R.id.two_players);

        final CustomRadioGroup opts = findViewById(R.id.opts);
        final RadioButton dummy= findViewById(R.id.dummy);
        final RadioButton easy= findViewById(R.id.easy);
        final RadioButton medium= findViewById(R.id.medium);
        final RadioButton hard= findViewById(R.id.hard);

        twoPlayers.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(final CompoundButton buttonView, final boolean isChecked) {
                if(!cellsAreEmpty()) {
                    final RestartDialog dialog = new RestartDialog(buttonView.getContext(), getResources().getString(R.string.game_lost));
                    dialog.show();
                    dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                        @Override
                        public void onDismiss(DialogInterface dialogInterface) {
                            if (dialog.wasAccepted()) {
                                Controller.getInstance().restartCells();
                            } else {
                                twoPlayers.setSecondListener(new CompoundButton.OnCheckedChangeListener() {
                                    @Override
                                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                                        if (isChecked) {
                                            dummy.setEnabled(false);
                                            dummy.setClickable(false);
                                            easy.setEnabled(false);
                                            easy.setClickable(false);
                                            medium.setEnabled(false);
                                            medium.setClickable(false);
                                            hard.setEnabled(false);
                                            hard.setClickable(false);
                                        } else {
                                            dummy.setEnabled(true);
                                            dummy.setClickable(true);
                                            easy.setEnabled(true);
                                            easy.setClickable(true);
                                            medium.setEnabled(true);
                                            medium.setClickable(true);
                                            hard.setEnabled(true);
                                            hard.setClickable(true);
                                        }
                                    }
                                });
                                twoPlayers.toggle(true);
                            }
                        }
                    });
                }
                if (isChecked) {
                    dummy.setEnabled(false);
                    dummy.setClickable(false);
                    easy.setEnabled(false);
                    easy.setClickable(false);
                    medium.setEnabled(false);
                    medium.setClickable(false);
                    hard.setEnabled(false);
                    hard.setClickable(false);
                } else {
                    dummy.setEnabled(true);
                    dummy.setClickable(true);
                    easy.setEnabled(true);
                    easy.setClickable(true);
                    medium.setEnabled(true);
                    medium.setClickable(true);
                    hard.setEnabled(true);
                    hard.setClickable(true);
                }
            }
        });

        switch(PrefsMethods.getInstance().getDifficulty()){
            case R.id.dummy: dummy.toggle();
                break;
            case R.id.easy: easy.toggle();
                break;
            case R.id.medium: medium.toggle();
                break;
            case R.id.hard: hard.toggle();
                break;
        }

        opts.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, final int checkedId) {
                if(!cellsAreEmpty()) {
                    final RestartDialog dialog = new RestartDialog(group.getContext(), getResources().getString(R.string.game_lost));
                    dialog.show();
                    dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                        @Override
                        public void onDismiss(DialogInterface dialogInterface) {
                            if (dialog.wasAccepted()) {
                                PrefsMethods.getInstance().setDifficulty(checkedId);
                                Controller.getInstance().restartCells();
                            } else {
                                opts.switchListener();
                                switch(PrefsMethods.getInstance().getDifficulty()){
                                    case R.id.dummy: dummy.toggle();
                                        Controller.getInstance().getIA().setDifficulty(R.id.dummy);
                                        break;
                                    case R.id.easy: easy.toggle();
                                        Controller.getInstance().getIA().setDifficulty(R.id.easy);
                                        break;
                                    case R.id.medium: medium.toggle();
                                        Controller.getInstance().getIA().setDifficulty(R.id.medium);
                                        break;
                                    case R.id.hard: hard.toggle();
                                        Controller.getInstance().getIA().setDifficulty(R.id.hard);
                                        break;
                                }
                                opts.switchListener();
                            }
                        }
                    });
                } else {
                    PrefsMethods.getInstance().setDifficulty(checkedId);
                }
            }
        });

        stats.setOnClickListener(this);
        settings.setOnClickListener(this);

        Controller.getInstance().init(cells, this, playerO, playerX);
    }

    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.stats){
            final StatsDialog dialog = new StatsDialog(this);
            dialog.show();
        } else if (v.getId()==R.id.settings){
            final SettingsDialog dialog = new SettingsDialog(this);
            dialog.show();
        } else {
            if(!twoPlayers.isChecked() && Controller.getInstance().movementsCount < 8) {
                Controller.getInstance().getIA().disableAvailableCells();
            }
            Controller.getInstance().movement((Cell) v);
            if(!twoPlayers.isChecked() && Controller.getInstance().movementsCount < 8){
                Controller.getInstance().getIA().move();
            }
        }
    }

    @Override
    public void onBackPressed() {
        if (exit) {
            finish();
        } else {
            CustomToast toast = new CustomToast(this, R.string.press_back_again_to_exit);
            toast.setTextSize(15);
            toast.showAndHide(1000);
            exit = true;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    exit = false;
                }
            }, 1000);
        }
    }

    public boolean cellsAreEmpty(){
        boolean empty = true;
        for(int i = 0; i < cells.size(); i++){
            if(!cells.get(i).getText().equals("")){
                empty = false;
            }
        }
        return empty;
    }

    public class IA {

    }
}
