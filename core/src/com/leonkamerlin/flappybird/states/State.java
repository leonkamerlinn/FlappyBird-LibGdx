package com.leonkamerlin.flappybird.states;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.leonkamerlin.flappybird.utils.GameCamera;

/**
 * Created by Leon on 6.6.2017..
 */

public abstract class State {

    private Viewport mViewPort;
    protected OrthographicCamera mOrthographicCamera;
    protected Vector3 mMouse;
    protected GameStateManager mGameStaticManager;

    protected State(GameStateManager gameStateManager) {
        GameCamera gameCamera = new GameCamera();
        mGameStaticManager = gameStateManager;
        mOrthographicCamera = gameCamera.getCamera();
        mViewPort = gameCamera.getViewPort();
        mMouse = new Vector3();

    }

    protected abstract void handleInput(float dt);
    public abstract void update(float dt);
    public abstract void render(SpriteBatch spriteBatch);
    public abstract void dispose();


    public void resize(int width, int height) {
        mViewPort.update(width, height);
    }

    public void pause() {

    }

    public void resume() {

    }


}
