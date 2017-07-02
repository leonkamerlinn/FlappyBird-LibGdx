package com.leonkamerlin.flappybird.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

import static com.leonkamerlin.flappybird.FlappyBird.GAME_WORLD_HEIGHT;
import static com.leonkamerlin.flappybird.FlappyBird.GAME_WORLD_WIDTH;

/**
 * Created by Leon on 24.6.2017..
 */

public abstract class BaseSprite {
    private final Sprite mSprite;
    private final Texture mTexture;
    private boolean mCollided;
    private Vector3 mPosition;
    private Vector3 mVelocity;

    public BaseSprite(Sprite sprite) {
        mSprite = sprite;
        mTexture = sprite.getTexture();
        resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        mPosition = new Vector3(0, 0, 0);
        mVelocity = new Vector3(0, 0, 0);
        mCollided = false;
    }

    public void setCollided(boolean collided) {
        mCollided = collided;
    }
    public boolean isCollided() {
        return mCollided;
    }

    public Sprite getSprite() {
        return mSprite;
    }

    public void resize(int width, int height) {
        float w_ratio = mTexture.getWidth() * (GAME_WORLD_WIDTH / width);
        float h_ratio = mTexture.getHeight() * (GAME_WORLD_HEIGHT / height);
        mSprite.setSize(w_ratio, h_ratio);

    }

    public void resize(int width, int height, float scale) {
        float w = mTexture.getWidth() * (GAME_WORLD_WIDTH / width) * Gdx.graphics.getDensity();
        float h = mTexture.getHeight() * (GAME_WORLD_HEIGHT / height) * Gdx.graphics.getDensity();
        mSprite.setSize(w * scale, h * scale);

    }



    public void fillWidth(float percent) {
        float w_ratio = mTexture.getWidth() * (GAME_WORLD_WIDTH / Gdx.graphics.getWidth());
        float h_ratio = mTexture.getHeight() * (GAME_WORLD_HEIGHT / Gdx.graphics.getHeight());
        float width = (GAME_WORLD_WIDTH / 100) * percent;
        mSprite.setSize((width / w_ratio) * w_ratio, (width / w_ratio) * h_ratio);

    }

    public void fillHeight(float percent) {
        float w_ratio = mTexture.getWidth() * (GAME_WORLD_WIDTH / Gdx.graphics.getWidth());
        float h_ratio = mTexture.getHeight() * (GAME_WORLD_HEIGHT / Gdx.graphics.getHeight());
        float height = (GAME_WORLD_HEIGHT / 100) * percent;
        mSprite.setSize((height / h_ratio) * w_ratio, (height / h_ratio) * h_ratio);


    }

    public void fill(float unit) {
        float w_ratio = mTexture.getWidth() * (GAME_WORLD_WIDTH / Gdx.graphics.getWidth());
        float h_ratio = mTexture.getHeight() * (GAME_WORLD_HEIGHT / Gdx.graphics.getHeight());
        mSprite.setSize((unit / w_ratio) * w_ratio, (unit / w_ratio) * h_ratio);

    }


    public float getRatio() {
        return GAME_WORLD_WIDTH / GAME_WORLD_HEIGHT;
    }

    public void setToCenter() {
        float ws = mSprite.getWidth();
        float hs = mSprite.getHeight();
        mPosition.x = (GAME_WORLD_WIDTH / 2) - (ws / 2);
        mPosition.y = (GAME_WORLD_HEIGHT / 2) - (hs / 2);
        mSprite.setPosition(getX(), getY());
    }



    public void setToHorizontalCenter() {
        float ws = mSprite.getWidth();
        mPosition.x = (GAME_WORLD_WIDTH / 2) - (ws / 2);
        mSprite.setPosition(getX(), getY());
    }

    public void setToVerticalCenter() {
        float hs = mSprite.getHeight();
        mPosition.y = (GAME_WORLD_HEIGHT / 2) - (hs / 2);
        mSprite.setPosition(getX(), getY());
    }

    public float getMarginLeft(OrthographicCamera camera) {
        return  camera.position.x - getX();
    }

    public void freeFall(float dt) {
        mVelocity.add(0, gravity(), 0);
        mVelocity.scl(dt);
        mPosition.add(movement() * dt, mVelocity.y, 0);
        mVelocity.scl(1 / dt);
        setPosition(getX(), getY());
    }
    public void jump(){
        mVelocity.y = jumpStrength();
    }

    public float getX() {
        return mPosition.x;
    }

    public float getRightX() {
        return getX() + getSprite().getWidth();
    }

    public float getY() {
        return mPosition.y;
    }

    public float getTopY() {
        return getY() + getSprite().getHeight();
    }

    public void setPosition(float x, float y) {
        mPosition.x = x;
        mPosition.y = y;
        getSprite().setPosition(getX(), getY());
    }


    protected float gravity() {
        return -500;
    }


    protected float movement() {
        return 0;
    }

    protected float jumpStrength() {
        return 200;
    }
}
