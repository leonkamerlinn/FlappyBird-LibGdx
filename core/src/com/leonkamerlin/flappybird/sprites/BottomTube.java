package com.leonkamerlin.flappybird.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * Created by Leon on 6.6.2017..
 */

public class BottomTube extends BaseSprite {

    public BottomTube() {
        super(new Sprite(new Texture(Gdx.files.internal("bottomtube.png"))));
        fillHeight(50f);
    }


}
