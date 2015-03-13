package info.pello.games.arkapong;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;

public class ArkapongGameTwoActivity extends AndroidApplication {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        AndroidApplicationConfiguration cfg = new AndroidApplicationConfiguration();
        cfg.useGL20 = false;
        
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(ArkapongGameTwoActivity.this);
        // El modo de tirada, si no está puesto cogerá real
        float speed = Float.parseFloat(sp.getString("speed", "3"));

        initialize(new ArkaPong(false, speed), cfg);
    }
}