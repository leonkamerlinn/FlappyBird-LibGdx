package com.leonkamerlin.flappybird.sprites;

import com.badlogic.gdx.Gdx;
import com.sun.org.apache.bcel.internal.generic.PUSH;
import com.sun.org.apache.regexp.internal.RE;

import java.util.Random;


/**
 * Created by Leon on 24.6.2017..
 */

public class Tube {
    private final int LOWEST_OPENING = 100;
    private final BottomTube mBottomTube;
    private final TopTube mTopTube;
    private final Ground mGround;

    public Tube(TopTube topTube, BottomTube bottomTube, Ground ground) {
        mTopTube = topTube;
        mBottomTube = bottomTube;
        mGround = ground;
        getTopTube().setPosition(getTopTube().getX(), getBottomTube().getTopY() + LOWEST_OPENING);
        setRandomY();
    }

    public TopTube getTopTube() {
        return mTopTube;
    }

    public BottomTube getBottomTube() {
        return mBottomTube;
    }

    public void setX(float x) {
        getBottomTube().setPosition(x, getBottomTube().getY());
        getTopTube().setPosition(x, getTopTube().getY());
    }

    public void setY(float y) {
        getBottomTube().setPosition(getBottomTube().getX(), y);
        getTopTube().setPosition(getTopTube().getX(), getBottomTube().getTopY() + LOWEST_OPENING);
    }

    public float getRightX() {
        return getTopTube().getRightX();
    }

    public float getX() {
        return getTopTube().getX();
    }

    public void setRandomY() {
        Random r = new Random();
        int Low = -50;
        int High = (int) mGround.getTopY();
        int Result = r.nextInt(High-Low) + Low;
        setY(Result);
    }

}
