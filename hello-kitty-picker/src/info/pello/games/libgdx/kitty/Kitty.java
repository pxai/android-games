/**
 * 
 */
package info.pello.games.libgdx.kitty;

import com.badlogic.gdx.math.Vector3;

/**
 * Kitty
 * nuestro personaje del juego
 * @author Pello Altadill
 *
 */
public class Kitty {

		
	// Usaremos también x e y
	public float x;
	public float y;

	/**
	 * Kitty
	 * Constructor para posicionar a Kitty en un lugar concreto
	 * @param x
	 * @param y
	 */
	public Kitty(float x, float y){
		this.x = x;
		this.y = 0;
	}
	
	/**
	 * left
	 * nos movemos a la izquierda, llama a setPos y este
	 * controla si se puede o no
	 */
	public void left () {
		setPos(--x);
	}

	/**
	 * right
	 * nos movemos a la derecha, llama a setPos y este
	 * controla si se puede o no
	 */
	public void right () {
		setPos(++x);
	}
	
	/**
	 * setPos
	 * mueve a la nueva posición de Kitty,
	 * controlando que no se sale de los margenes de pantalla
	 * @param x nueva posición
	 */
	private void setPos(float x){
		if(x < 1f)  this.x = 1f; 
		if(x > 13f) this.x = 13f; 

	}
}
