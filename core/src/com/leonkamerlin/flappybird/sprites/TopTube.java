package com.leonkamerlin.flappybird.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

import org.omg.CORBA.PUBLIC_MEMBER;

import java.util.Random;

/**
 * Created by Leon on 6.6.2017..
 */

public class TopTube extends BaseSprite {

    public TopTube() {
        super(new Sprite(new Texture(Gdx.files.internal("toptube.png"))));
        fillHeight(50f);
    }


}
