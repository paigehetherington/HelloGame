package com.theironyard;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class HelloGame extends ApplicationAdapter {
	SpriteBatch batch; //batch draws things for us
	Texture img; //where it loads image and stores in memory
	float x, y, xv, yv; //float less accurate, ok for games

	static final float MAX_VELOCITY = 100; //final makes it a constant
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
	}

	@Override
	public void render () { //called every time game wants to render a frame so very often (game loop)
		move();

		Gdx.gl.glClearColor(1, 0, 0, 1); //clears last frame that was rendered and makes new frame
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); // "
		batch.begin(); //prepares for drawing what comes next
		batch.draw(img, x, y); //pass image object and tell where to draw
		batch.end(); //ends drawing
	}

	float decelerate(float velocity) { //broke into separate method
		float deceleration = 0.99f; //interprets as float not double. dampening effect.the closer to 1 the slower the deceleration
		velocity = velocity * deceleration;
		if (Math.abs(velocity) < 1) {
			velocity = 0;

		}
		return velocity;
	}

	void move() {
		if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
			yv = MAX_VELOCITY;
		}
		if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
			yv = MAX_VELOCITY * -1;
		}
		if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
			xv = MAX_VELOCITY;
		}
		if (Gdx.input.isKeyPressed(Input.Keys.LEFT)){
			xv = MAX_VELOCITY * -1;
		}

		y += yv *Gdx.graphics.getDeltaTime(); //delta time - amt of time since last frame ran. yv= # pixels movein 1 second
		x += xv * Gdx.graphics.getDeltaTime();

		yv = decelerate(yv);
		xv = decelerate(xv);


	}

}
