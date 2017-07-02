package com.leonkamerlin.flappybird.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.leonkamerlin.flappybird.sprites.Background;
import com.leonkamerlin.flappybird.sprites.Bird;
import com.leonkamerlin.flappybird.sprites.BottomTube;
import com.leonkamerlin.flappybird.sprites.Ground;
import com.leonkamerlin.flappybird.sprites.TopTube;
import com.leonkamerlin.flappybird.sprites.Tube;
import com.leonkamerlin.flappybird.utils.GameCamera;

/**
 * Created by Leon on 6.6.2017..
 */

public class PlayState extends State {
    private final Background[] mBackgrounds;
    private final GameCamera mGameCamera;
    private final Sound mJumpSound;
    private final BitmapFont mScoreCount;
    private Bird mBird;
    private Ground[] mGrounds;
    private Tube[] mTubes;


    public PlayState(GameStateManager gsm, GameCamera gameCamera) {
        super(gsm);
        mBird = new Bird();
        mBackgrounds = new Background[] {new Background(), new Background()};
        mBird.getSprite().setPosition(mBird.getX(), mBird.getY());
        mGrounds = new Ground[]{new Ground(), new Ground(), new Ground()};
        mGameCamera = gameCamera;
        mTubes = new Tube[] {
                new Tube(new TopTube(), new BottomTube(), mGrounds[0]),
                new Tube(new TopTube(), new BottomTube(), mGrounds[0]),
                new Tube(new TopTube(), new BottomTube(), mGrounds[0]),
                new Tube(new TopTube(), new BottomTube(), mGrounds[0]),
                new Tube(new TopTube(), new BottomTube(), mGrounds[0]),
                new Tube(new TopTube(), new BottomTube(), mGrounds[0]),
        };

        mJumpSound = Gdx.audio.newSound(Gdx.files.internal("sfx_wing.ogg"));

        mScoreCount = new BitmapFont();
        mScoreCount.getData().scale(2f);
    }

    @Override
    public void handleInput(float dt) {
        if (Gdx.input.justTouched()) {
            //mGameStaticManager.set(new MenuState(mGameStaticManager));
            //dispose();
            mBird.jump();
            if (!mBird.isDied()) {
                mJumpSound.play();
            }

        }
    }


    @Override
    public void render(SpriteBatch sb) {

        sb.begin();
        for (Background background: mBackgrounds) {
            background.getSprite().draw(sb);
        }

        for (Tube tube: mTubes) {
            tube.getTopTube().getSprite().draw(sb);
            tube.getBottomTube().getSprite().draw(sb);
        }

        for (Ground ground: mGrounds) {
            ground.getSprite().draw(sb);
        }

        System.out.println(mBird.tubeCount(mTubes));


        mBird.getSprite().draw(sb);
        mScoreCount.draw(sb, String.valueOf(mBird.tubeCount(mTubes)), mGameCamera.getX() + 10, 650);
        sb.end();

    }

    @Override
    public void update(float dt) {
        handleInput(dt);
        mBird.freeFall(dt);
        mGameCamera.getCamera().position.set(mBird.getX() + 80, mGameCamera.getCamera().viewportHeight / 2, 0);
        prepositionBackground();
        prepositionGrounds();
        prepositionTubes();



        if (mBird.isCollidedWithGround(mGrounds[0])) {
            mBird.setPosition(mBird.getX(), mGrounds[0].getTopY());
        }
        if(mBird.isCollidedWithTubes(mTubes)) {
            Tube collidedTube = mBird.getLastCollidingTube();
            if (collidedTube.getTopTube().isCollided()) {
                mBird.setPosition(mBird.getX(), mBird.getY());
            }

        }


    }



    @Override
    public void dispose() {
        mBackgrounds[1].getSprite().getTexture().dispose();
        mBird.getSprite().getTexture().dispose();
    }


    @Override
    public void resize(int width, int height) {
        for (Background background: mBackgrounds) {
            background.fillHeight(100f);
        }
        for(Ground ground: mGrounds) {
            ground.fillHeight(20f);
        }


        mBackgrounds[1].setPosition(mBackgrounds[0].getRightX(), mBackgrounds[1].getY());

        mBird.fill(50);
        mBird.setToCenter();
        mGrounds[1].setPosition(mGrounds[0].getRightX(), mGrounds[1].getY());
        mGrounds[2].setPosition(mGrounds[1].getRightX(), mGrounds[1].getY());


        mTubes[0].setX(mGameCamera.getRightX());
        mTubes[1].setX(mTubes[0].getRightX() + 150);
        mTubes[2].setX(mTubes[1].getRightX() + 150);
        mTubes[3].setX(mTubes[2].getRightX() + 150);
        mTubes[4].setX(mTubes[3].getRightX() + 150);
        mTubes[5].setX(mTubes[4].getRightX() + 150);





    }

    private void prepositionBackground() {
        mBackgrounds[0].setPosition(mGameCamera.getX() - 20, mBackgrounds[0].getY());
        mBackgrounds[1].setPosition(mBackgrounds[0].getRightX(), mBackgrounds[1].getY());
    }

    private void prepositionGrounds() {
        float cameraX = mGameCamera.getX();
        if (cameraX > mGrounds[0].getRightX()) {
            mGrounds[0].setPosition(mGrounds[2].getRightX(), mGrounds[0].getY());
        } else if(cameraX > mGrounds[1].getRightX()) {
            mGrounds[1].setPosition(mGrounds[0].getRightX(), mGrounds[1].getY());
        } else if (cameraX > mGrounds[2].getRightX()) {
            mGrounds[2].setPosition(mGrounds[1].getRightX(), mGrounds[2].getY());
        }
    }

    private void prepositionTubes() {
        float cameraX = mGameCamera.getX();
        if (cameraX > mTubes[2].getRightX()) {

            mTubes[0].setX(mTubes[5].getRightX() + 150); mTubes[0].setRandomY();
            mTubes[1].setX(mTubes[0].getRightX() + 150); mTubes[1].setRandomY();
            mTubes[2].setX(mTubes[1].getRightX() + 150); mTubes[2].setRandomY();

        } else if(cameraX > mTubes[5].getRightX()) {

            mTubes[3].setX(mTubes[2].getRightX() + 150); mTubes[3].setRandomY();
            mTubes[4].setX(mTubes[3].getRightX() + 150); mTubes[4].setRandomY();
            mTubes[5].setX(mTubes[4].getRightX() + 150); mTubes[5].setRandomY();

        }
    }
}
