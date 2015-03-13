/**
 * 
 */
package info.pello.android.games.pong;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

/**
 * @author luser
 *
 */
public class HiloPrincipalJuego extends Thread {

	private boolean gameOver;
	private SurfaceHolder surfaceHolder;
	private PantallaJuego pantallaJuego;
	
	public HiloPrincipalJuego(SurfaceHolder holder, PantallaJuego pantallaJuego) {
		// TODO Auto-generated constructor stub
		this.surfaceHolder = holder;
		this.pantallaJuego = pantallaJuego;
	}

	public void finalizarJuego (boolean finalizado) {
		gameOver = finalizado;
	}
	
	public void run () {
		Canvas lienzo = null;
		while (!gameOver) {
			try {
				lienzo = this.surfaceHolder.lockCanvas();
				synchronized (surfaceHolder) {
					this.pantallaJuego.onDraw(lienzo);
				}
			}finally {
				if (lienzo != null)
					this.surfaceHolder.unlockCanvasAndPost(lienzo);			
			}
		}// while
	}
}
