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

/**
 * HelloKittySplash
 * Pantalla de presentación del juego
 * Poca cosa, muestra una imágen y hace sonar un sonido
 * Al tocar la pantalla el juego se inicia, es decir,
 * se cambia a otra pantalla.
 * Necesita heredar nuestra clase HelloKittyScreen para poder
 * movernos de una pantalla a otra.
 * @author Pello Altadill
 *
 */
public class HelloKittySplash extends HelloKittyScreen  {
	
	// Nuestra cámara especial
	private OrthographicCamera camera;
	// El SpriteBatch para pintar y..
	private SpriteBatch batch;
	// La textura y el sprite que usaremos para pintar
	private Texture texture;
	private Sprite sprite;
	
	/**
	 * El constructor al que le pasamos una instancia de game
	 * para poder cambiar de pantalla
	 * @param game
	 */
	public HelloKittySplash(Game game) {
		// Llamamos al constructor de la superclase
		// este constructor llamará al create!!
		super(game);
	}
	

	/**
	 * create
	 * inicia la cámara, carga imágenes, etc...
	 * invocado desde el constructor
	 */
	protected void create() {		
		// Cargamos ancho y alto de la pantalla
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();
		
		// En este caso la cámara será 1  x 480/320= (1,1.5)
		camera = new OrthographicCamera(1, h/w);
		
		// Instanciamos el spritebatch para pintar
		batch = new SpriteBatch();
		
		// Cargamos el fondo
		texture = new Texture(Gdx.files.internal("data/images1.png"));
		// le ponemos un filtro para suavizarlo y que no salga pixelado
		texture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		// Cargamos una región concreta
		TextureRegion region = new TextureRegion(texture, 0, 0, 480, 360);
		
		// De esa región pasamos a un Sprite
		sprite = new Sprite(region);
		// Le damos un tamaño adecuado teniendo en cuenta nuestra cámara
		sprite.setSize(1, 1 * sprite.getHeight() / sprite.getWidth());
		// Lo colocamos para que quede centrado
		sprite.setPosition(-sprite.getWidth()/2, -sprite.getHeight()/2);
		// Y que suene algo!
	    Assets.hello.play(1);

	}

	/**
	 * render
	 * el método que pintará en pantalla
	 */
	@Override
	public void render(float delta) {	
		 if(Gdx.input.isTouched()) {
			 // Esto es necesario si quisieramos sacar el punto exacto
			 // donde se ha tocado en nuestro mundo, no el punto virtual
		      //Vector3 touchPos = new Vector3();
		      //touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
		      //camera.unproject(touchPos);

		      Gdx.app.log("Log", "Han tocado: " + Gdx.input.getX() + ":" + Gdx.input.getY());
		      game.setScreen(new HelloKittyGame(this.game));
		   }
		 
		 // Limpiamos la pantalla
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		// Asociamos la cámara a nuestro batcher
		batch.setProjectionMatrix(camera.combined);
		
		// Y pintamos...
		batch.begin();
		sprite.draw(batch);
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
