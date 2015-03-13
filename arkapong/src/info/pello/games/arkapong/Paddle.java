/**
 * 
 */
package info.pello.games.arkapong;

import java.util.Random;

import com.badlogic.gdx.math.Vector3;

/**
 * @author Pelo Altadill
 * @greetz Oscar Intsausti
 */
public class Paddle {
	private float velocity = 40f;
	private Random random = new Random();
	private Vector3 position;
	private Vector3 prevPosition;
	private Vector3 direction;
	private boolean active;
    private float stateTime;
    private float paddleLength = DEFAULT_PADDLE_LENGTH;
    public static final float DEFAULT_PADDLE_LENGTH = 2f;

	/**
	 * default constructor
	 */	
	public Paddle (){
		active = true;
		position = new Vector3(random.nextInt(13) + 1, 9, 0);
		prevPosition = new Vector3(position.x,position.y,0);
		direction = new Vector3(velocity, velocity, 0);
        this.stateTime = (float)Math.random();
        paddleLength = 2f;

	}
	
	/**
	 * Paddle constructor
	 * @param x
	 * @param y
	 */
	public Paddle (float x, float y){
		active = true;
		position = new Vector3(x, y, 0);
		direction = new Vector3(0, 0, 0);
		prevPosition = new Vector3(position.x,position.y,0);
        this.stateTime = (float)Math.random();

	}

    /**
     * mover
     * mueve la posición de la pala. Se controla para que no se
     * salga de los márgenes.
     * @param x
     */
    public void move (float y) {
    		if(y < 0) y = 0;
    		if(y > 10) y = 7;
           	position.y = y;
    }
    
    /**
     * savePrevPosition
     */
    public void savePrevPosition () {
    	prevPosition.x = position.x;
    	prevPosition.y = position.y;
    }
    
    /**
     * getPrevDirection
     * @return
     */
    public int getPrevDirection (float defaultDirection) {
    	if (prevPosition.y > position.y) {
    		return 1;
    	} else if (prevPosition.y < position.y) {
    		return -1;
    	} else {
    		return (int)defaultDirection;
    	}
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

	/**
	 * @return the paddleLength
	 */
	public float getPaddleLength() {
		return paddleLength;
	}

	/**
	 * @param paddleLength the paddleLength to set
	 */
	public void setPaddleLength(float paddleLength) {
		this.paddleLength = paddleLength;
	}
	
	/**
	 * @param paddleLength the paddleLength to set
	 */
	public void setDefaultPaddleLength() {
		this.paddleLength = DEFAULT_PADDLE_LENGTH;
	}

	/**
	 * @return the active
	 */
	public boolean isActive() {
		return active;
	}

	/**
	 * @param active the active to set
	 */
	public void setActive(boolean active) {
		this.active = active;
	}


}
