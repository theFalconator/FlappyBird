package com.nfalcon.flappy.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.nfalcon.flappy.FlappyDemo;
import com.nfalcon.flappy.sprites.Bird;
import com.nfalcon.flappy.sprites.Tube;

public class PlayState extends State {
    private static final int TUBE_SPACING = 125;
    public static final int TUBE_COUNT = 4;

    private Bird bird;
    private Tube tube;

    private Texture bg;

    private Array<Tube> tubes;

    public PlayState(GameStateManager gsm) {
        super(gsm);
        bird = new Bird(50,250);

        tubes = new Array<>();

        for(int i = 0; i <= TUBE_COUNT; i++) {
            tubes.add(new Tube(i*TUBE_SPACING + 2*Tube.TUBE_WIDTH));
        }

        bg = new Texture("bg.png");
        cam.setToOrtho(false, FlappyDemo.WIDTH/2, FlappyDemo.HEIGHT/2);
    }

    @Override
    public void dispose() {
        bg.dispose();
        bird.dispose();
        for(Tube t : tubes) { t.dispose(); }
    }

    @Override
    protected void handleInput() {
        if(Gdx.input.justTouched()) {
            bird.jump();
        }

    }

    @Override
    public void update(float dt) {
        handleInput();
        bird.update(dt);
        cam.position.x = bird.getPosition().x + 80;

        for(Tube t : tubes) {
            if(t.collides(bird.getBounds())) {
                gsm.set(new PlayState(gsm));
                break;
            }
            if(cam.position.x - (cam.viewportWidth / 2) > t.getPosTopTube().x + t.getTopTube().getWidth()) {
                t.reposition(t.getPosTopTube().x + ((Tube.TUBE_WIDTH + TUBE_SPACING) * TUBE_COUNT));
            }
        }

        cam.update();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();

        sb.draw(bg, cam.position.x - (cam.viewportWidth / 2), 0);

        sb.draw(bird.getTexture(), bird.getPosition().x, bird.getPosition().y);

        for(Tube t: tubes) {
            sb.draw(t.getTopTube(), t.getPosTopTube().x, t.getPosTopTube().y);
            sb.draw(t.getBottomTube(), t.getPosBottomTube().x, t.getPosBottomTube().y);
        }

        sb.end();
    }
}
