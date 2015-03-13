package info.pello.games.arkapong;


import java.util.Random;

import com.badlogic.gdx.math.Vector3;

/**
 * represents a Ball
 * @author Pello Altadill
 * @greetz Kitt
 */
public class Ball {
	private float velocity = DEFAULT_VELOCITY;
	private Random random = new Random();
	private Vector3 position;
	private Vector3 direction;
    private float stateTime;
    public final static float DEFAULT_VELOCITY = 3f;
    

	/**
	 * 
	 */	
	public Ball (float speed){
		velocity = speed;
		position = new Vector3(7, 5, 0);
		direction = new Vector3((random.nextInt(2)==0)?-1:1, (random.nextInt(2)==0)?-1:1, 0);
        this.stateTime = (float)Math.random();
	}
	

	public Ball (float x, float y, float speed){
		velocity = speed;
		position = new Vector3(x, y, 0);
		direction = new Vector3(random.nextInt(1)-1, random.nextInt(1)-1, 0);
        this.stateTime = (float)Math.random();
	}

	/**
	 * update position based adding direction to
	 * @param delta
	 */
	public void update(float delta){
        stateTime += delta;
		this.position.add(this.direction.x * velocity * delta, this.direction.y * velocity * delta, 0);
	}

	/**
	 * change direction
	 * @param x
	 * @param y
	 */
	public void changeDirection(float x, float y) {
		if (x<0) {
			this.direction.x = -velocity;			
		} else if (x > 0) {
			this.direction.x = velocity;						
		} else {
			this.direction.x = 0;			
		}

		if (y < 0) {
			this.direction.y = -velocity;			
		} else if (y > 0) {
			this.direction.y = velocity;						
		} else {
			this.direction.y = 0;			
		}

	}
	
	


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Ball [VELOCITY=" + velocity + ", random=" + random
				+ ", position=" + position + ", direction=" + direction + "]";
	}


	/**
	 * @return the velocity
	 */
	public float getVelocity() {
		return velocity;
	}


	/**
	 * @param velocity the velocity to set
	 */
	public void setVelocity(float velocity) {
		this.velocity = velocity;
	}


	/**
	 * @return the random
	 */
	public Random getRandom() {
		return random;
	}


	/**
	 * @param random the random to set
	 */
	public void setRandom(Random random) {
		this.random = random;
	}


	/**
	 * @return the position
	 */
	public Vector3 getPosition() {
		return position;
	}


	/**
	 * @param position the position to set
	 */
	public void setPosition(Vector3 position) {
		this.position = position;
	}


	/**
	 * @return the direction
	 */
	public Vector3 getDirection() {
		return direction;
	}


	/**
	 * @param direction the direction to set
	 */
	public void setDirection(Vector3 direction) {
		this.direction = direction;
	}


	/**
	 * @return the stateTime
	 */
	public float getStateTime() {
		return stateTime;
	}


	/**
	 * @param stateTime the stateTime to set
	 */
	public void setStateTime(float stateTime) {
		this.stateTime = stateTime;
	}

	
}
