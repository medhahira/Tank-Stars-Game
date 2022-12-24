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

public class TankChoice implements Screen {
    float height= Gdx.graphics.getHeight();
    float width = Gdx.graphics.getWidth();
    private SpriteBatch batch = new SpriteBatch();
    private Texture background1Texture = new Texture("data/tank_choice_back1.png");
    private Texture background2Texture = new Texture("data/tank_choice_back2.png");
    private Texture arrowRightTexture = new Texture("data/arrow_right.png");
    private Texture arrowLeftTexture = new Texture("data/arrow_left.png");
    private Texture chooseButtonTexture = new Texture("data/choose_tank.png");
    private Sprite background1Sprite = new Sprite(background1Texture);
    private Sprite background2Sprite = new Sprite(background2Texture);
    private Sprite arrowRightSprite = new Sprite(arrowRightTexture);
    private Sprite arrowLeftSprite = new Sprite(arrowLeftTexture);
    private Sprite chooseButtonSprite = new Sprite(chooseButtonTexture);
    private OrthographicCamera camera = new OrthographicCamera(width,height);

    protected Vector3 temp = new Vector3();

    public static MainGame game;
    static Texture tankTexture;
    static int player_num;
    //Sprite tankSprite = new Sprite(tankTexture);
    public TankChoice(MainGame game, Texture tankTexture, int player_num){
        TankChoice.game = game;
        TankChoice.tankTexture = tankTexture;
        TankChoice.player_num = player_num;
        camera.setToOrtho(false);
        //Sprite tankSprite = new Sprite(tankTexture);

        arrowRightSprite.setSize(arrowRightSprite.getWidth()/5, arrowRightSprite.getHeight()/5);
        arrowLeftSprite.setSize(arrowLeftSprite.getWidth()/5, arrowLeftSprite.getHeight()/5);
        chooseButtonSprite.setSize(chooseButtonSprite.getWidth()/5, chooseButtonSprite.getHeight()/5);
        //tankSprite.setSize(tankSprite.getWidth()/5, tankSprite.getHeight()/5);

        arrowRightSprite.setPosition(2331*width/2546, 513*height/1170);
        arrowLeftSprite.setPosition(1661*width/2546, 513*height/1170);
        chooseButtonSprite.setPosition(1829*width/2546, 125*height/1170);
        //tankSprite.setPosition(1600*width/2546, 466*height/1170);
        if (player_num == 1){
            background1Sprite.setSize(width,height);
        }
        else if (player_num == 2){
            background2Sprite.setSize(width,height);
        }

        Texture texture2 = new Texture("data/spectre_choice_tank.png");
        Texture texture3 = new Texture("data/tiger_choice_tank.png");

        //Main.tanks.add(texture1);
        Main.tanks.add(texture2);
        Main.tanks.add(texture3);
        //Sprite tankSprite = new Sprite(tankTexture);
    }


    @Override
    public void show(){};

    @Override
    public void render(float delta){
        batch.begin();
        if (TankChoice.player_num == 1){
            background1Sprite.draw(batch);
        }
        else if (TankChoice.player_num == 2){
            background2Sprite.draw(batch);
        }
        arrowRightSprite.draw(batch);
        arrowLeftSprite.draw(batch);
        chooseButtonSprite.draw(batch);
        Sprite s = new Sprite(tankTexture);
        s.setSize(s.getWidth()/5, s.getHeight()/5);
        s.setPosition(1815*width/2546, 420*height/1170);
        s.draw(batch);
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
        background1Texture.dispose();
        background2Texture.dispose();
        tankTexture.dispose();
        chooseButtonTexture.dispose();
        arrowRightTexture.dispose();
        arrowLeftTexture.dispose();
    };
    void handleTouch(){
        if(Gdx.input.justTouched()) {
            temp.set(Gdx.input.getX(),Gdx.input.getY(), 0);

            camera.unproject(temp);

            float touchX = temp.x;
            float touchY= temp.y;

            //System.out.println(touchX);
            if((touchX>=chooseButtonSprite.getX()) && touchX<=(chooseButtonSprite.getX()+chooseButtonSprite.getWidth()) && (touchY>=chooseButtonSprite.getY()) && touchY<=(chooseButtonSprite.getY()+chooseButtonSprite.getHeight()) ) {
                System.out.println("TANK CHOSEN");
                Texture newTexture;
                newTexture = Main.tanks.get(0);
                if (TankChoice.player_num == 1){
                    Main.player1_tank_idx = Main.tanks.indexOf(TankChoice.tankTexture);
                    System.out.println(Main.player1_tank_idx);
                    game.setScreen(new TankChoice(game, newTexture, 2));
                } else if (TankChoice.player_num == 2){
                    Main.player2_tank_idx = Main.tanks.indexOf(TankChoice.tankTexture);
                    System.out.println(Main.player2_tank_idx);
                    System.out.println("both players have chosen");
                    game.setScreen(new GroundChoice(game));
                }
            } else if ((touchX>=arrowRightSprite.getX()) && touchX<=(arrowRightSprite.getX()+arrowRightSprite.getWidth()) && (touchY>=arrowRightSprite.getY()) && touchY<=(arrowRightSprite.getY()+arrowRightSprite.getHeight())) {
                System.out.println("RIGHT");
                int idx = Main.tanks.indexOf(TankChoice.tankTexture);
                System.out.println(idx);
                Texture newTexture;
                if (idx<2){
                    newTexture = Main.tanks.get(idx + 1);
                }
                else {
                    newTexture = Main.tanks.get(0);
                }
                if (player_num == 1){
                    game.setScreen(new TankChoice(game,newTexture,1));
                }
                else if (player_num == 2){
                    game.setScreen(new TankChoice(game,newTexture,2));
                }
            } else if ((touchX>=arrowLeftSprite.getX()) && touchX<=(arrowLeftSprite.getX()+arrowLeftSprite.getWidth()) && (touchY>=arrowLeftSprite.getY()) && touchY<=(arrowLeftSprite.getY()+arrowLeftSprite.getHeight())){
                System.out.println("LEFT");
                int idx = Main.tanks.indexOf(TankChoice.tankTexture);
                System.out.println(idx);
                Texture newTexture;
                if (idx<1){
                    newTexture = Main.tanks.get(2);
                }
                else {
                    newTexture = Main.tanks.get(idx-1);
                }
                if (player_num == 1){
                    game.setScreen(new TankChoice(game,newTexture,1));
                }
                else if (player_num == 2){
                    game.setScreen(new TankChoice(game,newTexture,2));
                }
            }
        }
    }

    public SpriteBatch getBatch() {
        return batch;
    }

    public float getHeight() {
        return height;
    }

    public Texture getBackground1Texture() {
        return background1Texture;
    }

    public void setBatch(SpriteBatch batch) {
        this.batch = batch;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public void setBackground1Texture(Texture background1Texture) {
        this.background1Texture = background1Texture;
    }
}

