package info.pello.games.arkapong;

import java.util.Hashtable;

import android.content.Context;
import android.graphics.Typeface;

/**
 * FontCache
 * a proper way to get fonts for android controls
 * As seen on http://stackoverflow.com/questions/16901930/memory-leaks-with-custom-font-for-set-custom-font/16902532#16902532
 * @author stackoverflow
 *
 */
public class FontCache {

    private static Hashtable<String, Typeface> fontCache = new Hashtable<String, Typeface>();

    /**
     * gets font 
     * @param name
     * @param context
     * @return
     */
    public static Typeface get(String name, Context context) {
        Typeface tf = fontCache.get(name);
        if(tf == null) {
            try {
                tf = Typeface.createFromAsset(context.getAssets(), name);
            }
            catch (Exception e) {
                return null;
            }
            fontCache.put(name, tf);
        }
        return tf;
    }
}
