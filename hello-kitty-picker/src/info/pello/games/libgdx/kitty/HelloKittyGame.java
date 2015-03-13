package info.pello.games.libgdx.kitty;



import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.BoundingBox;


/**
 * HelloKittyGame
 * Pantalla principal del juego HelloKitty
 * @author Pello Xabier
 *
 */
public class HelloKittyGame extends HelloKittyScreen  {
	
	// Nuestra cámara especial
	private OrthographicCamera camera;
	// El spritebatch para pintar
	private SpriteBatch batch;
	// Textura y sprite para pintar el fondo
	private Texture texture;
	private Sprite sprite;
	// Nuestro mundo
	private World world;
	// Los recursos agrupados en la clase Assets
	private Assets assets;
	
	// Por cada botón creamos un BoundingBox 
	// para facilitar la detección de toque
	BoundingBox btnLeft;
	BoundingBox btnRight;
	

	/**
	 * Constructor de la pantalla de juego
	 * Le pasamos una instancia de game para poder manejar otras pantallas
	 * @param game
	 */
	public HelloKittyGame(Game game) {
		// Llamamos al constructor de la superclase
		// este constructor llamará al create!!
		super(game);
		
	}
	
	/**
	 * create
	 * inicia los elementos necesarios para la pantalla de juego:
	 * cámara, texturas, regiones, sonidos, etc..
	 */
	protected void create() {		
		// Crea una instancia de los Assets
		assets = new Assets();
		
		// Reproducimos un sonido
	    Assets.letsPlaySound.play(1);
	    // Y ponemos en marcha la música
	    Assets.music.play();
	    
		// Creamos una instancia del mundo
	    world = new World();
		
	    // Creamos una cámara para que nuestro mundo 
	    // sea 15x10
	    camera = new OrthographicCamera(15, 10);
	    // y ponemos la cámara en medio
		camera.position.set(15f / 2, 10f / 2, 0);
		
		// creamos el batcher para pintar
		batch = new SpriteBatch();

		// Y creamos dos boundingbox para poder controlar mejor
		// dónde toca el usuario
		btnLeft = new BoundingBox(new Vector3(0, 0, 0), new Vector3(1, 1, 0));
		btnRight = new BoundingBox(new Vector3(14, 0, 0), new Vector3(15, 1, 0));
		
		// Recalcula  el punto de vista de la cámara,
		// en realidad solo haría falta si hubieramos
		// cambiado alguna de sus propiedades
		camera.update();

	}

	/**
	 * render
	 * Vamos allá, en este método se actualizará el dibujo
	 * según el estado del mundo
	 */
	@Override
	public void render(float delta) {	
		// Han tocado la pantalla, así que vamos a ver
		// si han tocado alguno de los botones que mueven a Kitty
		 if(Gdx.input.justTouched()) {
			 // Para controlar la coincidencia usamos un vector
		      Vector3 touchPos = new Vector3();
		      touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
		      // el unproject hace que la posición se ponga según la camara actual.
		      camera.unproject(touchPos);
		      Gdx.app.log("Log", "Han tocado en el game: " + Gdx.input.getX() + ":" + Gdx.input.getY());
		      
		      // ¿Han tocado el botón izquierdo?
				if(btnLeft.contains(touchPos)){
					 // movemos a kitty a la izquierda
				      world.kitty.left();
				      Gdx.app.log("Log", "Han tocado left: " + world.kitty.x + ":" + world.kitty.y);
				}
				
				// ¿Han tocado el botón derecho?
				if(btnRight.contains(touchPos)){
					  // movemos a kitty a la derecha
				      world.kitty.right();
				      Gdx.app.log("Log", "Han tocado right: " + world.kitty.x + ":" + world.kitty.y);
				}
		   }

		// Actualizamos el mundo
		world.update(delta);
		 
		// Limpiamos la pantalla
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

		// Asociamos la camara a nuestro batcher
		batch.setProjectionMatrix(camera.combined);
		// necesario si queremos pintar sprites
		batch.enableBlending();
		// Y a pintar!
		batch.begin();
		batch.draw(Assets.background, 0, 0, 15, 10); // fondo
		batch.draw(Assets.lft, 0, 0, 1, 1); // botón izquierdo
		batch.draw(Assets.right, 14, 0, 1, 1); // botón derecho
		
		// Y ahora pintamos las frutas
		Fruit fruit = null;
		for (int i=0; i < world.fruits.size();i++ ) {
			fruit = world.fruits.get(i);
			// ¿alguna fruta está pillada?
			if (fruit.state == Fruit.CATCH) {
				// mensajito
			     Gdx.app.log("Log", "Fruit picked!! " + fruit.fruitName + " :" + world.points + " po");
			     // hacemos sacar un sonido
			     Assets.getSoundByName(fruit.fruitName).play();
			     // Y sacamos esa fruta del mundo
			     world.fruits.remove(i);
			}
			
			// ¿Alguna fruta se ha espachurrado?
			if (fruit.state == Fruit.FAIL) {
				// mensajito...
			     Gdx.app.log("Log", "Fruit failed! " + fruit.fruitName);
			     // sonido de fallo
			     Assets.fail.play(1);
			     // Y sacamos esa fruta del mundo
				world.fruits.remove(i);
			}
			// En cualquier otro caso sacamos la fruta en la posición correspondiente
			batch.draw(Assets.getByName(fruit.fruitName), fruit.position.x - 1f/4, fruit.position.y - 1f/8, 0.5f,0.5f);
		     Gdx.app.log("Log", "Fruit rain: " +":"+ fruit.fruitName +":"+ fruit.position.x + ":" + fruit.position.y);
			
		}
		// Y por último pintamos a Kitty
		batch.draw(Assets.kitty, world.kitty.x, 0,1f,1.5f);
		batch.end();

	}

	@Override
	public void dispose() {
		batch.dispose();
		texture.dispose();
	}
	
	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

}
