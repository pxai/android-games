package info.pello.games.arkapong;


import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class ArkapongPreferencesActivity extends PreferenceActivity {
	Button botonmenu;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
 
        addPreferencesFromResource(R.xml.preferences);
        setContentView(R.layout.preferences_layout);
        botonmenu = (Button) this.findViewById(R.id.botonmenu);
        botonmenu.setOnClickListener(new View.OnClickListener() {
    	    public void onClick(View view) {
    	    	Intent miIntent = new Intent(ArkapongPreferencesActivity.this, ArkapongMenuActivity.class);
    	    	startActivity(miIntent);
    	    	ArkapongPreferencesActivity.this.finish();
    	    }
    	});
    }
}