package com.mygame.game;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.graphics.OrthographicCamera;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.BitmapFont.*;
public class Resume extends ApplicationAdapter implements Screen {

    private BitmapFont font;
    private Terrain background;
    private SpriteBatch batch = new SpriteBatch();
    private Texture resumeTexture;
    private Sprite resumeSprite;
    private float width;
    private float height;
    Vector3 temp = new Vector3();
    public static MainGame game;
    private static float RESUME_VERTICAL_PF = 3.0f;
    public OrthographicCamera camera;

    public Resume(MainGame game) {
        Resume.game = game;
        background = new Terrain(new MainGame(), 1);
        batch = new SpriteBatch();
        font = new BitmapFont(Gdx.files.internal("data/ken.fnt"));

        //BitmapFont.TextBounds bounds = font.getBounds("Hello World");

        height = Gdx.graphics.getHeight();
        width = Gdx.graphics.getWidth();
        camera = new OrthographicCamera(width,height);
        resumeTexture = new Texture("data/resume_screen.png");
        resumeSprite = new Sprite(resumeTexture);
        resumeSprite.setSize(width, height);
        resumeSprite.setPosition(0, 0);
        background.ResumeSavedGame = 1;
        background.render(0);
    }

    @Override
    public void create() {

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(26 / 255f, 41 / 255f, 106 / 255f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        resumeSprite.draw(batch);
        batch.end();

        handleTouch();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        batch.dispose();
        resumeTexture.dispose();
    }

    public SpriteBatch getBatch() {
        return batch;
    }

    public void setBatch(SpriteBatch batch) {
        this.batch = batch;
    }

    public void handleTouch() {
        if (Gdx.input.justTouched()) {
            temp.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            System.out.println("HI");
            float touchX = temp.x;
            float touchY = temp.y;
            System.out.println(touchX);
            System.out.println(758*width/2546);
            System.out.println(touchY);
            game.setScreen(new Terrain(game, 1));
            //System.out.println(touchX);
            if ((touchX >= 758*width/2546) && touchX <= (1788*width/2546) && (touchY >= 930*height/1170) && touchY <= (1118*height/1170)){
                System.out.println("SAVED GAME 1");

            }
        }
    }
}
