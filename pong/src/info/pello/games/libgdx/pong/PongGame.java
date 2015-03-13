package info.pello.games.libgdx.pong;


import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.BoundingBox;

public class PongGame implements ApplicationListener {
	// La cámara que usaremos como punto de vista
	private OrthographicCamera camera;
	// El SpriteBatch para agrupar las ordenes de pintado
	private SpriteBatch batch;
	// La pala: la textura completa y la región que nos interesa
	private Texture texturaPala;
	private TextureRegion regionPala;
	
	// La pelota: la textura completa y la región que nos interesa
	private Texture texturaPelota;
	private TextureRegion regionPelota;
	
	
	// El mundo del juego.
	private World world;
	Vector3 puntoDeToque;
	
	@Override
	public void create() {		
		// Cremos una instancia del mundo
		world = new World();
		
		// Usamos un vector para guardar el punto en el que se toca la pantalla
		puntoDeToque = new Vector3();
		
		// Creamos la cámara especial para ver nuestro mundo en 10x15
		camera = new OrthographicCamera(10, 15);
		camera.position.set(10f / 2, 15f / 2, 0);
		camera.update();
		// Creamos el spritebatch para pintar
		batch = new SpriteBatch();

		// Cargamos la textura de la pala
		texturaPala = new Texture(Gdx.files.internal("data/pala.jpg"));
		texturaPala.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		// y sacamos una región concreta
		regionPala = new TextureRegion(texturaPala, 0,0,64,16);
		
		// Cargamos la textura de la pelota
		texturaPelota = new Texture(Gdx.files.internal("data/pelota.jpg"));
		
		texturaPelota.setFilter(TextureFilter.Linear, TextureFilter.Linear);

		// Sacamos una region de la textura de la pelota.
		regionPelota = new TextureRegion(texturaPelota, 0,0,16,16);

	}


	@Override
	public void render() {		
		// se toca la pantalla?
		if (Gdx.input.isTouched()){
			camera.unproject(puntoDeToque.set(Gdx.input.getX(), Gdx.input.getY(), 0));
			// En función del toque movemos la pala
			world.getPala().mover(puntoDeToque.x - 1);
		}
		
		
		
		 
		// Borramos la pantalla ponemos todo a NEGRO
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		// Al batcher le aplicamos nuestra cámara.
		batch.setProjectionMatrix(camera.combined);
		
		batch.begin();

		// Ha terminado el juego?
		if (world.isGameOver()) {
			// Creamos una nueva instancia y se empieza de nuevo
			world = new World();
		} else {
			// Si la pelota sigue en movimiento actualizamos el mundo
			world.update(Gdx.graphics.getDeltaTime());
			// y pintamos el escenario: pelota y pala
			batch.draw(regionPelota, world.getPelota().posicion.x,world.getPelota().posicion.y,0.5f,0.5f);
			batch.draw(regionPala, world.getPala().posicion.x,world.getPala().posicion.y,3,0.5f);
		}
		batch.end();

	}

	@Override
	public void dispose() {
		batch.dispose();
		texturaPala.dispose();
		texturaPelota.dispose();
	}
	
	@Override
	public void resize(int width, int heigcht) {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}
}
