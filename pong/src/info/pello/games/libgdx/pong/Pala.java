/**
 * 
 */
package info.pello.games.libgdx.pong;

import java.util.Random;

import com.badlogic.gdx.math.Vector3;

/**
 * Pala
 * Representa la pala del juego
 * @author Pello Altadill
 * 
 */
public class Pala {
	public final float velocidad = 3.5f;
	public Vector3 posicion;
	public Vector3 direccion;
	int x = 0;
	int y = 0;
	
	/**
	 * Pala
	 * constructor de nuestr pala.
	 * En él iniciamos la posicón y la dirección.
	 * @param x
	 * @param y
	 */
	public Pala(int x, int y){
		this.x = x;
		this.y = y;
		posicion = new Vector3(this.x, this.y, 0);
		direccion = new Vector3(velocidad, velocidad, 0);
	}
	
	/**
	 * mover
	 * mueve la posición de la pala. Se controla para que no se
	 * salga de los márgenes.
	 * @param x
	 */
	public void mover (float x) {
		if(x < 0) x = 0;
		if(x > 7) x = 7;
		posicion.x = x;
	}
	
}
