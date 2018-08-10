package com.jaimedediego.feathergames.tictactoe.utils;

public class Session {

    private static Session instance;

    public int darkColorTheme=0;
    public int lightColorTheme=0;
    public int lighterColorTheme=0;
    public int black;

    private Session() {}

    public static Session getInstance() {
        if (instance == null) {
            instance = new Session();
        }
        return instance;
    }
}