package info.pello.games.arkapong;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;

public class ArkapongGameActivity extends AndroidApplication {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        AndroidApplicationConfiguration cfg = new AndroidApplicationConfiguration();
        cfg.useGL20 = false;
        // Cargamos las preferences
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(ArkapongGameActivity.this);
        // El modo de tirada, si no está puesto cogerá real
        float speed = Float.parseFloat(sp.getString("speed", "2"));
        initialize(new ArkaPong(true,speed), cfg);
    }
}