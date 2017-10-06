package com.nfalcon.flappy.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

public class Tube {
    public static final int VARIABILITY = 130;
    public static final int TUBE_WIDTH = 52;
    public static final int TUBE_GAP = 100;
    public static final int MIN_OPENING = 120;

    private Texture topTube, bottomTube;
    private Vector2 posTopTube, posBottomTube;
    private Random rnd;

    public Tube(float x) {
        topTube = new Texture("toptube.png");
        bottomTube = new Texture("bottomtube.png");
        rnd = new Random();

        posTopTube = new Vector2(x, rnd.nextInt(VARIABILITY) + TUBE_GAP + MIN_OPENING);
        posBottomTube = new Vector2(x, posTopTube.y - TUBE_GAP - bottomTube.getHeight());
    }

    public void reposition(float x) {
        posTopTube.set(x, rnd.nextInt(VARIABILITY) + TUBE_GAP + MIN_OPENING);
        posBottomTube.set(x, posTopTube.y - TUBE_GAP - bottomTube.getHeight());
    }

    public Texture getTopTube() {
        return topTube;
    }

    public Texture getBottomTube() {
        return bottomTube;
    }

    public Vector2 getPosTopTube() {
        return posTopTube;
    }

    public Vector2 getPosBottomTube() {
        return posBottomTube;
    }
}
