/**
 * 
 */
package info.pello.games.libgdx.kitty;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.TimeUtils;

/**
 * World
 * El mundo de Kitty, está formado por un conjunto de frutas
 * y por supuesto por la petardilla de Kitty
 * @author Pello Altadill
 *
 */
public class World {
	// La personaja
	Kitty kitty;
	// La lluvia de frutas
	ArrayList<Fruit> fruits = new ArrayList<Fruit>();
	
	// Último momento de caída fruta. Puede servir para incrementar dificultad
	long lastDropTime = 0;
	
	// Los puntos que lleva Kitty
	long points = 0;

	/**
	 * constructor, llama a un inicializador
	 */
	public World(){
		initialize();
	}
	
	/**
	 * initialize
	 * Posiciona a Kitty y hace llover una fruta
	 */
	private void initialize () {
		kitty = new Kitty(15f / 2, 0);
		spawnFruit();
	}

	/**
	 * spawnFruit
	 * spawn es algo así como engendrar, generar
	 */
	private void spawnFruit () {
		// creamos una fruta
	    Fruit fruit = new Fruit();
	    // Y la incluímos en la lluvia del mundo
	    fruits.add(fruit);
	    // Guardamos el momento de creación
	    setLastDropTime();
	 }
	
	/**
	 * setLastDropTime
	 * guardamos el momento de la última fruta generada
	 * para uso futuro
	 */
	public void setLastDropTime () {
	    lastDropTime = TimeUtils.nanoTime();		
	}
	
	/**
	 * update
	 * The mother of the lamb, este es el método que se ejecuta
	 * cada vez que se pinta la pantalla en el render del juego.
	 * Controla la lluvia de frutas
	 * @param delta
	 */
	public void update(float delta){
		// Esta variable es pare decidir si generamos nueva fruta o no
		boolean spawnNew = false;
		Fruit fruit = null;
		
		// Vamos a recorrer el array que contiene las frutas
		// y según su posición LAS MARCAMOS como:
		// CATCH: la fruta ha sido pillada
		// FAIL: la fruta ha llegado al suelo
		for (int i = 0;i<fruits.size();i++) {
			fruit = fruits.get(i);

			// Actualizamos la posición de la fruta
			fruit.position.y -=  0.05;
			fruit.direction.y = fruit.VELOCITY;
			
			// Está la fruta en la posición de Kitty? LA HA PILLADO!!
			if (fruit.position.x >= kitty.x && fruit.position.x <= kitty.x + 1
					&& fruit.position.y <1 && fruit.position.y > 0) {
				// Sumamos puntos
				points++;
				// La marcamos como pillada
				fruits.get(i).state = Fruit.CATCH;
				// Y marcamos la variable para que luego se genere otra fruta
				spawnNew = true;
			}

			// La fruta se ha pasado de largo?
			if (fruit.position.y < 0) {
				// La marcamos como fallo
				fruits.get(i).state = Fruit.FAIL;
				// Y marcamos para que luego se genere una fruta
				spawnNew = true;
			}
			
		}
		
		// Si tras revisar las frutas se decide
		// que hay que generar más, se hace.
		if (spawnNew) {
			spawnFruit();
		}
		

	}

}

