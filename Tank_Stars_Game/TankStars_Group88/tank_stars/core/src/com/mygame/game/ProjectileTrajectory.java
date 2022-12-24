package com.mygame.game;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class ProjectileTrajectory extends Actor{

    private ProjectileController controller;
    private MissilePath projectileEquation;
    private Sprite trajectorySprite;

    public int trajectoryPointCount = 20;
    public float timeSeparation = 0.02f;

    public void TrajectoryActor(ProjectileController controller, float gravity, Sprite trajectorySprite) {
        this.controller = controller;
        this.trajectorySprite = trajectorySprite;
        this.projectileEquation = new MissilePath();
        this.projectileEquation.gravity = gravity;
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        projectileEquation.initialvelocity.set(controller.power, 0f);
        projectileEquation.initialvelocity.rotateDeg(controller.angle);
    }

    //@Override
    public void draw(SpriteBatch batch, float parentAlpha, float coordinate_x, float coordinate_y) {
        float t = 0f;
        float width = this.getWidth();
        float height = this.getHeight();

        float timeSeparation = this.timeSeparation;

        float x = coordinate_x, y = coordinate_y;

        for (int i = 0; i < trajectoryPointCount; i++) {
            x = x + projectileEquation.Xcoordinate(t); //this.x + projectileEquation.Xcoordinate(t);
            y = y + projectileEquation.Ycoordinate(t); //this.y + projectileEquation.Ycoordinate(t);

//            batch.setColor(Color.WHITE);
            trajectorySprite.setPosition(x, y);
            trajectorySprite.draw(batch);

            t += timeSeparation;
        }
    }

    public MissilePath getProjectileEquation() {
        return projectileEquation;
    }
    public Actor hit(float x, float y) {
        return null;
    }
}


