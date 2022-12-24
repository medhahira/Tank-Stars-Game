package com.mygame.game;
import com.badlogic.gdx.Game;

public class MainGame extends Game {
    @Override
    public void create() {
        setScreen(new LoadScreen(this));
    }

}