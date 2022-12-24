package com.mygame.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import java.util.HashMap;
import java.util.Map;

public class Player {
    private static Map<Integer, Player> instances = new HashMap<Integer, Player>();

    private float health = 100;
    private float fuel = 100;
    private Texture tank_texture;
    private int num;
    ProjectileController projectile_path_player;
    private Player(int num, Texture tank_texture){
        this.tank_texture = tank_texture;
        //this.projectile_path_player = new ProjectileController(ProjectileController.angle);
    }

    public static Player getInstance(int integer, Texture texture){

        if (!instances.containsKey(integer)){
            instances.put(integer, new Player(integer, texture));
            System.out.println("created");
            System.out.println(integer);
        }
        return instances.get(integer);
    }

    public float getHealth() {
        return health;
    }
    public void decreaseHealth40() {
        this.health -=5;
    }
    public void decreaseHealth10() {
        this.health -=10;
    }
    public void decreaseHealth50() {
        this.health -=50;
    }
    public void decreaseFuel() {
        this.fuel-=0.5;
    }

    public float getFuel() {
        return fuel;
    }

    public Texture getTank_texture() {
        return tank_texture;
    }


    public ProjectileController getProjectile_path_player() {
        this.projectile_path_player.setPower(projectile_path_player.getPower()+1);
        return projectile_path_player;
    }

    public static float decreased40(float num){
        return num-40f;
    }

    public void setFuel(float fuel) {
        this.fuel = fuel;
    }

    public void setHealth(float health) {
        this.health = health;
    }
}
