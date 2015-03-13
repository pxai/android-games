/**
 * 
 */
package info.pello.games.libgdx.pong;

import java.util.Random;

import com.badlogic.gdx.math.Vector3;

/**
 * Pelota
 * Representa la pelota del juego
 * @author Pello Altadill
 * 
 */
public class Pelota {

	// la velocidad supondrá el número de pixeles recorridos por sg.
	public final float velocidad = 8f;
	private Random random = new Random();
	// En este vector guardaremos la posición actual
	public Vector3 posicion;
	// Con este vector controlaremos la dirección.
	public Vector3 direccion;
	
	/**
	 * Pelota
	 * Constructor
	 * Establecemos su posición y dirección usando vectores
	 */
	public Pelota(){
		// La posición inicial tendrá una altura aleatoria
		posicion = new Vector3(random.nextInt(13) + 1, 9, 0);
		// La dirección la fijará la velocidad.
		direccion = new Vector3(velocidad, velocidad, 0);
	}
	

	
}
