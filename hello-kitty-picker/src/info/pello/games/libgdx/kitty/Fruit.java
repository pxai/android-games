/**
 * 
 */
package info.pello.games.libgdx.kitty;

import com.badlogic.gdx.math.Vector3;
import java.util.Random;
/**
 * Fruit
 * Clase que representa una fruta de las que llueven en el mundo
 * @author pello Altadill
 *
 */
public class Fruit {
	// Velocidad de caída de las frutas
	public final float VELOCITY = 3.5f;
	// Un atributo para generar posiciones aleatorias
	private Random random = new Random();
	// Vectores de posición y dirección. 
	public Vector3 position;
	public Vector3 direction;

	// Nombre de la fruta
	public String fruitName;
	// Estado de la fruta, nos sirve para controlarla desde el mundo
	public int state = FALLING;
	public final static int FALLING = 0;
	public final static int CATCH = 1;
	public final static int FAIL = 2;
	
	// Nombres de frutas/chuches que van a caer
	private String[] fruitNames = {"apple","banana","cherries","grapes","icecream","lollipop","peach","strawberry"};

	/**
	 * Fruit
	 * constructor por defecto
	 * la fruta va a caer desde una posición x aleatoria
	 */
	public Fruit(){
		position = new Vector3(random.nextInt(13) + 1, 9, 0);
		direction = new Vector3(VELOCITY, VELOCITY, 0);
		fruitName = fruitNames[random.nextInt(fruitNames.length)];
	}
	

	/**
	 * Fruit
	 * constructor en el que damos una posicón concreta
	 * a la fruta. Puede ser útil si se juega a otro nivel difícil
	 * para hacer que la fruta aparezca donde queramos.
	 * @param x
	 * @param y
	 */
	public Fruit(float x, float y){
		position = new Vector3(x, y, 0);
		direction = new Vector3(VELOCITY, VELOCITY, 0);
	}
	

	
}
