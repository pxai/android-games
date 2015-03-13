/**
 * 
 */
package info.pello.games.libgdx.kitty;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Game;

/**
 * HelloKittyScreen
 * Clase abstracta que necesitamos para poder usar más de una pantalla
 * en nuestro juego. Cada pantalla del juego (inicio, juego, fin,... )
 * hereda de esta clase de tal forma que podremos:
 * 1. Disponer de los mismos métodos render, dispose, resize
 * 2. Pasar una instancia del juego en el constructor (Game game)
 * 3. Poder cambiar de pantalla a través de la instancia de juego
 * @author Pello Xabier
 *
 */
public abstract class HelloKittyScreen implements Screen {
	// El juego
	Game game;
	
	/**
	 * Constructor al que le pasamos el juego
	 * @param game
	 */
	public HelloKittyScreen (Game game) {
		this.game = game;
		create();
	}
	
	/**
	 * create
	 * este método no es parte de Screen así
	 * que lo metemos nosotros. Los herederos
	 * lo implementarán
	 */
	protected abstract void create ();
	

	@Override
	public void render (float delta) {
	}


	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}


}
