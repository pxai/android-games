package info.pello.games.libgdx.kitty;



/**
 * @author pello
 *
 */
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;

public class HelloKitty extends Game {

	public Screen helloKittySplash;
	public Screen helloKittyGame;
	
	@Override
	public void create() {
		Assets.load();
		helloKittySplash = new HelloKittySplash(this);
		setScreen(helloKittySplash);
	}

}
