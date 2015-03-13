package info.pello.games.arkapong;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;


	public class TTFTextView extends TextView {

		/*
		 * Caches typefaces based on their file path and name, so that they don't have to be created every time when they are referenced.
		 */
		private static Typeface mTypeface;

		public TTFTextView(final Context context) {
		    this(context, null);
		}

		public TTFTextView(final Context context, final AttributeSet attrs) {
		    this(context, attrs, 0);
		}

		public TTFTextView(final Context context, final AttributeSet attrs, final int defStyle) {
		    super(context, attrs, defStyle);

		     if (mTypeface == null) {
		         mTypeface = FontCache.get("fonts/square.ttf", context);
		     }
		     setTypeface(mTypeface);
		}

		}