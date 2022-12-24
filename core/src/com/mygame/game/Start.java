package com.mygame.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;

public class Start implements Screen{
    private SpriteBatch batch;
    private Texture backgroundTexture;
    private Texture b1v1Texture;
    private Texture bcompTexture;
    private Sprite backgroundSprite;
    private Sprite b1v1Sprite;
    private Sprite bcompSprite;
    private static float START_VERTICAL_PF = 3.0f;
    float height= Gdx.graphics.getHeight();
    float width = Gdx.graphics.getWidth();
    static MainGame game;
    private Vector3 touch_coord = new Vector3();
    private OrthographicCamera cam;

    public Start(MainGame game){
        Start.game = game;
        batch = new SpriteBatch();
        backgroundTexture = new Texture("data/start_1.png");
        b1v1Texture = new Texture("data/1v1_b.png");
        bcompTexture = new Texture("data/death.png");
        backgroundSprite  = new Sprite(backgroundTexture);
        b1v1Sprite = new Sprite(b1v1Texture);
        bcompSprite = new Sprite(bcompTexture);
        b1v1Sprite.setSize(b1v1Sprite.getWidth()/4, b1v1Sprite.getHeight()/4);
        bcompSprite.setSize(bcompSprite.getWidth()/4, bcompSprite.getHeight()/4);
        b1v1Sprite.setPosition(1750*width/2546 , 667*height/1170);
        bcompSprite.setPosition(1750*width/2546 , 349*height/1170);
        cam = new OrthographicCamera(width,height);
        cam.setToOrtho(false);
        backgroundSprite.setSize(width,height);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(1, 0, 0, 1);
        batch.begin();
        backgroundSprite.draw(batch);
        b1v1Sprite.draw(batch);
        bcompSprite.draw(batch);
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
        backgroundTexture.dispose();
        b1v1Texture.dispose();
        bcompTexture.dispose();
    }

    void handleTouch(){
        if(Gdx.input.justTouched()) {
            touch_coord.set(Gdx.input.getX(),Gdx.input.getY(), 0);

            cam.unproject(touch_coord);

            float touchX = touch_coord.x;
            float touchY= touch_coord.y;

            System.out.println(touchX);
            if((touchX>=b1v1Sprite.getX()) && touchX<=(b1v1Sprite.getX()+b1v1Sprite.getWidth()) && (touchY>=b1v1Sprite.getY()) && touchY<=(b1v1Sprite.getY()+b1v1Sprite.getHeight()) ) {
                Texture texture1 = new Texture("data/abrams_choice_tank.png");
                Main.tanks.add(texture1);
                Texture newTexture = texture1;
                game.setScreen(new TankChoice(game, newTexture,1));
            } else if ((touchX>=bcompSprite.getX()) && touchX<=(bcompSprite.getX()+bcompSprite.getWidth()) && (touchY>=bcompSprite.getY()) && touchY<=(bcompSprite.getY()+bcompSprite.getHeight()) ) {
                System.out.println("HI DEATH MODE");
                game.setScreen(new DeathMode(game));
            }
        }
    }

    public SpriteBatch getBatch() {
        return batch;
    }

    public Texture getBackgroundTexture() {
        return backgroundTexture;
    }

    public OrthographicCamera getCam() {
        return cam;
    }

    public Sprite getB1v1Sprite() {
        return b1v1Sprite;
    }

    public void setBackgroundTexture(Texture backgroundTexture) {
        this.backgroundTexture = backgroundTexture;
    }

    public void setB1v1Texture(Texture b1v1Texture) {
        this.b1v1Texture = b1v1Texture;
    }
}
