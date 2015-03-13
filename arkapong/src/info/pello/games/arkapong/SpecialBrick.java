/**
 * 
 */
package info.pello.games.arkapong;

import java.util.Random;

import com.badlogic.gdx.math.Vector3;

/**
 * @author luser
 *
 */
public class SpecialBrick {
	private int brickType;
	private Vector3 position;

	/**
	 * constructor
	 * @param brickType
	 */
	public SpecialBrick (int brickType) {
		this.brickType = brickType;
	}

	public int getBrickType() {
		return brickType;
	}

	public void setBrickType(int brickType) {
		this.brickType = brickType;
	}

	public Vector3 getPosition() {
		return position;
	}

	public void setPosition(Vector3 position) {
		this.position = position;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SpecialBrick [brickType=" + brickType + ", position="
				+ position + "]";
	}
	
	
}
