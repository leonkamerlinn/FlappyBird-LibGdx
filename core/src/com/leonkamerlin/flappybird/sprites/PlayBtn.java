package com.leonkamerlin.flappybird.sprites;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.glutils.GLVersion;

/**
 * Created by Leon on 22.6.2017..
 */

public class PlayBtn extends BaseSprite {
    public PlayBtn() {
        super(new Sprite(new Texture(Gdx.files.internal("playbtn.png"))));
    }

}
