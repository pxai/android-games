package info.pello.games.libgdx.hola;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * HolaGame
 * Ejemplo mínimo de aplicación libgdx.
 * Muestra por pantalla una textura (una imagen) y un texto
 * @author Pello Altadill
 *
 */
public class HolaGame implements ApplicationListener {

	private SpriteBatch batch;
	private Texture textura;
	private BitmapFont fuente;

	/**
	 * create
	 * se invoca al crear el juego por primera vez, es donde
	 * se suelen meter las inicializaciones, se cargan texturas, etc..
	 */
	@Override
	public void create() {		
		
		// Creamos una textura cargando una imagen
		// Si hemos generado el proyecto con gdx-setup-ui.jar este recurso
		// debe estar en el directorio assets del proyecto android correspondiente
		textura = new Texture(Gdx.files.internal("data/mario1.jpg"));
		
		// Instanciamos el SpriteBatch donde acumularemos las ordenes de dibujo
		// para que el dibujado se más eficiente.
		batch = new SpriteBatch();

	}

	/**
	 * render
	 * método que el juego invoca una y otra vez.  Es el encargado de pintar
	 * la pantalla con las texturas, sprites, textos, etc. según es estado del juego
	 * en ese momento.
	 */
	@Override
	public void render() {		
		// Borramos la pantalla
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

		// Creamos un String y un BitmapFont para sacar texto por pantalla
		String texto = "Muerte negra total";
		fuente = new BitmapFont();

		
		// Iniciamos el batch
		batch.begin();
		// metemos la textura en x:100 y:200
		batch.draw(textura, 100,200);
		// metemos el texto en x:50, y:150
		fuente.draw(batch, texto, 50, 150);
		// y a pintar.
		batch.end();
	}

	/**
	 * dispose
	 * invocado cuando el programa va a morir
	 * muy útil para liberar memoria
	 */
	@Override
	public void dispose() {
		// liberamos los recursos
		batch.dispose();
		textura.dispose();
		fuente.dispose();
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

