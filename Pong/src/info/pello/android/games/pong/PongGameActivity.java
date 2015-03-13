package info.pello.android.games.pong;

import android.os.Bundle;
import android.app.Activity;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.support.v4.app.NavUtils;

public class PongGameActivity extends Activity {

	public static TextView tvEvent;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        //tvEvent = (TextView) this.findViewById(R.id.tv_event);
        
        // En lugar de asociar un fichero de layout
        // le asociamos directamente una clase que extiende un View:
        // que es de tipo SurfaceView
        setContentView(new PantallaJuego(this));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //getMenuInflater().inflate(R.menu.pong_game_activity, menu);
        return true;
    }


    
}
