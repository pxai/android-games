package info.pello.games.hittheroad;

import java.util.Iterator;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.BoundingBox;

public class HitTheRoadGame extends ApplicationAdapter {
    Animation straightAnimation;
    Animation upAnimation;
    Animation downAnimation;
    Animation foeAnimation;
    Texture carTexture;
    Texture foeTexture;
    World world;
	private TiledMapTileLayer collisionLayer;
	private OrthographicCamera camera;
	private SpriteBatch batch;
	private Texture fireTexture;
	private Sprite sprite;
	Sound laser;
	Sound explosion;
	Music music;
	
    BoundingBox btnUp;
    BoundingBox btnDown;
    BoundingBox btnAccelerate;
    BoundingBox btnFire;
    private BitmapFont scoreFont;
    
    // Map:
	private TiledMap map;
	private TiledMapRenderer renderer;
	private final static float CAMERA_WIDTH = 15;
	private final static float CAMERA_HEIGHT = 10;
	
	
	@Override
	public void create() {		
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();
		
		camera = new OrthographicCamera(h, w);
		batch = new SpriteBatch();
		
		explosion = Gdx.audio.newSound(Gdx.files.internal("data/explosion.mp3"));
		laser = Gdx.audio.newSound(Gdx.files.internal("data/laser.mp3"));
		music = Gdx.audio.newMusic(Gdx.files.internal("data/motor.mp3"));
		
        carTexture = new Texture(Gdx.files.internal("data/player.gif"));
        foeTexture = new Texture(Gdx.files.internal("data/foe.gif"));
        fireTexture = new Texture(Gdx.files.internal("data/shuriken.png"));

        TextureRegion[] straightAnimationFrames = TextureRegion.split(carTexture, 42, 21)[0];
        TextureRegion[] downAnimationFrames= TextureRegion.split(carTexture, 42, 21)[1];
        TextureRegion[] upAnimationFrames  = TextureRegion.split(carTexture, 42, 21)[2];
        TextureRegion[] foeAnimationFrames = TextureRegion.split(foeTexture, 42, 21)[0];
		
        straightAnimation = new Animation(0.25f, straightAnimationFrames);
        upAnimation = new Animation(0.25f, upAnimationFrames);
        downAnimation = new Animation(0.25f, downAnimationFrames);
        foeAnimation = new Animation(0.25f,foeAnimationFrames);
        
        btnDown = new BoundingBox(new Vector3(0, 0, 0), new Vector3(2, 5, 0));
        btnUp = new BoundingBox(new Vector3(0, 5, 0), new Vector3(2, 10, 0));
        btnFire = new BoundingBox(new Vector3(11, 0, 0), new Vector3(13, 10, 0));
        btnAccelerate = new BoundingBox(new Vector3(13, 0, 0), new Vector3(15, 10, 0));
        
        scoreFont = new BitmapFont(Gdx.files.internal("data/emulogic.fnt"), Gdx.files.internal("data/emulogic.png"), false);
		
        world = new World(new Player(0,6));
        
		map = new TmxMapLoader().load("data/road.tmx");
        renderer = new OrthogonalTiledMapRenderer(map, 1 / 32f);
        collisionLayer = (TiledMapTileLayer)map.getLayers().get(0);
        
		// We create a camera
		camera = new OrthographicCamera();

        camera.setToOrtho(false, CAMERA_WIDTH, CAMERA_HEIGHT);
        camera.position.set(CAMERA_WIDTH/2,CAMERA_HEIGHT/2,0);
  		camera.update();
  		
        batch = new SpriteBatch();
  		batch.setProjectionMatrix(camera.combined);
  		
  		// commented or I'll go insane
  		//music.play();
	}

	@Override
	public void dispose() {
		batch.dispose();
	}
	
	/**
	 * updates screen buttons coordinates
	 * always relative to player x
	 */
	private void updateButtons() {
	      btnDown.set(new Vector3(world.player.position.x, 0, 0), new Vector3(world.player.position.x + 2, 5, 0));
	      btnUp.set(new Vector3(world.player.position.x, 5, 0), new Vector3(world.player.position.x + 2, 10, 0));
	      btnFire.set(new Vector3(world.player.position.x + 11, 0, 0), new Vector3(world.player.position.x + 13, 10, 0));
	      btnAccelerate.set(new Vector3(world.player.position.x + 13, 0, 0), new Vector3(world.player.position.x + 15, 10, 0));
	}

	@Override
	public void render() {		
        Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
        world.player.changeDirection(0, 0);
        
        updateButtons();
   	 	checkTouched();
   	 	checkKeyInput();
   	 	
		world.update(Gdx.graphics.getDeltaTime());
   	    moveCamera(world.player.position.x,world.player.position.y);
		camera.update();
		renderer.setView(camera);
		renderer.render();
		
   	 	TextureRegion playerFrame = null;
   	 	TextureRegion foeFrame = null;

   	 	if (world.player.direction.y > 0 && world.player.direction.x > 0) {
   	 		playerFrame = upAnimation.getKeyFrame(world.player.stateTime, true);
   	 	} else if (world.player.direction.y < 0  && world.player.direction.x > 0) {
   	 		playerFrame = downAnimation.getKeyFrame(world.player.stateTime, true);   	 		
   	 	} else if (world.player.direction.x > 0) {
   	 		playerFrame = straightAnimation.getKeyFrame(world.player.stateTime, true);
   	 	} else {
   	 		playerFrame = straightAnimation.getKeyFrame(2, true);   	 		
   	 	}
   	 	
		batch.setProjectionMatrix(camera.combined);
		batch.begin();

		// Draw each foe
   	 	for (Foe foe : world.foes) {//(float) Math.random()*100
   	 		foeFrame = foeAnimation.getKeyFrame((float) Math.random()*100, true);
   	        batch.draw(foeFrame, foe.position.x, foe.position.y,2,1);
   	 	}
   	 	
		// Draw each player fire
   	 	for (Fire fire : world.playerFire) {
   	        batch.draw(fireTexture, fire.position.x, fire.position.y,1,1);
   	 	}
   	 	
   	 	// any explosion?
   	 	for (int i=0;i<world.explosions.size();i++) {
   	 		explosion.play();
   	 	}
   	 	world.explosions.removeAllElements();
   	 	
   	 	
        batch.draw(playerFrame, world.player.position.x, world.player.position.y,2,1);
		scoreFont.setScale(0.06f);
		scoreFont.draw(batch, "SCORE: " +world.player.getPoints(), world.player.position.x, world.player.position.y+4); 
		batch.end();
	}

	/**
	 * Checks wether a key has been pressed or not
	 */
	private void checkTouched() {
		if(Gdx.input.justTouched() || Gdx.input.isTouched()) {
			 Vector3 touchPos = new Vector3();
			 touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
			  camera.unproject(touchPos);
		  Gdx.app.log("Log", "Coords or key pressed: " + Gdx.input.getX() + ":" + Gdx.input.getY());
		  //turn left or right 
		      if(btnUp.contains(touchPos)){
		      	  world.player.changeDirection(world.player.direction.x, 1);
			      Gdx.app.log("Log", "Up");
			}
			
			if(btnDown.contains(touchPos)){
		    	  world.player.changeDirection(world.player.direction.x, -1);
			      Gdx.app.log("Log", "Down");
			}
			
			if(btnAccelerate.contains(touchPos)){
		    	  world.player.changeDirection(1, world.player.direction.y);
		    	  world.player.goForward();
			      Gdx.app.log("Log", "Acceleration pressed ");
			}
			
			if(btnFire.contains(touchPos)){
				if (world.canFire()) {
					world.playerFire();
					laser.play();
				}
					Gdx.app.log("Log", "Fire pressed ");
			}
		 }
	}
	
	/**
	 * Checks for user key pressed
	 */
	private void checkKeyInput () {
		if(Gdx.input.isKeyPressed(Input.Keys.A) && 
				Gdx.input.isKeyPressed(Input.Keys.M)) {
	          Cell currentCell = collisionLayer.getCell((int)world.player.position.x/32,(int)world.player.position.y/32);
	          Gdx.app.log("Tile ", (int)world.player.position.x/32+":"+(int)world.player.position.y/32+"." + showCellProperties(currentCell));

		      	  world.player.changeDirection(0.1f, 1);
		    	  //moveCamera(0.1f,0.1f);
			      Gdx.app.log("Log", "Han tocado up: ");
	    } else if(Gdx.input.isKeyPressed(Input.Keys.Z) && 
				Gdx.input.isKeyPressed(Input.Keys.M)) {
		    	  world.player.changeDirection(0.1f, -1);
		    	  //moveCamera(0.1f,-0.1f);
			      Gdx.app.log("Log", "Han tocado dow:n ");
		} else if(Gdx.input.isKeyPressed(Input.Keys.M)){
		    	  world.player.changeDirection(0.1f, 0);
		    	  world.player.goForward();
			      Gdx.app.log("Log", "Han acelerado ");
		 }
		
		// Fire is pressed
		if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
			if (world.canFire()) {
				world.playerFire();
				laser.play();
			}
		    Gdx.app.log("Log", "Fire pressed ");
		}
	}

	/**
	 * method to move the camera within map boundaries
	 * @param input
	 */
	private void moveCamera(float xpositions, float ypositions) {
      //  camera.position.x += xpositions;		
       // camera.position.y += ypositions;	

            camera.position.set(xpositions+(CAMERA_WIDTH/2),ypositions, 0);
            camera.update();

	}
	

	private String showCellProperties(Cell currentCell) {
		// TODO Auto-generated method stub
		String properties = "";
		properties += "id: " + currentCell.getTile().getId() + ",[ ";
		Iterator<String> keys = currentCell.getTile().getProperties().getKeys();
		while (keys.hasNext()) {
			String k = keys.next();
			properties += "keys: " + k + "=" + currentCell.getTile().getProperties().get(k)+ ", ";			
		}
		return "]"+ properties;
	}
	
	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}
}
