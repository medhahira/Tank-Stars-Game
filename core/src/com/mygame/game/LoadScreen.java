package com.mygame.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.math.Vector3;

public class LoadScreen implements Screen {
	private SpriteBatch batch;
	private Texture backgroundTexture;
	private Sprite backgroundSprite;
	//Texture buttonTexture;
	//Sprite buttonSprite;
	//private static float BUTTON_RESIZE_FACTOR = 800f;
	//private static float START_VERT_POSITION_FACTOR = 2.7f;
	static MainGame game;
	//Vector3 temp = new Vector3();
	public LoadScreen(MainGame game){
		LoadScreen.game = game;
		float height= Gdx.graphics.getHeight();
		float width = Gdx.graphics.getWidth();
		batch = new SpriteBatch();
		backgroundTexture = new Texture("data/welcome_page.png");
		//buttonTexture = new Texture("data/start_button.png");
		backgroundSprite  = new Sprite(backgroundTexture);
		//buttonSprite = new Sprite(buttonTexture);
		backgroundSprite.setSize(width,height);
		backgroundSprite.setPosition(0,0);
		//buttonSprite.setSize(buttonSprite.getWidth()*(width/BUTTON_RESIZE_FACTOR), buttonSprite.getHeight()*(width/BUTTON_RESIZE_FACTOR));
		//buttonSprite.setPosition((width/2f - buttonSprite.getWidth()/2) , width/START_VERT_POSITION_FACTOR);
	}

	@Override
	public void show() {
	}

	@Override
	public void render(float delta) {
		ScreenUtils.clear(1, 0, 0, 1);
		batch.begin();
		backgroundSprite.draw(batch);
		//buttonSprite.draw(batch);
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
	public void dispose () {
		batch.dispose();
		//buttonTexture.dispose();
		backgroundTexture.dispose();
	}

	public void handleTouch() {

		if (Gdx.input.justTouched()) {
			game.setScreen(new MainMenu(game));
			System.out.println("here");
		}
	}

	public SpriteBatch getBatch() {
		return batch;
	}

	public Texture getBackgroundTexture() {
		return backgroundTexture;
	}

	public static MainGame getGame() {
		return game;
	}
	public void setBackgroundTexture(Texture backgroundTexture) {
		this.backgroundTexture = backgroundTexture;
	}

	public void setBatch(SpriteBatch batch) {
		this.batch = batch;
	}

	public void setBackgroundSprite(Sprite backgroundSprite) {
		this.backgroundSprite = backgroundSprite;
	}
}
