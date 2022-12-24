package com.mygame.game;

import com.badlogic.gdx.*;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import org.w3c.dom.ls.LSOutput;

import java.io.IOException;
import java.sql.SQLOutput;
import java.util.ArrayList;

public class DeathMode extends InputAdapter implements Screen {
    private ArrayList<Float> y_coordinates = new ArrayList();
    private ArrayList<Integer> x_coordinates = new ArrayList<>();

    public static float check_distance(float a, float b){
        float ans;
        if (a>b){
            ans = a-b;
        } else if (b>a){
            ans = b-a;
        } else {
            return 0;
        }
        return ans;
    }

    public static int check_within(float upper, float lower, float num_check){
        if (num_check >= lower) {
            if (num_check <= upper) {
                return 1;
            }
        }
        return 0;
    }

    //as convention, tank on left with bow on right is player 1, and on right is player 2 to begin with
    static int flag = 1;
    static float power_1;
    static float power_2;
    static float angle_1;
    static float angle_2;

    static int flag_menu = 0;
    static int resume = 0;
    static int pause = 0;

    static int show1 = 2;
    static int show2 = 2;
    public static int ResumeSavedGame = 0;

    Stage stage;
    static int count = 0;

    float time = 0;
    static float y_offset0 = 0;
    static float y_offset1 = 0;
    int x_offset = 0;
    float sinOffset = 0;
    float Speed = 1.0f;
    float x = 0;
    double y = 0;
    ArrayList<Explosion> explosions;
    static float height = Gdx.graphics.getHeight();
    static float width = Gdx.graphics.getWidth();
    static int count1 = (int) width - 50;
    static float dist_trajectory_player1_x = height;
    static float dist_trajectory_player1_y = width;
    static float dist_trajectory_player2_x = height;
    static float dist_trajectory_player2_y = width;
    private SpriteBatch batch = new SpriteBatch();
    private Texture in_game_menuTexture = new Texture("data/in_game_menu.png");
    private OrthographicCamera camera = new OrthographicCamera(width, height);
    private Texture p1_wonTexture = new Texture("data/p1_won.png");
    private Texture p2_wonTexture = new Texture("data/p2_won.png");
    private Texture abrams_right = new Texture("data/abrams_tank1.png");
    private Texture spectre_right = new Texture("data/spectre_tank1.png");
    private Texture tiger_right = new Texture("data/tiger_tank1.png");
    private Texture abrams_left = new Texture("data/abrams_tank2.png");
    private Texture spectre_left = new Texture("data/spectre_tank2.png");
    private Texture tiger_left = new Texture("data/tiger_tank2.png");
    private Texture backgroundTexture = new Texture("data/nightmare.png");
    private Texture groundTexture = new Texture("data/ground.png");
    private Texture pauseTexture = new Texture("data/pause_button.png");
    private Sprite p1_won = new Sprite(p1_wonTexture);
    private Sprite p2_won = new Sprite(p2_wonTexture);

    private Sprite backgroundSprite = new Sprite(backgroundTexture);
    private Sprite groundSprite = new Sprite(groundTexture);
    private Sprite in_game_menuSprite = new Sprite(in_game_menuTexture);

    private Texture player1_healthTexture = new Texture("data/player1_health.png");
    private Texture player2_healthTexture = new Texture("data/player2_health.png");
    private Sprite player1_healthSprite = new Sprite(player1_healthTexture);
    private Texture good_shotTexture = new Texture("data/good_shot.png");
    private Sprite good_shot = new Sprite(good_shotTexture);
    private Sprite player2_healthSprite = new Sprite(player2_healthTexture);
    private Texture navTexture = new Texture("data/nav.png");
    private Sprite navSprite = new Sprite(navTexture);
    private Sprite pauseSprite = new Sprite(pauseTexture);

    private Texture menuTexture = new Texture("data/menu.png");
    private Sprite menuSprite = new Sprite(menuTexture);
    Player player1, player2;
    Sprite player1_sprite, player2_sprite;
    protected Vector3 temp = new Vector3();
    public static MainGame game;
    private ArrayList<Integer> x_coord;
    private ArrayList<Float> y_coord;
    private Texture circlee = new Texture("data/nav.png");
    private ProjectileTrajectory Trajectory_show = new ProjectileTrajectory();
    private Sprite trajectorySprite = new Sprite(circlee);
    private Load loader = new Load();
    private Information Saver;
    Sound sound_explosion;
    Sound sound_error;

    public DeathMode(MainGame game) {

        for (int i = 0; i<width; i+=1) {
            float y = 10 + (float) Math.sin(0.02 * i) + (float) Math.cos(0.03 * i);
            y_coordinates.add(8 * y);
            x_coordinates.add(i);
        }

        DeathMode.game = game;
        ArrayList<Integer> x_ = new ArrayList<>();
        ArrayList<Float> y_ = new ArrayList<>();

        explosions = new ArrayList<Explosion>();

        this.x_coord = x_;
        this.y_coord = y_;
        sound_explosion = Gdx.audio.newSound(Gdx.files.internal("data/explosion_sound.wav"));
        sound_error = Gdx.audio.newSound(Gdx.files.internal("data/error.mp3"));
        camera.setToOrtho(false);
        Main.tank_bow_right.add(abrams_right);
        Main.tank_bow_right.add(spectre_right);
        Main.tank_bow_right.add(tiger_right);

        Main.tank_bow_left.add(abrams_left);
        Main.tank_bow_left.add(spectre_left);
        Main.tank_bow_left.add(tiger_left);
        int player1_idx = Main.player1_tank_idx;
        player1 = Player.getInstance(1, Main.tank_bow_right.get(2));
        int player2_idx = Main.player2_tank_idx;
        player2 = Player.getInstance(2, Main.tank_bow_left.get(2));
        player1_sprite = new Sprite(Main.tank_bow_right.get(2));
        player2_sprite = new Sprite(Main.tank_bow_left.get(2));
        backgroundSprite.setSize(width, height);
        groundSprite.setSize(groundSprite.getWidth() / 5, groundSprite.getHeight() / 5);
        player1_sprite.setSize(player1_sprite.getWidth() / 5, player1_sprite.getHeight() / 5);
        player2_sprite.setSize(player2_sprite.getWidth() / 5, player2_sprite.getHeight() / 5);
        p1_won.setSize(p1_won.getWidth() / 25, p1_won.getHeight() / 25);
        p1_won.setPosition(width / 2, height / 2);
        p2_won.setSize(p2_won.getWidth() / 25, p2_won.getHeight() / 25);
        p2_won.setPosition(width / 2, height / 2);
        player1_healthSprite.setSize(player1_healthSprite.getWidth() / 5, player1_healthSprite.getHeight() / 5);
        player2_healthSprite.setSize(player2_healthSprite.getWidth() / 5, player2_healthSprite.getHeight() / 5);
        navSprite.setSize(navSprite.getWidth() / 5, navSprite.getHeight() / 5);
        in_game_menuSprite.setSize(in_game_menuSprite.getWidth() / 5, in_game_menuSprite.getHeight() / 5);
        in_game_menuSprite.setPosition(858 * width / 2546, 188 * height / 1170);
        menuSprite.setSize(menuSprite.getWidth() / 5, menuSprite.getHeight() / 5);
        pauseSprite.setSize(pauseSprite.getWidth() / 5, pauseSprite.getHeight() / 5);
        float get_x = width / 4;
        float get_y = (10 + (float) Math.sin(3 * get_x / 50) + (float) Math.cos(2 * get_x / 50)) * 5;
        //System.out.println(get_x);
        //System.out.println(get_y);
        trajectorySprite.setSize(trajectorySprite.getWidth() / 45, trajectorySprite.getHeight() / 45);
        player1_sprite.setPosition(x_coordinates.get(count), y_coordinates.get(count));
        player2_sprite.setPosition(x_coordinates.get((count1)), y_coordinates.get((count1)));
        player1_healthSprite.setPosition(470 * width / 2546, 956 * height / 1170);
        player2_healthSprite.setPosition(1598 * width / 2546, 956 * height / 1170);
        navSprite.setPosition(280 * width / 2546, 120 * height / 1170);
        menuSprite.setPosition(0, 956 * height / 1170);
        pauseSprite.setPosition(2320 * width / 2546, 960 * height / 1170);
        batch = new SpriteBatch();

    }

    ShapeRenderer shapeRenderer = new ShapeRenderer();

    @Override
    public void show() {
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
    }

    int check_bomb;

    @Override
    public void render(float delta) {

        batch.begin();
        Gdx.gl.glClearColor(0f, 0f, 0f, 0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        backgroundSprite.draw(batch);
        menuSprite.draw(batch);


        if (DeathMode.show1 == 1) {
            DeathMode.angle_2 = 45f;
            DeathMode.power_2 = 50f;
            Trajectory_show.TrajectoryActor(new ProjectileController(45f, 50f), -100, trajectorySprite);

            Trajectory_show.act(50);
            Trajectory_show.draw(batch, 10, player1_sprite.getX() + 20, player1_sprite.getY() + 20);
        }
        if (DeathMode.show2 == 1) {
            DeathMode.angle_2 = 135f;
            DeathMode.power_2 = 50f;
            Trajectory_show.TrajectoryActor(new ProjectileController(135f, 50f), -100, trajectorySprite);
            Trajectory_show.act(50);
            Trajectory_show.draw(batch, 10, player2_sprite.getX() + 20, player2_sprite.getY() + 20);
        }
        for (Explosion explosion : explosions) {
            if (check_bomb < 40 && check_bomb != 0) {
                check_bomb += 1;
                explosion.draw(batch);
                sound_explosion.play(1.0f);
            } else {
                check_bomb = 0;
            }
        }
        batch.end();

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        for (int i = 0; i<width; i+=1){
            float y = 10+(float) Math.sin(0.02*i)+(float)Math.cos(0.03*i);
            shapeRenderer.setColor(64/255f, 33/255f, 15/255f, 1);
            shapeRenderer.rect(i, (float) 0, (float) 1,8*y);
        }
        shapeRenderer.end();

        batch.begin();

        player1_sprite.draw(batch);
        player2_sprite.draw(batch);
        if (DeathMode.flag_menu == 1) {
            in_game_menuSprite.draw(batch);
        }
        if (player1.getHealth() <= 0 && player2.getHealth() > 0) {
            System.out.println("player 1 lost");
        } else if (player1.getHealth() > 0 && player2.getHealth() <= 0) {
            System.out.println("player 2 lost");
        }
        if (player1.getHealth() <= 0 && player2.getHealth() > 0) {
            p2_won.draw(batch);
            System.out.println("player 1 lost");
            //try {
            //    Thread.sleep(1000);
            //} catch (InterruptedException e) {
            //    throw new RuntimeException(e);
            //}
            //game.setScreen(new MainMenu(game));
        } else if (player1.getHealth() > 0 && player2.getHealth() <= 0) {
            p1_won.draw(batch);
            System.out.println("player 2 lost");
        }
        batch.end();

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(1f, 1f, 1f, 1f);
        shapeRenderer.rect(50, 200, 100, 20);
        shapeRenderer.end();
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(1f, 0f, 0f, 1f);
        if (player1.getHealth() >= 0) {
            shapeRenderer.rect(50, 200, player1.getHealth(), 20);
        }
        shapeRenderer.end();

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(1f, 1f, 1f, 1f);
        shapeRenderer.rect(width - 150, 200, 100, 20);
        shapeRenderer.end();
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(1f, 0f, 0f, 1f);
        if (player2.getHealth() >= 0) {
            shapeRenderer.rect(width - 150, 200, player2.getHealth(), 20);
        }
        shapeRenderer.end();

        if (Gdx.input.isKeyPressed(Input.Keys.Z)) {
            //KEY FOR PLAYER1 TO MOVE TO THE LEFT
            if (count <= 0) {
                sound_error.play(2.0f);
            } else {
                count -= 1;
                if (count >= 0) {
                    player1_sprite.setPosition(x_coordinates.get(count), y_coordinates.get(count));
                    player1_sprite.setRotation((float) Math.atan(x_coordinates.get(count) - y_coordinates.get(count + 1)));
                    player1_sprite.setOrigin(0, 0);
                }
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.X)) {
            //KEY FOR PLAYER1 TO MOVE TO THE LEFT
            if (count >= width - 20) {
                sound_error.play(2.0f);
            } else {
                count += 1;
                if (count >= 0) {
                    player1_sprite.setPosition(x_coordinates.get(count), y_coordinates.get(count));
                    player1_sprite.setRotation((float) Math.atan(x_coordinates.get(count) - y_coordinates.get(count - 1)));
                    player1_sprite.setOrigin(0, 0);
                }
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.M)) {
            //KEY FOR PLAYER1 TO MOVE TO THE LEFT
            if (count1 >= 512-1) {
                sound_error.play(2.0f);
            } else {
                count1 += 1;
                if (count1 >= 0) {
                    player2_sprite.setPosition(x_coordinates.get(count1), y_coordinates.get(count1));
                    player2_sprite.setRotation((float) Math.atan(x_coordinates.get(count1) - y_coordinates.get(count1 - 1)));
                    player2_sprite.setOrigin(0, 0);
                }
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.N)) {
            //KEY FOR PLAYER1 TO MOVE TO THE LEFT
            if (count1 < 0) {
                sound_error.play(2.0f);
            } else {
                count1 -= 1;
                if (count1 >= 0) {
                    player2_sprite.setPosition(x_coordinates.get(count1), y_coordinates.get(count1));
                    player2_sprite.setRotation((float) Math.atan(x_coordinates.get(count1) - y_coordinates.get(count1 + 1)));
                    player2_sprite.setOrigin(0, 0);
                }
            }
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.C)) {
            player2_sprite.setPosition(x_coordinates.get(count1 + 10), y_coordinates.get(count1 + 10));
            float check = check_distance(player1_sprite.getX(), player2_sprite.getX());
            if (check_within(200, 100, check) == 1){
                player2.decreaseHealth50();
            } else {
                player2.decreaseHealth40();
            }

            if (explosions.isEmpty()) {
                System.out.println("1 Fired, control to player 2");
                explosions.add(new Explosion(player2_sprite.getX() - 10, player2_sprite.getY() - 10));
                check_bomb = 1;
                System.out.println(explosions.isEmpty());
            } else {
                ArrayList<Explosion> rem = new ArrayList<Explosion>();
                for (Explosion explosion : explosions) {
                    rem.add(explosion);
                }
                explosions.removeAll(rem);
                System.out.println("1 Fired, control to player 2");
                explosions.add(new Explosion(player2_sprite.getX() - 10, player2_sprite.getY() - 10));
                check_bomb = 1;
                System.out.println(explosions.isEmpty());
            }
        }


        if (Gdx.input.isKeyJustPressed(Input.Keys.B)) {
            float check = check_distance(player1_sprite.getX(), player2_sprite.getX());
            if (check_within(200, 100, check) == 1){
                player1.decreaseHealth50();
            } else {
                player1.decreaseHealth40();
            }
            if (count >= 10) {
                player1_sprite.setPosition(x_coordinates.get(count - 10), y_coordinates.get(count - 10));
            }
            if (explosions.isEmpty()) {
                System.out.println("1 Fired, control to player 2");
                explosions.add(new Explosion(player1_sprite.getX() - 10, player1_sprite.getY() - 10));
                check_bomb = 1;
                System.out.println(explosions.isEmpty());
            } else {
                ArrayList<Explosion> rem = new ArrayList<Explosion>();
                for (Explosion explosion : explosions) {
                    rem.add(explosion);
                }
                explosions.removeAll(rem);
                System.out.println("1 Fired, control to player 2");
                explosions.add(new Explosion(player1_sprite.getX() - 10, player1_sprite.getY() - 10));
                check_bomb = 1;
                System.out.println(explosions.isEmpty());
            }
        }
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
        player1.getTank_texture().dispose();
        player2.getTank_texture().dispose();
        player1_healthTexture.dispose();
        player2_healthTexture.dispose();
        navTexture.dispose();
        menuTexture.dispose();
        pauseTexture.dispose();
    }
    //need to handle menu here
    //void handleTouch(){
    //}
    public SpriteBatch getBatch() {
        return batch;
    }

    public float getWidth() {
        return width;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public void setBatch(SpriteBatch batch) {
        this.batch = batch;
    }
    public void handleTouch(){
        if (Gdx.input.isKeyPressed(Input.Keys.ENTER)){
            game.setScreen(new MainMenu(game));
        }
    }

}