package com.leonkamerlin.flappybird.utils;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import static com.leonkamerlin.flappybird.FlappyBird.GAME_WORLD_HEIGHT;
import static com.leonkamerlin.flappybird.FlappyBird.GAME_WORLD_WIDTH;


/**
 * Created by Leon on 23.6.2017..
 */

public class GameCamera {
    private final OrthographicCamera mCamera;

    public GameCamera() {
        mCamera = new OrthographicCamera(GAME_WORLD_WIDTH, GAME_WORLD_HEIGHT);
        mCamera.translate(mCamera.viewportWidth / 2, mCamera.viewportHeight / 2);

    }

    public OrthographicCamera getCamera() {
        return mCamera;
    }

    public Viewport getViewPort() {
        Viewport viewPort = new StretchViewport(GAME_WORLD_WIDTH, GAME_WORLD_HEIGHT, mCamera);
        viewPort = new ScreenViewport(mCamera);
        viewPort = new FitViewport(GAME_WORLD_WIDTH, GAME_WORLD_HEIGHT, mCamera);
        return viewPort;
    }

    public float getX() {
        return getCamera().position.x - (getCamera().viewportWidth / 2);
    }

    public float getRightX() {
        return getX() + getCamera().viewportWidth;
    }
}
