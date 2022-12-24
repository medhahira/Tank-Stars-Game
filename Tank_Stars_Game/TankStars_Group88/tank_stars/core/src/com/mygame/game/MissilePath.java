package com.mygame.game;
import com.badlogic.gdx.math.Vector2;

public class MissilePath {
    public float gravity;
    public Vector2 initialstartPoint = new Vector2();
    public Vector2 initialvelocity = new Vector2();

    public float Xcoordinate(float temp) {
        float Xcoord = initialstartPoint.x + initialvelocity.x * temp;
        return Xcoord;
    }

    public float Ycoordinate(float temp) {
        float Ycoord = initialvelocity.y * temp + initialstartPoint.y + 0.5f * gravity * temp * temp;
        return Ycoord;
    }
}