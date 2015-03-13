package info.pello.games.hittheroad;

import com.badlogic.gdx.Gdx;

public class Player extends Car {

	private int points;
	
	/**
	 * 
	 */
	public Player() {
		super();
		points = 0;
	}

	/**
	 * @param x
	 * @param y
	 */
	public Player(float x, float y) {
		super(x, y);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void update(float delta) {
        stateTime += delta;
		this.position.add(this.direction.x * delta, this.direction.y * delta, 0);
	}
	
	/**
	 * just increments the score
	 */
	public void goForward () {
		points++;
	}
	
	/**
	 * getter for points
	 * @return
	 */
	public int getPoints() {
		return points;
	}

	/**
	 * add points to player score
	 * @param i
	 */
	public void addPoints(int i) {
		points += i;
	}
	
}
