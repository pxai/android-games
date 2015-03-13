package info.pello.games.hittheroad;

import java.util.Random;
import java.util.Vector;

public class World {
	public Player player;
	public Vector<Foe> foes;
	public Vector<Fire> playerFire;
	public Vector explosions;
	private float lastPlayerFire = 0;
	private final static float FIRE_INTERVAL = 0.5f;
	private Random random;
	
	/**
	 * constructor
	 * @param player
	 */
	public World (Player player) {
		this.player = player;
		foes = new Vector<Foe>();
		playerFire = new Vector<Fire>();
		random = new Random();
		initFoes();
	}

	/**
	 * initializes foes
	 */
	private void initFoes() {
		for (int i=0;i<40;i++) {
			foes.add(new Foe(random.nextInt(1000),random.nextInt(10)+3));
		}
	}

	/**
	 * fires... whatever
	 */
	public void playerFire() {
			lastPlayerFire = player.stateTime;
			playerFire.add(new Fire(player.position.x+1,player.position.y));
	}
	
	/**
	 * simple method to avoid kame hame effect when firing
	 * @return
	 */
	public boolean canFire () {
		return (player.stateTime - lastPlayerFire > FIRE_INTERVAL);
	}

	/**
	 * update position based adding direction to
	 * @param delta
	 */
	public void update(float delta){
		
		explosions = new Vector();
		
		for (Fire fire : playerFire) {
			fire.update(delta);
		}
	
		player.update(delta);

		for (int i = foes.size()-1;i>=0;i--) {
			foes.elementAt(i).update(delta);
			if (isHit(foes.elementAt(i))) {
				explosions.add("boom");
	   	 		player.addPoints(1000);
				foes.remove(i);
			}
		}

	}

	/**
	 * checks if foe is hit by player fire
	 * @param elementAt
	 * @return
	 */
	private boolean isHit(Foe foe) {
		
		for (Fire fire : playerFire) {
			if (fire.position.x >= foe.position.x &&
					fire.position.x <= foe.position.x+2 &&
					fire.position.y >= foe.position.y &&
					fire.position.y <= foe.position.y +4) {
				playerFire.removeElement(fire);
				return true;
			}
		}
		
		return false;
	}
}
