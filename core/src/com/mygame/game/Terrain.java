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

public class Terrain extends InputAdapter implements Screen{
    //as convention, tank on left with bow on right is player 1, and on right is player 2 to begin with
    public static int compare(float a, float b){
        if (a>=b){
            return 1;
        } else {
            return 0;
        }
    }
    static int flag = 1;

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
    static float height= Gdx.graphics.getHeight();
    static float width = Gdx.graphics.getWidth();
    static int count1 = (int)width-50;
    static float dist_trajectory_player1_x = height;
    static float dist_trajectory_player1_y = width;
    static float dist_trajectory_player2_x = height;
    static float dist_trajectory_player2_y = width;
    private SpriteBatch batch = new SpriteBatch();
    private Texture in_game_menuTexture = new Texture("data/in_game_menu.png");
    private OrthographicCamera camera = new OrthographicCamera(width,height);
    private Texture p1_wonTexture = new Texture("data/p1_won.png");
    private Texture p2_wonTexture = new Texture("data/p2_won.png");
    private Texture abrams_right = new Texture("data/abrams_tank1.png");
    private Texture spectre_right = new Texture("data/spectre_tank1.png");
    private Texture tiger_right = new Texture("data/tiger_tank1.png");
    private Texture abrams_left = new Texture("data/abrams_tank2.png");
    private Texture spectre_left = new Texture("data/spectre_tank2.png");
    private Texture tiger_left = new Texture("data/tiger_tank2.png");
    private Texture backgroundTexture = new Texture("data/backdrop.png");
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
    Ground ground;
    protected Vector3 temp = new Vector3();
    public static MainGame game;
    private ArrayList <Integer> x_coord;
    private ArrayList <Float> y_coord;
    private Texture circlee = new Texture("data/nav.png");
    private ProjectileTrajectory Trajectory_show = new ProjectileTrajectory();
    private Sprite trajectorySprite = new Sprite(circlee);
    private Load loader = new Load();
    private Information Saver;
    Sound sound_explosion;
    Sound sound_error;
    public Terrain (MainGame game, int ground_num){
        ground = Ground.getInstance(ground_num);

        Terrain.game = game;
        ArrayList <Integer> x_ = new ArrayList<>();
        ArrayList <Float> y_ = new ArrayList<>();

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
        player1 = Player.getInstance(1, Main.tank_bow_right.get(player1_idx));
        int player2_idx = Main.player2_tank_idx;
        player2 = Player.getInstance(2, Main.tank_bow_left.get(player2_idx));
        player1_sprite = new Sprite(player1.getTank_texture());
        player2_sprite = new Sprite(player2.getTank_texture());
        backgroundSprite.setSize(width,height);
        //System.out.println(Main.player1_tank_idx);
        //System.out.println(Main.player2_tank_idx);
        groundSprite.setSize(groundSprite.getWidth()/5, groundSprite.getHeight()/5);
        player1_sprite.setSize(player1_sprite.getWidth()/5, player1_sprite.getHeight()/5);
        player2_sprite.setSize(player2_sprite.getWidth()/5, player2_sprite.getHeight()/5);
        p1_won.setSize(p1_won.getWidth()/25, p1_won.getHeight()/25);
        p1_won.setPosition(width/2, height/2);
        p2_won.setSize(p2_won.getWidth()/25, p2_won.getHeight()/25);
        p2_won.setPosition(width/2, height/2);
        player1_healthSprite.setSize(player1_healthSprite.getWidth()/5, player1_healthSprite.getHeight()/5);
        player2_healthSprite.setSize(player2_healthSprite.getWidth()/5, player2_healthSprite.getHeight()/5);
        navSprite.setSize(navSprite.getWidth()/5, navSprite.getHeight()/5);
        in_game_menuSprite.setSize(in_game_menuSprite.getWidth()/5, in_game_menuSprite.getHeight()/5);
        in_game_menuSprite.setPosition(858*width/2546, 188*height/1170);
        menuSprite.setSize(menuSprite.getWidth()/5, menuSprite.getHeight()/5);
        pauseSprite.setSize(pauseSprite.getWidth()/5, pauseSprite.getHeight()/5);
        float get_x = width/4;
        float get_y = (10+(float) Math.sin(3*get_x/50)+(float)Math.cos(2*get_x/50))*5;
        //System.out.println(get_x);
        //System.out.println(get_y);
        trajectorySprite.setSize(trajectorySprite.getWidth()/45, trajectorySprite.getHeight()/45);
        player1_sprite.setPosition(ground.getX_coordinates().get(count),ground.getY_coordinates().get(count));
        player2_sprite.setPosition(ground.getX_coordinates().get((count1)), ground.getY_coordinates().get((count1)));
        player1_healthSprite.setPosition(470*width/2546, 956*height/1170);
        player2_healthSprite.setPosition(1598*width/2546, 956*height/1170);
        navSprite.setPosition(280*width/2546, 120*height/1170);
        menuSprite.setPosition(0, 956*height/1170);
        pauseSprite.setPosition(2320*width/2546,960*height/1170);
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

        if (ResumeSavedGame == 1)
        {
            try {
                Saver = loader.deserialize();
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
            player1_sprite.setX(Saver.getTank_1_x());
            player1_sprite.setY(Saver.getTank_1_y());
            player2_sprite.setX(Saver.getTank_2_x());
            player2_sprite.setY(Saver.getTank_2_y());
            ground.setNum_display(Saver.getGround_num());
            if (ground.getNum_display() == 1){
                ground.display(shapeRenderer);
            } else if (ground.getNum_display() == 2){
                ground.display2(shapeRenderer);
            } else if (ground.getNum_display() == 3){
                ground.display3(shapeRenderer);
            } else if (ground.getNum_display() == 4){
                ground.display4(shapeRenderer);
            }
            player1.setHealth(Saver.getTank_1_health());
            player2.setHealth(Saver.getTank_2_health());
            player1.setFuel(Saver.getTank_1_fuel());
            player2.setFuel(Saver.getTank_2_fuel());
            Terrain.count1 = Saver.getCount1();
            Terrain.count = Saver.getCount();
            Terrain.flag = Saver.getChance();
            Main.player1_tank_idx = Saver.getTank1();
            Main.player2_tank_idx = Saver.getTank2();
            ResumeSavedGame = 0;
        }

        batch.begin();
        Gdx.gl.glClearColor(0f, 0f, 0f, 0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        backgroundSprite.draw(batch);
        menuSprite.draw(batch);


        if (Terrain.show1 == 1) {
            Trajectory_show.TrajectoryActor(new ProjectileController(ProjectileController.angle, ProjectileController.power), -100, trajectorySprite);

            Trajectory_show.act(50);
            Trajectory_show.draw(batch, 10, player1_sprite.getX() + 20, player1_sprite.getY() + 20);
        }
        else if(Terrain.show2 == 1){
            Trajectory_show.TrajectoryActor(new ProjectileController(ProjectileController.angle, ProjectileController.power), -100, trajectorySprite);
            Trajectory_show.act(50);
            Trajectory_show.draw(batch, 10, player2_sprite.getX() + 20, player2_sprite.getY() + 20);
        }
        for (Explosion explosion: explosions){
            if (check_bomb < 40 && check_bomb!=0){
                check_bomb+= 1;
                explosion.draw(batch);
                sound_explosion.play(1.0f);
            }
            else{
                check_bomb = 0;
            }
        }
        batch.end();
        if (ground.getNum_display() == 1){
            ground.display(shapeRenderer);
        } else if (ground.getNum_display() == 2){
            ground.display2(shapeRenderer);
        } else if (ground.getNum_display() == 3){
            ground.display3(shapeRenderer);
        } else if (ground.getNum_display() == 4){
            ground.display4(shapeRenderer);
        }
        batch.begin();

        player1_sprite.draw(batch);
        player2_sprite.draw(batch);
        if (Terrain.flag_menu == 1){
            in_game_menuSprite.draw(batch);
        }
        if (player1.getHealth()<=0 && player2.getHealth()>0){
            System.out.println("player 1 lost");
        } else if (player1.getHealth()>0 && player2.getHealth()<=0){
            System.out.println("player 2 lost");
        }
        if (player1.getHealth()<=0 && player2.getHealth()>0){
            p2_won.draw(batch);
            System.out.println("player 1 lost");
            //try {
            //    Thread.sleep(1000);
            //} catch (InterruptedException e) {
            //    throw new RuntimeException(e);
            //}
            //game.setScreen(new MainMenu(game));
        } else if (player1.getHealth()>0 && player2.getHealth()<=0){
            p1_won.draw(batch);
            System.out.println("player 2 lost");
        }
        batch.end();

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(1f,1f,1f,1f);
        shapeRenderer.rect(50,200,100,20);
        shapeRenderer.end();
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(0f,0f,1f,1f);
        if (Terrain.dist_trajectory_player1_y>-10 && Terrain.dist_trajectory_player1_y<10 && Terrain.dist_trajectory_player1_x>-10 && Terrain.dist_trajectory_player1_x<10){
            System.out.println("10 HIT");
            player1.decreaseHealth10();
            Terrain.dist_trajectory_player1_x = width;
            Terrain.dist_trajectory_player1_y = height;
        }
        else if (Terrain.dist_trajectory_player1_y>-40 && Terrain.dist_trajectory_player1_y<40 && Terrain.dist_trajectory_player1_x>-40 && Terrain.dist_trajectory_player1_x<40){
            System.out.println("40 HIT");
            player1.decreaseHealth40();
            Terrain.dist_trajectory_player1_x = width;
            Terrain.dist_trajectory_player1_y = height;
        }
        if (player1.getHealth()>=0){
            shapeRenderer.rect(50,200,player1.getHealth(),20);
        }
        shapeRenderer.end();

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(1f,1f,1f,1f);
        shapeRenderer.rect(width-150,200,100,20);
        shapeRenderer.end();
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(0f,0f,1f,1f);
        if (Terrain.dist_trajectory_player2_y >-10 && Terrain.dist_trajectory_player2_y<10 && Terrain.dist_trajectory_player2_x>-10 && Terrain.dist_trajectory_player2_x<10){
            System.out.println("10 HIT");
            player2.decreaseHealth10();
            Terrain.dist_trajectory_player2_x = width;
            Terrain.dist_trajectory_player2_y = height;
        }
        else if (Terrain.dist_trajectory_player2_y>-40 && Terrain.dist_trajectory_player2_y<40 && Terrain.dist_trajectory_player2_x>-40 && Terrain.dist_trajectory_player2_x<40){
            System.out.println("40 HIT");
            player2.decreaseHealth40();
            Terrain.dist_trajectory_player2_x = width;
            Terrain.dist_trajectory_player2_y = height;
        }
        if (player2.getHealth()>=0){
            shapeRenderer.rect(width-150,200,player2.getHealth(),20);
        }
        shapeRenderer.end();

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(0f,0f,0f,1f);
        shapeRenderer.rect(40,50,50,15);
        shapeRenderer.end();
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(1f,1f,0f,1f);
        //System.out.println(player1.getFuel());
        if (player1.getFuel()>=0){
            shapeRenderer.rect(40,50,player1.getFuel()/2,15);
        }
        shapeRenderer.end();


        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(0f,0f,0f,1f);
        shapeRenderer.rect(width-90,50,50,15);
        shapeRenderer.end();
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(1f,1f,0f,1f);
        if (player2.getFuel()>=0){
            shapeRenderer.rect(width-90,50,player2.getFuel()/2,15);
        }
        shapeRenderer.end();

        if (Terrain.pause == 1)
        {
            //Saver = new Information(player1_sprite.getX(), player2_sprite.getX(), player1_sprite.getY(), player2_sprite.getY(), ground.getNum_display(), player1.getHealth(), player2.getHealth(), player1.getFuel(), player2.getFuel(), Terrain.flag, Terrain.count, Terrain.count1);
            System.out.println("Z");
            try {
                loader.serialize(player1_sprite.getX(), player2_sprite.getX(), player1_sprite.getY(), player2_sprite.getY(), ground.getNum_display(), player1.getHealth(), player2.getHealth(), player1.getFuel(), player2.getFuel(), Terrain.flag, Terrain.count, Terrain.count1, Main.player1_tank_idx, Main.player2_tank_idx);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Terrain.pause = 0;
        }

        if (Terrain.resume == 1)
        {
            System.out.println("X");
            try {
                Saver = loader.deserialize();
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
            player1_sprite.setX(Saver.getTank_1_x());
            player1_sprite.setY(Saver.getTank_1_y());
            player2_sprite.setX(Saver.getTank_2_x());
            player2_sprite.setY(Saver.getTank_2_y());
//            ground;
            player1.setHealth(Saver.getTank_1_health());
            player2.setHealth(Saver.getTank_2_health());
            player1.setFuel(Saver.getTank_1_fuel());
            player2.setFuel(Saver.getTank_2_fuel());
            Terrain.count1 = Saver.getCount1();
            Terrain.count = Saver.getCount();
            Terrain.flag = Saver.getChance();
            Terrain.resume = 0;
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.L)){
            Terrain.show1 = 1;
            Terrain.show2 = 0;
            ProjectileController.angle = 45f;
            ProjectileController.power = 50f;
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.K)){
            Terrain.show1 = 0;
            Terrain.show2 = 1;
            ProjectileController.angle = 135f;
            ProjectileController.power = 50f;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A)){
            Trajectory_show.TrajectoryActor(new ProjectileController(ProjectileController.angle, ProjectileController.power-=1), -100, trajectorySprite);
            System.out.println(Trajectory_show.getProjectileEquation().Xcoordinate(player2_sprite.getX()));
        }

        if (Gdx.input.isKeyPressed(Input.Keys.D)){
            Trajectory_show.TrajectoryActor(new ProjectileController(ProjectileController.angle, ProjectileController.power+=1), -100, trajectorySprite);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.W)){
            Trajectory_show.TrajectoryActor(new ProjectileController(ProjectileController.angle+=0.5, ProjectileController.power), -100, trajectorySprite);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)){
            Trajectory_show.TrajectoryActor(new ProjectileController(ProjectileController.angle-=0.5, ProjectileController.power), -100, trajectorySprite);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.M)){
            if(count>=width-20 || count1>=width-20){
                if (Terrain.compare(count, width-20) == 1){
                    System.out.println("Testing works");
                }
                sound_error.play(2.0f);
            }
            else if (Terrain.flag == 1){
                if (player1.getFuel()>=0){
                    player1.decreaseFuel();
                    count+=1;
                    if (count>=0){
                        player1_sprite.setPosition(ground.getX_coordinates().get(count),ground.getY_coordinates().get(count));
                        player1_sprite.setRotation((float) Math.atan(ground.getX_coordinates().get(count)-ground.getY_coordinates().get(count-1)));
                        player1_sprite.setOrigin(0,0);
                    }
                }
            }
            else {
                if (player2.getFuel()>=0){
                    //System.out.println("count");
                    //System.out.println(count1);
                    count1+=1;
                    player2.decreaseFuel();
                    player2_sprite.setPosition(ground.getX_coordinates().get(count1),ground.getY_coordinates().get(count1));
                    player2_sprite.setRotation((float) Math.atan(ground.getX_coordinates().get(count1)-ground.getY_coordinates().get(count1-1)));
                    player2_sprite.setOrigin(0,0);
                }
            }
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.F)){
            player1.setFuel(100);
            Terrain.dist_trajectory_player2_x = trajectorySprite.getX()-player2_sprite.getX();
            Terrain.dist_trajectory_player2_y = trajectorySprite.getY()-player2_sprite.getY();
            System.out.println(Terrain.dist_trajectory_player2_x);
            System.out.println(Terrain.dist_trajectory_player2_y);
            if (Terrain.dist_trajectory_player2_y>-40 && Terrain.dist_trajectory_player2_y<40 && Terrain.dist_trajectory_player2_x>-40 && Terrain.dist_trajectory_player2_x<40){
                System.out.println(player2.getHealth());
                if (Terrain.flag == 1){
                    Terrain.flag = 2;
                    Terrain.show2 = 2;
                    Terrain.show1 = 2;
                    player2_sprite.setPosition(ground.getX_coordinates().get(count1+10),ground.getY_coordinates().get(count1+10));
                    if (explosions.isEmpty()){
                        System.out.println("1 Fired, control to player 2");
                        explosions.add(new Explosion(player2_sprite.getX()-10, player2_sprite.getY()-10));
                        check_bomb = 1;
                        System.out.println(explosions.isEmpty());

                    } else {
                        ArrayList<Explosion> rem = new ArrayList<Explosion>();
                        for (Explosion explosion: explosions){
                            rem.add(explosion);
                        }
                        explosions.removeAll(rem);
                        System.out.println("1 Fired, control to player 2");
                        explosions.add(new Explosion(player2_sprite.getX()-10, player2_sprite.getY()-10));
                        check_bomb = 1;
                        System.out.println(explosions.isEmpty());
                    }
                }
            }
            else if (Terrain.flag == 1){
                Terrain.flag = 2;
            }
            Terrain.show2 = 2;
            Terrain.show1 = 2;
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.G)){
            player2.setFuel(100);
            Terrain.dist_trajectory_player1_x = trajectorySprite.getX()-player1_sprite.getX();
            Terrain.dist_trajectory_player1_y = trajectorySprite.getY()-player1_sprite.getY();
            System.out.println(Terrain.dist_trajectory_player1_x);
            System.out.println(Terrain.dist_trajectory_player1_y);
            System.out.println(trajectorySprite.getX());
            System.out.println(player2_sprite.getX());
            System.out.println(Terrain.flag);
            System.out.println(Terrain.dist_trajectory_player1_x);
            System.out.println(Terrain.dist_trajectory_player1_y);
            if (trajectorySprite.getX()>player1_sprite.getX()-40 && trajectorySprite.getX()<player1_sprite.getX()+40) {
                if (Terrain.flag == 2) {
                    Terrain.flag = 1;
                    Terrain.show2 = 2;
                    Terrain.show1 = 2;
                    player1_sprite.setPosition(ground.getX_coordinates().get(count - 10), ground.getY_coordinates().get(count - 10));
                    if (explosions.isEmpty()) {
                        System.out.println("2 Fired, control to player 1");
                        explosions.add(new Explosion(player1_sprite.getX() + 10, player1_sprite.getY() + 10));
                        check_bomb = 1;
                        System.out.println(explosions.isEmpty());
                    } else {
                        ArrayList<Explosion> rem = new ArrayList<Explosion>();
                        for (Explosion explosion : explosions) {
                            rem.add(explosion);
                        }
                        explosions.removeAll(rem);
                        System.out.println("2 Fired, control to player 1");
                        explosions.add(new Explosion(player1_sprite.getX() + 10, player1_sprite.getY() + 10));
                        check_bomb = 1;
                        System.out.println(explosions.isEmpty());
                    }
                }
            }
            else if (Terrain.flag == 2){
                Terrain.flag = 1;
                Terrain.show2 = 2;
                Terrain.show1 = 2;
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.N)){
            if (count<=0 || count1<=0){
                System.out.println("less count");
                sound_error.play(2.0f);
            }
            else if (Terrain.flag == 2){
                if (player2.getFuel()>=0){
                    System.out.println("N working for 2");
                    System.out.println("count");
                    System.out.println(count1);
                    count1-=1;
                    player2.decreaseFuel();
                    System.out.println(player2.getFuel());
                    System.out.println("fuel");
                    System.out.println(player2.getFuel());
                    player2_sprite.setPosition(ground.getX_coordinates().get(count1),ground.getY_coordinates().get(count1));
                    player2_sprite.setRotation((float) Math.atan(ground.getX_coordinates().get(count1+1)-ground.getY_coordinates().get(count1)));
                    player2_sprite.setOrigin(0,0);
                }
            }
            else {
                if (player1.getFuel()>=0){
                    count-=1;
                    player1.decreaseFuel();
                    player1_sprite.setPosition(ground.getX_coordinates().get(count),ground.getY_coordinates().get(count));
                    player1_sprite.setRotation((float) Math.atan(ground.getX_coordinates().get(count+1)-ground.getY_coordinates().get(count1)));
                    player1_sprite.setOrigin(0,0);
                }
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.ENTER)){
            game.setScreen(new MainMenu(game));
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
    public void handleTouch() {
        if (Gdx.input.justTouched()) {
            temp.set(Gdx.input.getX(), Gdx.input.getY(), 0);

            camera.unproject(temp);
            float touchX = temp.x;
            float touchY = temp.y;
            //System.out.println(touchX);
            if ((touchX >= menuSprite.getX()) && touchX <= (menuSprite.getX() + menuSprite.getWidth()) && (touchY >= menuSprite.getY()) && touchY <= (menuSprite.getY() + menuSprite.getHeight())) {
                if(Terrain.flag_menu == 0){
                    Terrain.flag_menu = 1;
                }
            } if ((touchX >= 1131*width/2546) && touchX <= (1417*width/2546) && (touchY >= 567*height/1170 && touchY <= (650*height/1170))){
                Terrain.pause = 1;
                Terrain.resume = 0;
                System.out.println("PAUSE");
                Terrain.flag_menu = 0;
            } if ((touchX >= 1131*width/2546) && touchX <= (1417*width/2546) && (touchY >= 414*height/1170 && touchY <= (500*height/1170))){
                Terrain.resume= 1;
                Terrain.pause = 0;
                System.out.println("RESUME");
                Terrain.flag_menu = 0;
            }if ((touchX >= 1131*width/2546) && touchX <= (1417*width/2546) && (touchY >= 264*height/1170 && touchY <= (350*height/1170))){
                System.out.println("SAVE");
                Terrain.flag_menu = 0;
                game.setScreen(new MainMenu(game));
            }
        }
    }

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

}
