package com.mygame.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class GroundChoice implements Screen {
    float height= Gdx.graphics.getHeight();
    float width = Gdx.graphics.getWidth();
    private SpriteBatch batch = new SpriteBatch();
    private Texture backgroundTexture = new Texture("data/GroundChoiceBackground.png");;
    private Sprite backgroundSprite = new Sprite(backgroundTexture);
    private OrthographicCamera camera = new OrthographicCamera(width,height);
    protected Vector3 temp = new Vector3();
    public static MainGame game;
    static int player_num;
    //Sprite tankSprite = new Sprite(tankTexture);
    public GroundChoice(MainGame game){
        GroundChoice.game = game;
        camera.setToOrtho(false);
        backgroundSprite.setSize(width,height);
    }


    @Override
    public void show(){};

    @Override
    public void render(float delta){
        batch.begin();
        backgroundSprite.draw(batch);
        batch.end();
        handleTouch();
    };

    @Override
    public void resize(int width, int height){};

    @Override
    public void pause(){};

    @Override
    public void resume(){};

    @Override
    public void hide(){};

    @Override
    public void dispose(){
        backgroundTexture.dispose();
    };
    void handleTouch(){
        if(Gdx.input.justTouched()) {
            temp.set(Gdx.input.getX(),Gdx.input.getY(), 0);

            camera.unproject(temp);

            float touchX = temp.x;
            float touchY= temp.y;
            //System.out.println(touchX);
            if((touchX>=469*width/2546) && touchX<=(1069*width/2546) && (touchY>=526*height/1170) && touchY<=(813*height/1170)) {
                System.out.println("1");
                game.setScreen(new Terrain(game, 1));
            } else if((touchX>=1425*width/2546) && touchX<=(2025*width/2546) && (touchY>=526*height/1170) && touchY<=(813*height/1170)) {
                System.out.println("2");
                game.setScreen(new Terrain(game, 2));
            } else if((touchX>=469*width/2546) && touchX<=(1069*width/2546)&& (touchY>=137*height/1170) && touchY<=(418*height/1170)) {
                System.out.println("3");
                game.setScreen(new Terrain(game, 3));
            } else if ((touchX>=1425*width/2546) && touchX<=(2025*width/2546)&& (touchY>=137*height/1170) && touchY<=(418*height/1170)){
                System.out.println("4");
                game.setScreen(new Terrain(game, 4));
            }
        }
    }
    public SpriteBatch getBatch() {
        return batch;
    }

    public float getHeight() {
        return height;
    }

    public void setBatch(SpriteBatch batch) {
        this.batch = batch;
    }

    public void setHeight(float height) {
        this.height = height;
    }
}

