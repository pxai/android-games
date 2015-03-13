package info.pello.games.libgdx.pizarra;

import java.util.Random;
import java.util.Vector;

/**
 * World
 * Clase que representa el Mundo del juego
 * Consiste en un conjunto de Alumnos y unos atributos para marcar el estado 
 * del mundo.
 * Además tiene métodos para sacar un Alumno de forma aleatoria
 * Esta clase es usada por el juego libgdx pero ella es totalmente ajena
 * a eso. Por eso se dice que con libgdx se implementa una especie de MVC
 * @author Pello Altadill
 * 
 * 
 *
 */
public class World {
	private Vector<Alumno> alumnos = new Vector<Alumno>();
	// Nombres de los candidatos a salir
	private String[] nombres = {"Arricibita","Moreno","Torres","Carlos","Tejedor","Cobo",
								"Victor","Lana","Del Río","Enrico",
								"Guerrero","Pardo","Castle","Chueca","Berruezo",
								"Raquel","Natalia","Demi","Benjamin"};
	// objeto para sacar números aleatorios
	private Random aleatorio;
	
	// Atributo para guardar el índice sacado
	private int elegido;
	
	// Estado del mundo: inicio, barajando o resultado
	public final static int GAME_INIT_STATUS = 1;
	public final static int SHUFFLE_STATUS = 2;
	public final static int RESULT_STATUS = 3;
	
	// Atributo para guardar el estado actual.
	private int game_status;
	
	/**
	 * constructor
	 */
	public World () {
		inicializar();
	}
	
	/**
	 * inicializar
	 * carga el mundo con los alumnos e inicializa el random
	 */
	private void inicializar () {
		setGameStatus(GAME_INIT_STATUS);
		aleatorio = new Random();
		
		for (String nombre: nombres) 
			alumnos.add(new Alumno(nombre));
		
	}

	/**
	 * sacarAleatorio
	 * Saca un alumnos aleatorio
	 * @return Alumno
	 */
	public Alumno sacarAleatorio () {
		elegido = aleatorio.nextInt(alumnos.size());
		return alumnos.get(elegido);
	}

	/**
	 * @param game_status the game_status to set
	 */
	public void setGameStatus(int game_status) {
		this.game_status = game_status;
	}

	/**
	 * @return the game_status
	 */
	public int getGameStatus() {
		return game_status;
	}

	/**
	 * getElegido
	 * @return índice del elemento elegido
	 */
	public int getIndiceElegido() {
		return elegido;
	}
	
}
