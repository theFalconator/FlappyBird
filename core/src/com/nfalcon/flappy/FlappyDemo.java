package com.nfalcon.flappy;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.nfalcon.flappy.states.GameStateManager;
import com.nfalcon.flappy.states.MenuState;

public class FlappyDemo extends ApplicationAdapter {

	public static final int WIDTH = 480;
	public static final int HEIGHT = 800;

	private GameStateManager gsm;

	SpriteBatch batch;
	Texture img;

	
	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");

		gsm = new GameStateManager();
		gsm.push(new MenuState(gsm));

        Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 1);
	}

	@Override
	public void render () {

		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		gsm.update(Gdx.graphics.getDeltaTime());


		gsm.render(batch);

	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
