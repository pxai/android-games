package info.pello.games.libgdx.pizarra;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * PizarraGame
 * Minijuego libgdx para sacar gente a la pizarra
 * @author Pello Altadill
 *
 */
public class PizarraGame implements ApplicationListener {

	// el SpriteBatch para agrupar las ordenes de pintado
	private SpriteBatch batch;
	// Una textura para la presentación
	private Texture texturaFondo;
	// Una textura para mostrar personajes
	private Texture texturaPersonaje;
	// Fuente para sacar texto
	private BitmapFont fuente;

	private float anchuraTexto;

	// World, la clase que representa el "mundo" del juego
	// En este caso es un mundo que contiene un conjunto de alumnos
	private World mundo;
	// La clase alumno, los protagonistas del juego
	private Alumno alumno = null;
	// Atributo para guardar el índice seleccionado aleatoriamente
	private int elegido = 1;
	private int contador = 0;
	int posx = 0, posy = 0;
	
	// Música de fondo
	private Music fondo;
	// Música de sorteo
	private Music sorteo;
	// Un efecto de sonido
	private Sound haha;
	
	/**
	 * create
	 * se invoca al crear el juego por primera vez, es donde
	 * se suelen meter las inicializaciones, se cargan texturas, etc..
	 */
	@Override
	public void create() {
		// Iniciamos la instancia del mundo
		mundo = new World();

		// Creamos una instancia de SpriteBatch para poder pintar cosas
		batch = new SpriteBatch();
		
		// Cargamos la textura
		texturaFondo = new Texture(Gdx.files.internal("data/pizarrasplash.png"));

		// Cargamos un sonido que usaremos en algún momento.
		haha = Gdx.audio.newSound(Gdx.files.internal("data/haha.ogg"));

		// Cargamos la música de sorteo
		sorteo = Gdx.audio.newMusic(Gdx.files.internal("data/shuffle.mp3"));
	}


	/**
	 * render
	 * método que el juego invoca una y otra vez.  Es el encargado de pintar
	 * la pantalla con las texturas, sprites, textos, etc. según es estado del juego
	 * en ese momento.
	 */
	@Override
	public void render() {

		// Creamos un fuente predeterminada
		fuente = new BitmapFont();
		// le ponemos un color
		fuente.setColor(Color.BLUE);
		// y le aumentamos el tamaño
		fuente.setScale(2f);
		
		// Si se ha tocado la pantalla lo tratamos aquí.
		 if(Gdx.input.justTouched()) {
		      Gdx.app.log("Log", "Han tocado: "+ (contador++) +":" + Gdx.input.getX() + ":" + Gdx.input.getY());

		      // Al tocar la pantalla cambia el estado de juego.
		      switch (mundo.getGameStatus()) {
		      	case World.GAME_INIT_STATUS:
		      			// Si estamos en el inicio, pasamos a barajar.
		      			// Ponemos la música
		      			sorteo.setLooping(true); // en bucle
		      			sorteo.play(); // y a sonar.

		      			// Cambiamos el estado del "mundo"
		      			mundo.setGameStatus(World.SHUFFLE_STATUS);
		      			Gdx.app.log("Log", "Pasando a SHUFFLE ");
		      			break;
		      	case World.SHUFFLE_STATUS:
		      			// Si estabamos en estado barajar, pasamos a resultado.
		      			// Quitamos la música de fondo
				   		sorteo.stop();
				   		// Hacemos sonar una risa volumen 0.9f (va de 0 a 1)
						haha.play(0.9f);

		      			// Ponemos una música aleatoria para amenizar
				  		// Hay 7 canciones de 0 a 6
				   		int cancion = mundo.getIndiceElegido() % 7;
				   		fondo = Gdx.audio.newMusic(Gdx.files.internal("data/music"+cancion+".mp3"));
				   		fondo.setLooping(true);
				   		fondo.play();
				   		// Cambiamos el estado del "mundo" a resultado
						mundo.setGameStatus(World.RESULT_STATUS);
			  		    Gdx.app.log("Log", "Pasando a RESULTADO ");
						break;
		      	case World.RESULT_STATUS:
		      			// Si estabamos en estado resultado, pasamos a inicio
						mundo.setGameStatus(World.GAME_INIT_STATUS);
						// Y quitamos la música
						fondo.stop();
			  		      Gdx.app.log("Log", "Pasando a INIT ");
						break;
				default:
						// cualquier otro caso, estamos en inicio
						mundo.setGameStatus(World.GAME_INIT_STATUS);
						break;
		   
		      }
		      

		   }

		
		// Borramos la pantalla
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);


		// Vamos a empezar a pintar mediante el batch
		batch.begin();
		
		 // Ahora según el estado en que estemos, mostraremos por pantalla una cosa u otra.
		// Las dimensiones iniciales establecidas son 480x320
		// Se pueden sacar con Gdx.graphics.getWidth() y Gdx.graphics.getHeight()
		 switch (mundo.getGameStatus()) {
		 	case World.GAME_INIT_STATUS:
		 			// Estamos en inicio? ponemos la presentación
		 			batch.draw(texturaFondo,0,20);
		 			break;
		 			
		 	case World.SHUFFLE_STATUS:
	 			 // Si estamos en el estado de barajar, sacamos alumnos nuevos.
		 		// Sacamos un alumno y su índice
			 	alumno = mundo.sacarAleatorio();
			 	elegido = mundo.getIndiceElegido() + 1;

			 	// Cargamos una textura
				texturaPersonaje = new Texture(Gdx.files.internal("data/sprites/mario"+elegido+".jpg"));

				// Sacamos ancho de texto, para poder posicionarlo luego
			 	anchuraTexto = fuente.getBounds( alumno.getNombre()).width;

			 	Gdx.app.log("Log", "Alumno:  " + alumno.getNombre() + ":" + mundo.getIndiceElegido());
				// ponemos el texto posicionado al centro horizontal
				fuente.draw(batch,  alumno.getNombre(), Gdx.graphics.getWidth()/2 - anchuraTexto/2, 100);
				// el personaje lo ponemos a ojímetro
				batch.draw(texturaPersonaje, 220,150);
				break;
				
		 	case World.RESULT_STATUS:
				// ponemos el texto posicionado al centro horizontal
				fuente.draw(batch,  alumno.getNombre(), Gdx.graphics.getWidth()/2 - anchuraTexto/2, 100);
				// el personaje lo ponemos a ojímetro
				batch.draw(texturaPersonaje, 220,150);
				break;
		 	default:
				break;
		 }

		// Y con esto ya pintamos la pantalla
		batch.end();
		
	}

	/**
	 * dispose
	 * invocado cuando el programa va a morir
	 * muy útil para liberar memoria
	 */
	@Override
	public void dispose() {
		fuente.dispose();
		texturaPersonaje.dispose();
		batch.dispose();
		texturaFondo.dispose();
	}
	
	/**
	 * resize
	 * invocado al redimensionar la pantalla
	 */
	@Override
	public void resize(int width, int height) {
	}

	/**
	 * pause
	 * invocado al pausar la aplicación
	 */
	@Override
	public void pause() {
	}

	/**
	 * resume
	 * invocado al salir de la pausa
	 */
	@Override
	public void resume() {
	}
}
