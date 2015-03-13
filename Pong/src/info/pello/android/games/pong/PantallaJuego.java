package info.pello.android.games.pong;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;


/**
 * @author pello altadill
 * @version 1.0
 * Pantalla Juego extiende SurfaceView, un view especial que nos permite
 * refrescar continuamente lo que se ve en pantalla y está indicado para
 * juegos.
 * Además implemente SurfaceHolder.Callback para dar soporte a las interacciones
 * con el usuario.
 */
public class PantallaJuego extends SurfaceView implements SurfaceHolder.Callback {
	
	// El hilo de ejecución del juego
	private HiloPrincipalJuego hiloPrincipalJuego;
	private float x = 10;
	private float y = 10;
	
	/**
	 * PantallaJuego
	 * Constructor de la clase
	 * en el contexto contiene la Activity de Android que lo contiene.
	 * @param contexto
	 */
	public PantallaJuego (Context contexto) {
		super(contexto);
		getHolder().addCallback(this);
		setFocusable(true);
		hiloPrincipalJuego = new HiloPrincipalJuego(this.getHolder(), this);
	} 
	
	/**
	 * surfaceChanged
	 * Se invoca en el caso de que cambie la confi de pantalla
	 * @param SurfaceHolder holder
	 * @param int format
	 * @param int width
	 * @param int height
	 */
	public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
		
	}
	
	/**
	 * surfaceCreated
	 * Se invoca automáticamente en el momento en que se crea la pantalla
	 * por tanto aprovechamos para iniciar el hilo
	 * @param SurfaceHolder holder
	 */
	public void surfaceCreated (SurfaceHolder holder) {
		hiloPrincipalJuego.finalizarJuego(false);
		hiloPrincipalJuego.start();
	}
	
	/**
	 * surfaceDestroyed
	 * Se invoca automáticamente al cerrar la pantalla, por tanto aprovechamos
	 * para matar el hilo.
	 * @param SurfaceHolder holder
	 */
	public void surfaceDestroyed (SurfaceHolder holder) {
		boolean enMarcha = true;
		while (enMarcha) {
			try {
				hiloPrincipalJuego.join();
				enMarcha = false;
			} catch (InterruptedException ie) {
				
			}
		}
	}
	
	/**
	 * onDraw
	 * Este es el método que se encarga de dibujar en la pantalla
	 */
	protected void onDraw(Canvas canvas) {
		canvas.drawBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.rect), x, y, null);
		x++;
		y++;
	}
	
    /**
     * onTouchEvent
     * Se invoca cuando se toca la pantalla
     * @param MotionEvent event
     * @return boolean
     */
    public boolean onTouchEvent (MotionEvent event) {
    	if (event.getAction() == MotionEvent.ACTION_DOWN) {
    		Log.d("Touch!!","X: " + event.getX() + " - Y: " + event.getY());
    	}
    	
    	return super.onTouchEvent(event);
    }
	
}