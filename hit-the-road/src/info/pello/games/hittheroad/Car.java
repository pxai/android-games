package info.pello.games.hittheroad;


import java.util.Random;

import com.badlogic.gdx.math.Vector3;

/**
 * represents a space ship
 * @author Pello Altadill
 * @greetz Han Solo
 */
public abstract class Car {
	public final float VELOCITY = 5f;
	private Random random = new Random();
	public Vector3 position;
	public Vector3 direction;
    public float stateTime;
    
	public int state = STOPPED;
	public final static int MOVING = 0;
	public final static int STOPPED = 1;

	/**
	 * 
	 */
	public Car (){
		position = new Vector3(random.nextInt(13) + 1, 9, 0);
		direction = new Vector3(VELOCITY, VELOCITY, 0);
        this.stateTime = (float)Math.random();
	}
	

	public Car (float x, float y){
		position = new Vector3(x, y, 0);
		direction = new Vector3(0, 0, 0);
        this.stateTime = (float)Math.random();
	}

	/**
	 * update position based adding direction to
	 * @param delta
	 */
	public void update(float delta){
        stateTime += delta;
		this.position.add(this.direction.x * delta, this.direction.y * delta, 0);
	}

	/**
	 * change direction
	 * @param x
	 * @param y
	 */
	public void changeDirection(float x, float y) {
		if (x<0) {
			this.direction.x = -VELOCITY;			
		} else if (x > 0) {
			this.direction.x = VELOCITY;						
		} else {
			this.direction.x = 0;			
		}

		if (y < 0) {
			this.direction.y = -VELOCITY;			
		} else if (y > 0) {
			this.direction.y = VELOCITY;						
		} else {
			this.direction.y = 0;			
		}

	}
	
	
	/**
	 * setPosition
	 * 
	 * @param x
	 * @param y
	 */
	private void setPosition(float x, float y){
		if(x < 1f)  this.position.x = 1f; 
		if(x > 9f)  this.position.x = 9f; 
		if(y < 1f)  this.position.y = 1f;
		if(y > 13f) this.position.y = 13f;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Ship [VELOCITY=" + VELOCITY + ", random=" + random
				+ ", position=" + position + ", direction=" + direction
				+ ", state=" + state + "]";
	}

	
}
