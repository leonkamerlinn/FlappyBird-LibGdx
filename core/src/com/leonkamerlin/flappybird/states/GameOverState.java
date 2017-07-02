package com.leonkamerlin.flappybird.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.leonkamerlin.flappybird.sprites.Background;
import com.leonkamerlin.flappybird.sprites.Bird;
import com.leonkamerlin.flappybird.sprites.BottomTube;
import com.leonkamerlin.flappybird.sprites.GameOver;
import com.leonkamerlin.flappybird.sprites.Ground;
import com.leonkamerlin.flappybird.sprites.TopTube;
import com.leonkamerlin.flappybird.sprites.Tube;
import com.leonkamerlin.flappybird.utils.GameCamera;

/**
 * Created by Leon on 2.7.2017..
 */

public class GameOverState extends State {
    private final Background[] mBackgrounds;
    private final GameCamera mGameCamera;
    private final GameOver mGameOver;


    private Ground[] mGrounds;
    private Tube[] mTubes;


    public GameOverState(GameStateManager gsm, GameCamera gameCamera) {
        super(gsm);

        mBackgrounds = new Background[] {new Background(), new Background()};

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

        mGameOver = new GameOver();


    }

    @Override
    public void handleInput(float dt) {
        if (Gdx.input.justTouched()) {

            dispose();
            mGameStaticManager.set(new PlayState(mGameStaticManager, mGameCamera));


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

        mGameOver.getSprite().draw(sb);




        sb.end();

    }

    @Override
    public void update(float dt) {
        handleInput(dt);

        prepositionBackground();
        prepositionGrounds();
        prepositionTubes();



    }



    @Override
    public void dispose() {
        for(Background background: mBackgrounds) {
            background.getSprite().getTexture().dispose();
        }

        for(Tube tube: mTubes) {
            tube.getTopTube().getSprite().getTexture().dispose();
            tube.getBottomTube().getSprite().getTexture().dispose();
        }

        for (Ground ground: mGrounds) {
            ground.getSprite().getTexture().dispose();
        }

        mGameOver.getSprite().getTexture().dispose();


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


        mGrounds[1].setPosition(mGrounds[0].getRightX(), mGrounds[1].getY());
        mGrounds[2].setPosition(mGrounds[1].getRightX(), mGrounds[1].getY());


        mTubes[0].setX(mGameCamera.getRightX());
        mTubes[1].setX(mTubes[0].getRightX() + 150);
        mTubes[2].setX(mTubes[1].getRightX() + 150);
        mTubes[3].setX(mTubes[2].getRightX() + 150);
        mTubes[4].setX(mTubes[3].getRightX() + 150);
        mTubes[5].setX(mTubes[4].getRightX() + 150);


        mGameOver.fillWidth(80f);

        mGameOver.getSprite().setPosition(mGameOver.getSprite().getWidth() / 2, 677 / 2);






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
