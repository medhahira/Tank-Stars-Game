package com.mygame.game;

import com.badlogic.gdx.graphics.Texture;

import java.util.HashMap;
import java.util.Map;

public class Tank {
    private float health;
    private float fuel;
    Texture tankTexture;
    private static Map<Integer, Tank> instances = new HashMap<Integer, Tank>();
    private Tank(Texture tankTexture){
        this.health = 100;
        this.fuel = 100;
        this.tankTexture = tankTexture;
    }

    public static Tank getInstance(int integer, Texture texture){
        if (!instances.containsKey(integer)){
            instances.put(integer, new Tank(texture));
            System.out.println("created");
            System.out.println(integer);
        }
        return instances.get(integer);
    }

    public float getFuel() {
        return fuel;
    }

    public void decreaseFuel() {
        this.fuel -=1;
    }
}
