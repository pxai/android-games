/**
 * 
 */
package info.pello.games.libgdx.kitty;


import java.util.Hashtable;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Assets
 * La clase Assets agrupa todos los elementos gráficos y de sonido
 * del juego. Además optimiza la carga de imágenes ya que usa una textura
 * única y de ella saca regiones concretas para cada cosa: las frutas, los fondos, etc..
 * Los sonidos los carga de sus ficheros correspondientes.
 * @author Pello Altadill
 *
 */
public class Assets  {

	// Este contendrá la textura completa
	public static TextureAtlas atlas;
	// Cada una de estas es una parte de la textura
	public static AtlasRegion background;
	public static AtlasRegion splash;
	public static AtlasRegion apple;
	public static AtlasRegion banana;
	public static AtlasRegion bee1;
	public static AtlasRegion bee2;
	public static AtlasRegion cherries;
	public static AtlasRegion grapes;
	public static AtlasRegion icecream;
	public static AtlasRegion lollipop;
	public static AtlasRegion peach;
	public static AtlasRegion strawberry;
	public static AtlasRegion kitty;
	public static AtlasRegion lft;
	public static AtlasRegion right;
	// Los sonidos
	public static Sound hello;
	public static Sound fail;
	public static Sound appleSound;
	public static Sound bananaSound;
	public static Sound bee1Sound;
	public static Sound bee2Sound;
	public static Sound cherriesSound;
	public static Sound grapesSound;
	public static Sound icecreamSound;
	public static Sound lollipopSound;
	public static Sound peachSound;
	public static Sound strawberrySound;
	public static Sound letsPlaySound;
	public static Sound gameOverSound;
	public static Sound helloKittySound;
	
	// Unos hash para poder cargar regiones y sonidos por su nombre
	private static Hashtable<String,AtlasRegion> imagenes = new Hashtable<String,AtlasRegion>();
	private static Hashtable<String,Sound> sonidos = new Hashtable<String,Sound>();
	
	// Para la música, solo hay una
	public static Music music;
	
	/**
	 * load
	 * este es el método que carga el fichero con la super textura
	 * que contiene todo.
	 */
	public static void load(){
		atlas = new TextureAtlas(Gdx.files.internal("data/pack"));
		
		// Sacamos el fondo
		background = atlas.findRegion("background");
		
		// Sacamos la pantalla de inicio
		splash = atlas.findRegion("splash");

		// Y ahora sacamos cada fruta
		apple = atlas.findRegion("apple");
		// Y la metemos en el hashtable para luego poder sacarla por nombre
		imagenes.put("apple", apple);
		
		// y así con cada fruta...
		banana = atlas.findRegion("banana");
		imagenes.put("banana", banana);
		cherries = atlas.findRegion("cherries");
		imagenes.put("cherries", cherries);
		grapes = atlas.findRegion("grapes");
		imagenes.put("grapes", grapes);
		icecream = atlas.findRegion("icecream");
		imagenes.put("icecream", icecream);
		lollipop = atlas.findRegion("lollipop");
		imagenes.put("lollipop", lollipop);
		peach = atlas.findRegion("peach");
		imagenes.put("peach", peach);
		strawberry = atlas.findRegion("strawberry");
		imagenes.put("strawberry", strawberry);

		// La abeja, para un futuro
		bee1 = atlas.findRegion("bee1");
		bee2 = atlas.findRegion("bee2");

		// la personaja
		kitty = atlas.findRegion("kitty");
		// Los botones
		lft = atlas.findRegion("lft");
		right = atlas.findRegion("right");
		
		// Los sonidos
		hello = Gdx.audio.newSound(Gdx.files.internal("data/hellokitty.ogg"));
		fail = Gdx.audio.newSound(Gdx.files.internal("data/fail.ogg"));
		gameOverSound = Gdx.audio.newSound(Gdx.files.internal("data/gameover.ogg"));
		letsPlaySound = Gdx.audio.newSound(Gdx.files.internal("data/letsplay.ogg"));

		// Ahora por cada fruta cargamos el sonido
		appleSound = Gdx.audio.newSound(Gdx.files.internal("data/apple.ogg"));
		// Y lo metemos en el hastable para poder sacarlo por nombre
		sonidos.put("apple", appleSound);
		bananaSound = Gdx.audio.newSound(Gdx.files.internal("data/banana.ogg"));
		sonidos.put("banana", bananaSound);
		cherriesSound = Gdx.audio.newSound(Gdx.files.internal("data/cherries.ogg"));
		sonidos.put("cherries", cherriesSound);
		grapesSound = Gdx.audio.newSound(Gdx.files.internal("data/grapes.ogg"));
		sonidos.put("grapes", grapesSound);
		icecreamSound = Gdx.audio.newSound(Gdx.files.internal("data/icecream.ogg"));
		sonidos.put("icecream", icecreamSound);
		lollipopSound = Gdx.audio.newSound(Gdx.files.internal("data/lollipop.ogg"));
		sonidos.put("lollipop", lollipopSound);
		peachSound = Gdx.audio.newSound(Gdx.files.internal("data/peach.ogg"));
		sonidos.put("peach", peachSound);
		strawberrySound = Gdx.audio.newSound(Gdx.files.internal("data/strawberry.ogg"));
		sonidos.put("strawberry", strawberrySound);
		
		// Y por último cargamos la música
		music = Gdx.audio.newMusic(Gdx.files.internal("data/music.mp3"));
	}
	
	/**
	 * getByName
	 * @param nombre de la imagen que queremos
	 * @return la correspondiete imagen sacada del hashtable
	 */
	public static AtlasRegion getByName (String name) {

		return imagenes.get(name);
	}
	
	/**
	 * getSoundByName
	 * @param nombre del sonido que queremos sacar
	 * @return el correspondiente sonido sacado del hashtable
	 */
	public static Sound getSoundByName (String name) {
		return sonidos.get(name);
	}
	
	/**
	 * dispose
	 * para cuando queramos liberar la memoria de todo
	 */
	public static void dispose(){
		atlas.dispose();
	}
	
}
