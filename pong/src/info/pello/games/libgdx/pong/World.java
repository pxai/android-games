/**
 * 
 */
package info.pello.games.libgdx.pong;


/**
 * World
 * @author Pello Xabier
 * Clase que representa el mundo del juego. Un mundo compuesto
 * por paredes laterales, una pala en la parte de abajo y una pared
 * en la parte de arriba. Y una pelotilla que va y viene.
 */
public class World {

	// Aquí los elementos de nuestro mundo:
	// una pelota
	private Pelota pelota;
	// y una pala
	private Pala pala;
	
	// Un atributo para controlar el fin del juego
	private boolean gameOver = false;
	
	/**
	 * constructor 
	 * se incializa en otro método
	 */
	public World(){
		initialize();
	}
	
	/**
	 * inicializamos el mundo, creamos una pelota y una pala
	 */
	private void initialize () {
		pelota = new Pelota();
		pala = new Pala(4,0);
	}
	
	/**
	 * update
	 * updates world status
	 * @param delta
	 */
	public void update (float delta) {
		
		// Altermos la posición de la pelota. 
		// La posición la marca el vector así que no tnemos más que añadir
		// a esa posición los valores de dirección. La dirección variará en
		// función de lo que pase con la pelota
		pelota.posicion.add(pelota.direccion.x*delta, pelota.direccion.y*delta, 0);

		// Toca la pala? ¡para arriba!
		if (pelota.posicion.x > pala.posicion.x && pelota.posicion.x <= pala.posicion.x + 2 &&
				pelota.posicion.y < 0.5) {
		
			// Alteramos la dirección
			pelota.direccion.y = pelota.velocidad;
			System.out.println("Palazo : pel(" + pelota.posicion.x +","+pelota.posicion.y +") pala (" + pala.posicion.x +","+pala.posicion.y +")");

		} 
		
		// Toca el techo? ¡para abajo!
		if (pelota.posicion.y > 14) {
			// Variamos el vector de dirección invirtendo la velocidad
			pelota.direccion.y = -pelota.velocidad;			
			System.out.println("Choque techo pel(" + pelota.posicion.x +","+pelota.posicion.y +") pala (" + pala.posicion.x +","+pala.posicion.y +")");


		}
		
		// Tocamos la pared izquierda?
		if (pelota.posicion.x < 0) {
			// Variamos el vector de dirección invirtendo la velocidad
			pelota.direccion.x = pelota.velocidad;
			System.out.println("Choque izq pel(" + pelota.posicion.x +","+pelota.posicion.y +") pala (" + pala.posicion.x +","+pala.posicion.y +")");


		}

		// Tocamos la pared derecha?
		if (pelota.posicion.x > 9) {
			pelota.direccion.x = -pelota.velocidad;
			System.out.println("Choque dcha pel(" + pelota.posicion.x +","+pelota.posicion.y +") pala (" + pala.posicion.x +","+pala.posicion.y +")");


		}
		
		// La pelota cae por abajo!!
		if (pelota.posicion.y < 0) {
			System.out.println("Cayó pel(" + pelota.posicion.x +","+pelota.posicion.y +") pala (" + pala.posicion.x +","+pala.posicion.y +")");
			// establecemos el final de la partida
			setGameOver(true);
		}


	}

	/**
	 * @return the pelota
	 */
	public Pelota getPelota() {
		return pelota;
	}

	/**
	 * @param pelota the pelota to set
	 */
	public void setPelota(Pelota pelota) {
		this.pelota = pelota;
	}

	/**
	 * @return the pala
	 */
	public Pala getPala() {
		return pala;
	}

	/**
	 * @param pala the pala to set
	 */
	public void setPala(Pala pala) {
		this.pala = pala;
	}

	public boolean isGameOver() {
		return gameOver;
	}

	public void setGameOver(boolean gameOver) {
		this.gameOver = gameOver;
	}
}
