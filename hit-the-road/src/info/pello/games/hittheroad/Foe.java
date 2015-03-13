package info.pello.games.hittheroad;

import com.badlogic.gdx.math.Vector3;

public class Foe extends Car {

	/**
	 * 
	 */
	public Foe() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param x
	 * @param y
	 */
	public Foe(float x, float y) {
		position = new Vector3(x, y, 0);
		direction = new Vector3(VELOCITY, 0, 0);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void update(float delta) {
        stateTime += delta;
		this.position.add(-this.direction.x * delta, this.direction.y * delta, 0);
	}

}
