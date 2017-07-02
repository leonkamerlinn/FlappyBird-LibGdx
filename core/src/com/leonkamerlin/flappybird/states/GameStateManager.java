package com.leonkamerlin.flappybird.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.leonkamerlin.flappybird.utils.GameCamera;

import java.util.Stack;

/**
 * Created by Leon on 6.6.2017..
 */

public class GameStateManager {
    private Stack<State> mState;

    public GameStateManager() {
        mState = new Stack<State>();
    }

    public void set(State newState) {
        mState.pop();
        mState.push(newState);
        mState.peek().resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    public void pop() {
        mState.pop();
    }

    public void push(State state) {
        mState.push(state);
    }


    public void update(float dt) {
        mState.peek().update(dt);
    }

    public void render(SpriteBatch sb) {
        mState.peek().render(sb);
    }

    public void dispose() {
        mState.peek().dispose();
    }

    public void resize(int width, int height) {
        mState.peek().resize(width, height);
    }

    public void pause() {
        mState.peek().pause();
    }

    public void resume() {
        mState.peek().resume();
    }


}
