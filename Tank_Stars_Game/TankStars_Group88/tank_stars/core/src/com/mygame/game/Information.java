package com.mygame.game;

import java.io.Serializable;

public class Information implements Serializable {
    static final long serialVersionUID = 23L;
    private float Tank_1_x;
    private float Tank_1_y;
    private float Tank_2_x;
    private float Tank_2_y;
    private int ground_num;
    private float Tank_1_health;
    private float Tank_2_health;
    private float Tank_1_fuel;
    private float Tank_2_fuel;
    private int chance;
    private int count;
    private int count1;
    private int Tank1;
    private int Tank2;

    public Information(float x1, float x2, float y1, float y2, int g, float h1, float h2, float f1, float f2, int c, int ct, int ct1, int t1, int t2)
    {
        Tank_1_x = x1;
        Tank_1_y = y1;
        Tank_2_x = x2;
        Tank_2_y = y2;
        ground_num = g;
        Tank_1_health = h1;
        Tank_2_health = h2;
        Tank_1_fuel = f1;
        Tank_2_fuel = f2;
        chance = c;
        count = ct;
        count1 = ct1;
        Tank1 = t1;
        Tank2 = t2;
    }

    public float getTank_1_x() {
        return Tank_1_x;
    }

    public float getTank_1_y() {
        return Tank_1_y;
    }

    public float getTank_2_x() {
        return Tank_2_x;
    }

    public float getTank_2_y() {
        return Tank_2_y;
    }

    public int getGround_num() {
        return ground_num;
    }

    public float getTank_1_health() {
        return Tank_1_health;
    }

    public float getTank_2_health() {
        return Tank_2_health;
    }

    public float getTank_1_fuel() {
        return Tank_1_fuel;
    }

    public float getTank_2_fuel() {
        return Tank_2_fuel;
    }

    public int getChance() {
        return chance;
    }

    public int getCount() {
        return count;
    }

    public int getCount1() {
        return count1;
    }

    public int getTank1() {
        return Tank1;
    }

    public int getTank2() {
        return Tank2;
    }
}
