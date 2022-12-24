package com.mygame.game;

import com.badlogic.gdx.graphics.Texture;

import java.util.ArrayList;

public class Main {
    static ArrayList<Texture> tanks = new ArrayList<Texture>();
    static ArrayList <Texture> tank_bow_right = new ArrayList<Texture>();
    static ArrayList <Texture> tank_bow_left = new ArrayList<Texture>();

    static int player1_tank_idx;
    static int player2_tank_idx;
    //static ArrayList<Integer> player1_tank_idx = new ArrayList<Integer>();
    public static void Main(){
        Texture abrams_right = new Texture("data/abrams_tank1");
        Texture spectre_right = new Texture("data/spectre_tank1");
        Texture tiger_right = new Texture("data/tiger_tank1");

        Texture abrams_left = new Texture("data/abrams_tank2");
        Texture spectre_left = new Texture("data/spectre_tank2");
        Texture tiger_left = new Texture("data/tiger_tank2");
        tank_bow_right.add(abrams_right);
        tank_bow_right.add(spectre_right);
        tank_bow_right.add(tiger_right);

        tank_bow_left.add(abrams_left);
        tank_bow_left.add(spectre_left);
        tank_bow_left.add(tiger_left);
    }
}
