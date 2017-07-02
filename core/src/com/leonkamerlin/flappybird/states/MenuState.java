package com.leonkamerlin.flappybird.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.leonkamerlin.flappybird.sprites.Background;
import com.leonkamerlin.flappybird.sprites.Badlogic;
import com.leonkamerlin.flappybird.sprites.PlayBtn;
import com.leonkamerlin.flappybird.utils.GameCamera;

/**
 * Created by Leon on 6.6.2017..
 */

public class MenuState extends State {
    private final Background mBackground;
    private final GameCamera mGameCamera;
    private PlayBtn mPlayBtn;
    public MenuState(GameStateManager gsm, GameCamera gameCamera) {
        super(gsm);
        mPlayBtn = new PlayBtn();
        mBackground = new Background();
        mGameCamera = gameCamera;


    }

    @Override
    public void handleInput(float dt) {
        if (Gdx.input.justTouched()) {
            dispose();
            mGameStaticManager.set(new PlayState(mGameStaticManager, mGameCamera));
        }

    }

    @Override
    public void update(float dt) {
        handleInput(dt);
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        mBackground.getSprite().draw(sb);
        mPlayBtn.getSprite().draw(sb);
        sb.end();
    }

    @Override
    public void dispose() {
        mBackground.getSprite().getTexture().dispose();
        mPlayBtn.getSprite().getTexture().dispose();
    }



    @Override
    public void resize(int width, int height) {
        mBackground.fillHeight(100f);
        mPlayBtn.fillWidth(30f);
        mPlayBtn.setToCenter();
    }
}
