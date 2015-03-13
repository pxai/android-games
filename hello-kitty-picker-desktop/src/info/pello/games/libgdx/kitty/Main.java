package info.pello.games.libgdx.kitty;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.tools.imagepacker.TexturePacker;
import com.badlogic.gdx.tools.imagepacker.TexturePacker.Settings;

@SuppressWarnings("deprecation")
public class Main {
	public static void main(String[] args) {
		/*Settings settings = new Settings();
		settings.padding = 2;
		settings.maxWidth = 1024;
		settings.maxHeight = 1024;
		settings.incremental = true;
		TexturePacker.process(settings, "images", "images");
	*/
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
	 
		cfg.title = "hello-kitty-picker";
		cfg.useGL20 = false;
		cfg.width = 480;
		cfg.height = 320;
		
		new LwjglApplication(new HelloKitty(), cfg);
	
		
	}
}
