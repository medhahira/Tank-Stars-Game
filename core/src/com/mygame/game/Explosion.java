package com.mygame.game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Interpolation;

public class Explosion {
    public static final float ANIMATION_LENGTH = 0.3f;
    public static Animation<TextureRegion> animation = null;
    float time;
    float x_coord;
    float y_coord;

    public int flag_rem = 0;
    public Explosion(float x, float y){
        this.x_coord = x;
        this.y_coord = y;
        if (animation == null){
            animation = new Animation<TextureRegion>(ANIMATION_LENGTH, TextureRegion.split(new Texture("data/exploded_.png"), 103, 103)[0]) ;
        }
    }
    public void check(float time_change){
        time = time + time_change;
        if (animation.isAnimationFinished(time)){
            flag_rem = 1;
        }
    }
    public void draw(SpriteBatch batch){
        batch.draw(animation.getKeyFrame(time),x_coord, y_coord);
    }
}
