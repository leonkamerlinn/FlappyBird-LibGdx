package com.leonkamerlin.flappybird.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;


/**
 * Created by Leon on 23.6.2017..
 */

public class GameOver extends BaseSprite {
    public GameOver() {
        super(new Sprite(new Texture(Gdx.files.internal("gameover.png"))));
    }






}
