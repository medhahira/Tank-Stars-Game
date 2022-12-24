package com.mygame.game;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.util.ArrayList;
public class Ground {
    private ArrayList<Float> y_coordinates = new ArrayList();
    private ArrayList<Integer> x_coordinates = new ArrayList<>();
    private static Ground my_ground = null;
    private int num_display;
    private Ground(int num_display){
        if (num_display == 1){
            for (int i = 0; i<width; i+=1){
                float y = 10+(float) Math.sin(0.02*i)+(float)Math.cos(0.03*i);
                y_coordinates.add(8*y);
                x_coordinates.add(i);
                this.num_display = num_display;
            }
        } else if (num_display == 2){
            for (int i = 0; i<width; i+=1){
                float y = 10+(float) Math.sin(0.05*i);
                y_coordinates.add(8*y);
                x_coordinates.add(i);
                this.num_display = num_display;
            }
        } else if (num_display == 3){
            for (int i = 0; i<width; i+=1){
                float y = 10+(float) Math.sin(0.05*i)+(float)(2*Math.cos(0.08*i));
                y_coordinates.add(8*y);
                x_coordinates.add(i);
                this.num_display = num_display;
            }
        }
        else if (num_display == 4){
            for (int i = 0; i<width; i+=1){
                float y = 10+(float) Math.sin(0.05*i)+(float)(Math.cos(0.05*i));
                y_coordinates.add(8*y);
                x_coordinates.add(i);
                this.num_display = num_display;
            }
        }
    }
    public static Ground getInstance(int num_display){
        if (my_ground == null){
            my_ground = new Ground(num_display);
        }
        return my_ground;
    }
    static float width = Gdx.graphics.getWidth();


    public ArrayList<Integer> getX_coordinates(){
        return this.x_coordinates;
    }
    public ArrayList<Float> getY_coordinates(){
        return this.y_coordinates;
    }
    public void display(ShapeRenderer shapeRenderer){
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        for (int i = 0; i<width; i+=1){
            float y = 10+(float) Math.sin(0.02*i)+(float)Math.cos(0.03*i);
            shapeRenderer.setColor(64/255f, 33/255f, 15/255f, 1);
            shapeRenderer.rect(i, (float) 0, (float) 1,8*y);
        }
        shapeRenderer.end();
    }
    public void display2(ShapeRenderer shapeRenderer){
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        for (int i = 0; i<width; i+=1){
            float y = 10+(float) Math.sin(0.05*i);
            shapeRenderer.setColor(64/255f, 33/255f, 15/255f, 1);
            shapeRenderer.rect(i, (float) 0, (float) 1,8*y);
        }
        shapeRenderer.end();
    }
    public void display3(ShapeRenderer shapeRenderer){
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        for (int i = 0; i<width; i+=1){
            float y = 10+(float) Math.sin(0.05*i)+(float)(2*Math.cos(0.08*i));
            shapeRenderer.setColor(64/255f, 33/255f, 15/255f, 1);
            shapeRenderer.rect(i, (float) 0, (float) 1,8*y);
        }
        shapeRenderer.end();
    }

    public void display4(ShapeRenderer shapeRenderer){
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        for (int i = 0; i<width; i+=1){
            float y = 10+(float) Math.sin(0.05*i)+(float)(Math.cos(0.05*i));
            shapeRenderer.setColor(64/255f, 33/255f, 15/255f, 1);
            shapeRenderer.rect(i, (float) 0, (float) 1,8*y);
        }
        shapeRenderer.end();
    }
    public int getNum_display() {
        return num_display;
    }

    public void setNum_display(int num_display) {
        this.num_display = num_display;
    }
}
