package com.leonkamerlin.flappybird.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * Created by Leon on 22.6.2017..
 */

public class Ground extends BaseSprite {
    public Ground() {
        super(new Sprite(new Texture(Gdx.files.internal("ground.png"))));
    }
}
