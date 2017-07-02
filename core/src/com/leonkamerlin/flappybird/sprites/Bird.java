package com.leonkamerlin.flappybird.sprites;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector3;
import com.sun.org.apache.bcel.internal.generic.PUSH;

/**
 * Created by Leon on 6.6.2017..
 */

public class Bird extends BaseSprite {

    private boolean collidedWithGround = false;
    private boolean collidedWithTubes = false;
    private int mTubeIndex = 0;
    private int mTubeCount = 0;
    private Tube mLastCollidingTube = null;

    public Bird() {
        super(new Sprite(new Texture(Gdx.files.internal("bird.png"))));
    }

    @Override
    protected float gravity() {
        return -20f;
    }

    @Override
    protected float movement() {
        return collidedWithGround || collidedWithTubes ? 0 : 100;
    }

    @Override
    protected float jumpStrength() {
        return collidedWithGround || collidedWithTubes ? 0 : 300;
    }

    public boolean isCollidedWithGround(Ground ground) {
        collidedWithGround = getY() <= ground.getTopY();
        return collidedWithGround;
    }

    public boolean isCollidedWithTubes(Tube[] tubes) {
        for (Tube tube: tubes) {
            if (tube.getX() <= getRightX() && getX() <= tube.getRightX()) {

                if (getTopY() >= tube.getTopTube().getY()) {
                    // collided with top tube
                    collidedWithTubes = true;
                    mLastCollidingTube = tube;
                    mLastCollidingTube.getTopTube().setCollided(true);
                    break;
                }

                if(getY() <= tube.getBottomTube().getTopY()) {
                    // collided with bottom tube
                    collidedWithTubes = true;
                    mLastCollidingTube = tube;
                    mLastCollidingTube.getBottomTube().setCollided(true);
                    break;
                }

            }

        }

        return collidedWithTubes;
    }

    public boolean isDied() {
        return collidedWithGround || collidedWithTubes;
    }

    public int tubeCount(Tube[] tubes) {
        for (Tube tube: tubes) {
            if(getX() >= tube.getRightX()) {

                if (tube.equals(tubes[mTubeIndex]) && !isDied()) {
                    mTubeCount++;
                    mTubeIndex++;
                    if (mTubeIndex == 6) {
                        mTubeIndex = 0;
                    }
                    break;
                }


            }

        }

        return mTubeCount;

    }

    public Tube getLastCollidingTube() {
        return mLastCollidingTube;
    }
}
