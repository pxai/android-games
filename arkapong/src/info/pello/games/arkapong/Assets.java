package info.pello.games.arkapong;


import java.util.Hashtable;
import java.util.Random;

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


        // Los sonidos
        public static Sound pong;
        public static Sound fail;
        public static Sound white;
        public static Sound red;
        public static Sound	yellow;
        public static Sound blue;
        public static Sound green;
        public static Sound orange;
        public static Sound pink;
        public static Sound brown;
        public static Sound black;
        public static Sound pip0;
        public static Sound pip1;
        public static Sound pop0;
        public static Sound pop1;
        public static Sound pop2;
        public static Sound pop3;
        public static Sound pop4;

        
        private static Hashtable<String,Sound> sounds = new Hashtable<String,Sound>();
        
        // Para la música, solo hay una
        public static Music music;
		private static Random random = new Random();
        
        /**
         * load
         * este es el método que carga el fichero con la super textura
         * que contiene todo.
         */
        public static void load(){

                
                // Los sonidos
                pong = Gdx.audio.newSound(Gdx.files.internal("data/sounds/pong.mp3"));
                sounds.put("pong", pong);
                fail = Gdx.audio.newSound(Gdx.files.internal("data/fail.ogg"));
                sounds.put("fail", fail);
                // colors
                white = Gdx.audio.newSound(Gdx.files.internal("data/sounds/white.mp3"));
                sounds.put("white", white);
                red = Gdx.audio.newSound(Gdx.files.internal("data/sounds/red.mp3"));
                sounds.put("red", red);
                blue = Gdx.audio.newSound(Gdx.files.internal("data/sounds/blue.mp3"));
                sounds.put("blue", blue);
                yellow = Gdx.audio.newSound(Gdx.files.internal("data/sounds/yellow.mp3"));
                sounds.put("yellow", yellow);
                green = Gdx.audio.newSound(Gdx.files.internal("data/sounds/green.mp3"));
                sounds.put("green", green);
                black = Gdx.audio.newSound(Gdx.files.internal("data/sounds/black.mp3"));
                sounds.put("black", black);
                orange = Gdx.audio.newSound(Gdx.files.internal("data/sounds/orange.mp3"));
                sounds.put("orange", orange);
                brown = Gdx.audio.newSound(Gdx.files.internal("data/sounds/brown.mp3"));
                sounds.put("brown", brown);
                pink = Gdx.audio.newSound(Gdx.files.internal("data/sounds/pink.mp3"));
                sounds.put("pink", pink);

                pip0 = Gdx.audio.newSound(Gdx.files.internal("data/sounds/pip0.mp3"));
                sounds.put("pip0", pip0);
                pip1 = Gdx.audio.newSound(Gdx.files.internal("data/sounds/pip1.mp3"));
                sounds.put("pip1", pip1);
            
                pop0 = Gdx.audio.newSound(Gdx.files.internal("data/sounds/pop0.mp3"));
                sounds.put("pop0", pop0);                	
                pop1 = Gdx.audio.newSound(Gdx.files.internal("data/sounds/pop1.mp3"));
                sounds.put("pop1", pop1);                	
                pop2 = Gdx.audio.newSound(Gdx.files.internal("data/sounds/pop2.mp3"));
                sounds.put("pop2", pop2);                	
                pop3 = Gdx.audio.newSound(Gdx.files.internal("data/sounds/pop3.mp3"));
                sounds.put("pop3", pop3);                	
                pop4 = Gdx.audio.newSound(Gdx.files.internal("data/sounds/pop4.mp3"));
                sounds.put("pop4", pop4);                	
     
               // Y por último cargamos la música
                music = Gdx.audio.newMusic(Gdx.files.internal("data/music.mp3"));
        }
        

        
        /**
         * getSoundByName
         * @param nombre del sonido que queremos sacar
         * @return el correspondiente sonido sacado del hashtable
         */
        public static Sound getSoundByName (String name) {
                return sounds.get(name);
        }

        /**
         * getRandomPop
         * @param nombre del sonido que queremos sacar
         * @return el correspondiente sonido sacado del hashtable
         */
        public static Sound getRandomPop () {
        		int what = random.nextInt(5);
                return getSoundByName("pop"+what);
        }
        /**
         * dispose
         * para cuando queramos liberar la memoria de todo
         */
        public static void dispose() {
        }
        
}

