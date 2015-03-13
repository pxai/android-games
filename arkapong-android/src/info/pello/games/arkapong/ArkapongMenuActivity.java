package info.pello.games.arkapong;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class ArkapongMenuActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);

        String[] items = { getResources().getString(R.string.game_title),
        		getResources().getString(R.string.game_title_two),
        		getResources().getString(R.string.setup_title),
                getResources().getString(R.string.help_title) };

        ListView menuLista = (ListView) findViewById(R.id.ListView_Menu);
        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this,
        	    R.layout.menu_item, items);
        
        menuLista.setAdapter(adaptador);

        
        menuLista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View itemClicked,
                int position, long id) {
                TextView textView = (TextView) itemClicked;
                String strText = textView.getText().toString();
                if (strText.equalsIgnoreCase(getResources().getString(
                        R.string.game_title))) {

                    startActivity(new Intent(ArkapongMenuActivity.this,
                    		ArkapongGameActivity.class));
                } else if (strText.equalsIgnoreCase(getResources().getString(
                        R.string.game_title_two))) {

                    startActivity(new Intent(ArkapongMenuActivity.this,
                    		ArkapongGameTwoActivity.class));
                } else if (strText.equalsIgnoreCase(getResources().getString(
                        R.string.help_title))) {

                    startActivity(new Intent(ArkapongMenuActivity.this,
                    		ArkapongHelpActivity.class));
                } else if (strText.equalsIgnoreCase(getResources().getString(
                        R.string.setup_title))) {

                    startActivity(new Intent(ArkapongMenuActivity.this,
                    		ArkapongPreferencesActivity.class));
                } 
              }
          });

                    
    }
}