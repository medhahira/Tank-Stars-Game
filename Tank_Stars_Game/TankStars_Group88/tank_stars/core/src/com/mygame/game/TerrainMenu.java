package com.mygame.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

import java.util.ArrayList;

public class TerrainMenu implements Screen {
    //as convention, tank on left with bow on right is player 1, and on right is player 2 to begin with


    float height = Gdx.graphics.getHeight();
    float width = Gdx.graphics.getWidth();
    private SpriteBatch batch = new SpriteBatch();
    private OrthographicCamera camera = new OrthographicCamera(width, height);
    private Texture in_game_menu = new Texture("data/in_game_menu.png");
    private Sprite in_game_menu_sprite = new Sprite(in_game_menu);
    private Texture abrams_right = new Texture("data/abrams_tank1.png");
    private Texture spectre_right = new Texture("data/spectre_tank1.png");
    private Texture tiger_right = new Texture("data/tiger_tank1.png");
    private Texture abrams_left = new Texture("data/abrams_tank2.png");
    private Texture spectre_left = new Texture("data/spectre_tank2.png");
    private Texture tiger_left = new Texture("data/tiger_tank2.png");
    private Texture backgroundTexture = new Texture("data/backdrop.png");
    private Texture groundTexture = new Texture("data/ground.png");
    private Texture pauseTexture = new Texture("data/pause_button.png");

    private Sprite backgroundSprite = new Sprite(backgroundTexture);
    private Sprite groundSprite = new Sprite(groundTexture);
    private Texture player1_healthTexture = new Texture("data/player1_health.png");
    private Texture player2_healthTexture = new Texture("data/player2_health.png");
    private Sprite player1_healthSprite = new Sprite(player1_healthTexture);
    private Sprite player2_healthSprite = new Sprite(player2_healthTexture);
    private Texture navTexture = new Texture("data/nav.png");
    private Sprite navSprite = new Sprite(navTexture);
    private Sprite pauseSprite = new Sprite(pauseTexture);

    private Texture menuTexture = new Texture("data/menu.png");
    private Sprite menuSprite = new Sprite(menuTexture);

    int player1_idx = Main.player1_tank_idx;
    private Texture player1_tankTexture;
    int player2_idx = Main.player2_tank_idx;
    private Texture player2_tankTexture;

    private Sprite player1_tankSprite;
    private Sprite player2_tankSprite;
    protected Vector3 temp = new Vector3();
    public static MainGame game;
    public static int ground_num;

    public TerrainMenu(MainGame game, int ground_num) {
        TerrainMenu.ground_num = ground_num;
        TerrainMenu.game = game;
        camera.setToOrtho(false);
        Main.tank_bow_right.add(abrams_right);
        Main.tank_bow_right.add(spectre_right);
        Main.tank_bow_right.add(tiger_right);

        Main.tank_bow_left.add(abrams_left);
        Main.tank_bow_left.add(spectre_left);
        Main.tank_bow_left.add(tiger_left);

        player1_tankTexture = Main.tank_bow_right.get(player1_idx);
        player2_tankTexture = Main.tank_bow_left.get(player2_idx);
        player1_tankSprite = new Sprite(player1_tankTexture);
        player2_tankSprite = new Sprite(player2_tankTexture);
        in_game_menu_sprite.setSize(in_game_menu.getWidth()/10, in_game_menu_sprite.getHeight()/10);
        in_game_menu_sprite.setPosition(858*width/2546, 261*height/1170);
        backgroundSprite.setSize(width, height);
        System.out.println(Main.player1_tank_idx);
        System.out.println(Main.player2_tank_idx);
        groundSprite.setSize(groundSprite.getWidth() / 5, groundSprite.getHeight() / 5);
        player1_tankSprite.setSize(player1_tankSprite.getWidth() / 5, player1_tankSprite.getHeight() / 5);
        player2_tankSprite.setSize(player2_tankSprite.getWidth() / 5, player2_tankSprite.getHeight() / 5);
        player1_healthSprite.setSize(player1_healthSprite.getWidth() / 5, player1_healthSprite.getHeight() / 5);
        player2_healthSprite.setSize(player2_healthSprite.getWidth() / 5, player2_healthSprite.getHeight() / 5);
        navSprite.setSize(navSprite.getWidth() / 5, navSprite.getHeight() / 5);
        menuSprite.setSize(menuSprite.getWidth() / 5, menuSprite.getHeight() / 5);
        pauseSprite.setSize(pauseSprite.getWidth() / 5, pauseSprite.getHeight() / 5);

        player1_tankSprite.setPosition(154 * width / 2546, 430 * height / 1170);
        player2_tankSprite.setPosition(1808 * width / 2546, 400 * height / 1170);
        player1_healthSprite.setPosition(470 * width / 2546, 956 * height / 1170);
        player2_healthSprite.setPosition(1598 * width / 2546, 956 * height / 1170);
        navSprite.setPosition(280 * width / 2546, 120 * height / 1170);
        menuSprite.setPosition(0, 956 * height / 1170);
        pauseSprite.setPosition(2320 * width / 2546, 960 * height / 1170);

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        batch.begin();
        backgroundSprite.draw(batch);
        groundSprite.draw(batch);
        player1_tankSprite.draw(batch);
        player2_tankSprite.draw(batch);
        player1_healthSprite.draw(batch);
        player2_healthSprite.draw(batch);
        navSprite.draw(batch);
        menuSprite.draw(batch);
        pauseSprite.draw(batch);
        in_game_menu_sprite.draw(batch);
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
        backgroundTexture.dispose();
        groundTexture.dispose();
        player1_tankTexture.dispose();
        player2_tankTexture.dispose();
        player1_healthTexture.dispose();
        player2_healthTexture.dispose();
        navTexture.dispose();
        menuTexture.dispose();
        pauseTexture.dispose();
    }

    public void handleTouch() {
        if (Gdx.input.justTouched()) {
            game.setScreen(new Terrain(game, ground_num));
        }
    }

    public SpriteBatch getBatch() {
        return batch;
    }

    public float getWidth() {
        return width;
    }

    public int getPlayer1_idx() {
        return player1_idx;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public void setBatch(SpriteBatch batch) {
        this.batch = batch;
    }
}