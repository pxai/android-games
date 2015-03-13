package info.pello.games.hittheroad;

import java.util.Random;

import com.badlogic.gdx.math.Vector3;

public class Fire extends Car {
	public final float VELOCITY = 60f;
	private Random random = new Random();
	public Vector3 position;
	public Vector3 direction;

	public Fire(){
		position = new Vector3(random.nextInt(13) + 1, 9, 0);
		direction = new Vector3(VELOCITY, VELOCITY, 0);
	}
	

	public Fire(float x, float y){
		position = new Vector3(x, y, 0);
		direction = new Vector3(VELOCITY, 0, 0);
	}
	
	/**
	 * update position based adding direction to
	 * @param delta
	 */
	public void update(float delta){
		this.position.add(this.direction.x * delta, 0, 0);
	}
	
}
