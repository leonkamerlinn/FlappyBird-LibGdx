package com.leonkamerlin.flappybird;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.leonkamerlin.flappybird.sprites.Badlogic;
import com.leonkamerlin.flappybird.states.GameStateManager;
import com.leonkamerlin.flappybird.states.MenuState;
import com.leonkamerlin.flappybird.utils.GameCamera;

public class FlappyBird extends ApplicationAdapter {
	public static final float  GAME_WORLD_WIDTH = 381;
	public static final float GAME_WORLD_HEIGHT = 677;
	public static final String TITLE = "Flappy Bird";
	private GameStateManager mGameStateManager;
	private SpriteBatch mSpriteBatch;
	private GameCamera mCamera;



	@Override
	public void create () {
		mCamera = new GameCamera();
		mSpriteBatch = new SpriteBatch();
		mGameStateManager = new GameStateManager();
		mGameStateManager.push(new MenuState(mGameStateManager, mCamera));

	}

	@Override
	public void render () {
		super.render();
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		mSpriteBatch.setProjectionMatrix(mCamera.getCamera().combined);
		mGameStateManager.update(Gdx.graphics.getDeltaTime());
		mGameStateManager.render(mSpriteBatch);
		mCamera.getCamera().update();

	}

	@Override
	public void dispose () {
		mGameStateManager.dispose();
	}

	@Override
	public void resize(int width, int height) {
		mGameStateManager.resize(width, height);
	}

	@Override
	public void pause() {
		mGameStateManager.pause();
	}

	@Override
	public void resume() {

		mGameStateManager.resume();
	}
}
