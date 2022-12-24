package com.mygame.game;

import com.badlogic.gdx.InputAdapter;

import java.util.HashMap;
import java.util.Map;

// Try to customise controller according to user input
public class ProjectileController extends InputAdapter {
    public static float power = 50f;
    //private static Map<Integer, ProjectileController> instances = new HashMap<Integer, ProjectileController>();
    public static float angle = 45f;

    ProjectileController(float angle, float power){
        this.angle = angle;
        this.power = power;
    }

    public float getAngle() {
        return angle;
    }

    public void setAngle(float angle) {
        this.angle = angle;
    }

    public void setPower(float power) {
        this.power = power;
    }

    public float getPower() {
        return power;
    }
}