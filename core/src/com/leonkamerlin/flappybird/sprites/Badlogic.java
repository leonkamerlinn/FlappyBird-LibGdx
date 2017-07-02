package com.leonkamerlin.flappybird.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import static com.leonkamerlin.flappybird.FlappyBird.GAME_WORLD_HEIGHT;
import static com.leonkamerlin.flappybird.FlappyBird.GAME_WORLD_WIDTH;


/**
 * Created by Leon on 23.6.2017..
 */

public class Badlogic extends BaseSprite {
    public Badlogic() {
        super(new Sprite(new Texture(Gdx.files.internal("badlogic.jpg"))));
    }






}
