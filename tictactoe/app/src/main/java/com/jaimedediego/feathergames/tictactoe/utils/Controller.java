package com.jaimedediego.feathergames.tictactoe.utils;

import android.content.Context;
import android.os.Handler;
import android.widget.TextView;

import com.jaimedediego.feathergames.tictactoe.R;
import com.jaimedediego.feathergames.tictactoe.methods.PrefsMethods;
import com.jaimedediego.feathergames.tictactoe.view.Components.Cell;
import com.jaimedediego.feathergames.tictactoe.view.Components.CustomToast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Controller {

    private static Controller instance;

    public int movementsCount = 0;
    public final int restartDelayMillis = 700;

    private TextView playerX;
    private TextView playerO;
    private Context context;
    private IA IA;

    private List<Cell> cells = new ArrayList<>();
    private String player = "O";

    private Controller() {}

    public static Controller getInstance() {
        if (instance == null) {
            instance = new Controller();
        }
        return instance;
    }

    public void init(List<Cell> cells, Context context, TextView playerO, TextView playerX) {
        this.context = context;
        this.cells = cells;
        this.playerO = playerO;
        this.playerX = playerX;
        this.IA = new IA();
    }

    public void movement(Cell cell){
        this.IA.availableCells.remove(cell);
        cell.setText(player);
        cell.setClickable(false);
        cell.setEnabled(false);
        if(detectsWin()){
            if(player.equals("X")){
                new CustomToast(context, R.string.Xwins).showAndHide(restartDelayMillis);
            } else {
                new CustomToast(context, R.string.Owins).showAndHide(restartDelayMillis);
            }
            disableCells();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    restartCells();
                    IA = new IA();
                }
            }, restartDelayMillis);
        } else if (movementsCount++ >= 8){
            new CustomToast(context, R.string.draw).showAndHide(restartDelayMillis);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    restartCells();
                    IA = new IA();
                }
            }, restartDelayMillis);
        }
        if(player.equals("X")){
            player = "O";
            playerO.setTextColor(Session.getInstance().lightColorTheme);
            playerX.setTextColor(Session.getInstance().black);
        } else {
            player = "X";
            playerX.setTextColor(Session.getInstance().lightColorTheme);
            playerO.setTextColor(Session.getInstance().black);
        }
    }

    public void restartCells(){
        movementsCount = 0;
        for (int i = 0; i < cells.size(); i++) {
            Cell cell = cells.get(i);
            cell.setClickable(true);
            cell.setEnabled(true);
            cell.setText("");
        }
    }

    public void enableCells(){
        movementsCount = 0;
        for (int i = 0; i < cells.size(); i++) {
            Cell cell = cells.get(i);
            cell.setClickable(true);
            cell.setEnabled(true);
        }
    }

    public void disableCells(){
        for (int i = 0; i < cells.size(); i++) {
            Cell cell = cells.get(i);
            cell.setClickable(false);
            cell.setEnabled(false);
        }
    }

    private boolean detectsWin(){
        if(cells.get(0).getText().equals(cells.get(1).getText()) && cells.get(0).getText().equals(cells.get(2).getText()) && cells.get(0).getText().equals(player)){
            return true;
        }
        if(cells.get(3).getText().equals(cells.get(4).getText()) && cells.get(3).getText().equals(cells.get(5).getText())  && cells.get(3).getText().equals(player)){
            return true;
        }
        if(cells.get(6).getText().equals(cells.get(7).getText()) && cells.get(6).getText().equals(cells.get(8).getText()) && cells.get(6).getText().equals(player)){
            return true;
        }
        if(cells.get(0).getText().equals(cells.get(3).getText()) && cells.get(0).getText().equals(cells.get(6).getText()) && cells.get(0).getText().equals(player)){
            return true;
        }
        if(cells.get(1).getText().equals(cells.get(4).getText()) && cells.get(1).getText().equals(cells.get(7).getText()) && cells.get(1).getText().equals(player)){
            return true;
        }
        if(cells.get(2).getText().equals(cells.get(5).getText()) && cells.get(2).getText().equals(cells.get(8).getText()) && cells.get(2).getText().equals(player)){
            return true;
        }
        if(cells.get(0).getText().equals(cells.get(4).getText()) && cells.get(0).getText().equals(cells.get(8).getText()) && cells.get(0).getText().equals(player)){
            return true;
        }
        if(cells.get(2).getText().equals(cells.get(4).getText()) && cells.get(2).getText().equals(cells.get(6).getText()) && cells.get(2).getText().equals(player)){
            return true;
        }
        return false;
    }

    public class IA {
        private int difficulty;
        private List<Cell> availableCells;

        private IA () {
            this.difficulty = PrefsMethods.getInstance().getDifficulty();
            availableCells = new ArrayList<>(cells);
            Collections.copy(availableCells, cells);
        }


        public void setDifficulty(int difficulty){
            this.difficulty = difficulty;
        }

        public void disableAvailableCells(){
            for (int i = 0; i < availableCells.size(); i++) {
                availableCells.get(i).setClickable(false);
            }
        }

        public void enableAvailableCells(){
            for (int i = 0; i < availableCells.size(); i++) {
                availableCells.get(i).setClickable(true);
            }
        }

        public void move(){
            final Random rand = new Random();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    switch (difficulty){
                        case R.id.dummy:
                            movement(availableCells.get(rand.nextInt(availableCells.size())));
                            break;
                        case R.id.easy:
                            movement(availableCells.get(rand.nextInt(availableCells.size())));
                            break;
                        case R.id.medium:
                            movement(availableCells.get(rand.nextInt(availableCells.size())));
                            break;
                        case R.id.hard:
                            movement(availableCells.get(rand.nextInt(availableCells.size())));
                            break;
                    }
                    enableAvailableCells();
                }
            }, 350);
        }
    }

    public IA getIA(){
        return IA;
    }
}