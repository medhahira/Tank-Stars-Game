package com.mygame.game;


import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.math.Vector3;
import sun.awt.im.InputMethodManager;

public class MainMenu implements Screen{
    private SpriteBatch batch;
    private Texture backgroundTexture;
    private Texture startTexture;
    private Texture resumeTexture;
    private Texture exitTexture;
    private Sprite backgroundSprite;
    private Sprite startSprite;
    private Sprite resumeSprite;
    private Sprite exitSprite;
    private OrthographicCamera camera;

    Vector3 temp = new Vector3();
    public static MainGame game;
    //private static float BUTTON = 512f;
    private static float START_VERTICAL_PF = 3.0f;
    private static float RESUME_VERTICAL_PF = 5.0f;
    private static float EXIT_VERTICAL_PF = 16.0f;
    public MainMenu (MainGame game){
        MainMenu.game = game;
        batch = new SpriteBatch();
        float height= Gdx.graphics.getHeight();
        float width = Gdx.graphics.getWidth();
        camera = new OrthographicCamera(width,height);
        camera.setToOrtho(false);

        backgroundTexture = new Texture("data/Menu_background.png");
        startTexture = new Texture("data/start_button.png");
        resumeTexture = new Texture("data/resume_button.png");
        exitTexture = new Texture("data/exit_button.png");

        backgroundSprite = new Sprite(backgroundTexture);
        startSprite= new Sprite(startTexture);
        resumeSprite = new Sprite(resumeTexture);
        exitSprite = new Sprite(exitTexture);

        startSprite.setSize(startSprite.getWidth()/5, startSprite.getHeight()/5);
        resumeSprite.setSize(resumeSprite.getWidth()/5, resumeSprite.getHeight()/5);
        exitSprite.setSize(exitSprite.getWidth()/5, exitSprite.getHeight()/5);

        //startSprite.setPosition(327*width/2546, 220*height/1170);
        startSprite.setPosition(1053*width/2546, 220*height/1170);
        resumeSprite.setPosition(327*width/2546, 220*height/1170);
        exitSprite.setPosition(1743*width/2546, 220*height/1170);
        backgroundSprite.setSize(width, height);

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        //Gdx.gl.glClearColor(40/255f, 67/255f, 135/255f, 1);
        //batch.setProjectionMatrix(camera.combined);
        //ScreenUtils.clear(26, 41, 106, 100);
        batch.begin();
        backgroundSprite.draw(batch);
        startSprite.draw(batch);
        resumeSprite.draw(batch);
        exitSprite.draw(batch);
        //batch.draw(backgroundTexture, 0, 0);
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
    public void dispose(){
        batch.dispose();
        startTexture.dispose();
        resumeTexture.dispose();
        exitTexture.dispose();
    }
    void handleTouch() {
        if(Gdx.input.justTouched()) {
            temp.set(Gdx.input.getX(),Gdx.input.getY(), 0);

            camera.unproject(temp);

            float touchX = temp.x;
            float touchY= temp.y;

            System.out.println(touchX);
            if((touchX>=startSprite.getX()) && touchX<=(startSprite.getX()+startSprite.getWidth()) && (touchY>=startSprite.getY()) && touchY<=(startSprite.getY()+startSprite.getHeight()) ) {
                System.out.println("start");
                game.setScreen(new Start(game));
            } else if ((touchX>=resumeSprite.getX()) && touchX<=(resumeSprite.getX()+resumeSprite.getWidth()) && (touchY>=resumeSprite.getY()) && touchY<=(resumeSprite.getY()+resumeSprite.getHeight()) ) {
                System.out.println("resume");
                game.setScreen(new Resume(game));
            } else if((touchX>=exitSprite.getX()) && touchX<=(exitSprite.getX()+exitSprite.getWidth()) && (touchY>=exitSprite.getY()) && touchY<=(exitSprite.getY()+exitSprite.getHeight())) {
                System.out.println("exit");
                Gdx.app.exit();
            }
        }
    }

    public SpriteBatch getBatch() {
        return batch;
    }

    public Texture getResumeTexture() {
        return resumeTexture;
    }

    public Texture getExitTexture() {
        return exitTexture;
    }

    public void setBatch(SpriteBatch batch) {
        this.batch = batch;
    }

    public void setBackgroundTexture(Texture backgroundTexture) {
        this.backgroundTexture = backgroundTexture;
    }

    public void setCamera(OrthographicCamera camera) {
        this.camera = camera;
    }
}
