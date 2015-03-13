package info.pello.games.arkapong;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.BoundingBox;

/**
 * 
 * @author Pello Altadill
 *
 */
public class ArkaPong implements ApplicationListener {

	

    private static final int WIDTH = 15;

	private static final int HEIGHT = 10;

	// La cámara que usaremos como punto de vista
    private OrthographicCamera camera;
    
    // El SpriteBatch para agrupar las ordenes de pintado
    private SpriteBatch batch;
    
    // The paddles
    private Texture paddleTexture;
    private TextureRegion paddleRegion;
    
    // The ball
    private Texture ballTexture;
    private TextureRegion ballRegion;

    // The special Brick
    private Texture specialBrickTexture;
    private TextureRegion specialBrickRegion;

    // Touch point
    private Vector3 touchPoint;

    private Texture dividerTexture;

    // Our world
    private World world;
    
    // Controls
    private BoundingBox paddle1Control;
    private BoundingBox paddle2Control;
    
    // Random block
    private BoundingBox randomBlock;
    
    // Score font
    private BitmapFont score1Font;
    private BitmapFont score2Font;
    
    // Assets where sounds are stored
    private Assets assets;
    
    private boolean isOnePlayer;
    private float speedBall;

    public ArkaPong () {
    	isOnePlayer = false;
    	speedBall = Ball.DEFAULT_VELOCITY;
    }
    
    /**
     * inits ArkaPong with parameter foronePlayer
     * @param isOnePlayer
     */
    public ArkaPong (boolean isOnePlayer,float speedBall) {
    	this.isOnePlayer = isOnePlayer;
    	this.speedBall = speedBall;
    }
    
	@Override
	public void create() {		
		Assets.load();
		
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();
		
		touchPoint = new Vector3();
		
		paddle1Control = new BoundingBox(new Vector3(0, 0, 0), new Vector3(2, 10, 0));
		paddle2Control = new BoundingBox(new Vector3(12, 0, 0), new Vector3(15, 10, 0));
		randomBlock = new BoundingBox(new Vector3(20, 0, 0), new Vector3(21, 1, 0));
		
	    camera = new OrthographicCamera(WIDTH, HEIGHT);
        camera.position.set(WIDTH / 2, HEIGHT / 2, 0);
        camera.update();
		batch = new SpriteBatch();
		
		// Font
		score1Font = new BitmapFont(Gdx.files.internal("data/square2.fnt"), Gdx.files.internal("data/square2.png"), false);
		score2Font = new BitmapFont(Gdx.files.internal("data/square2.fnt"), Gdx.files.internal("data/square2.png"), false);
		 
		ballTexture = new Texture(Gdx.files.internal("data/ball.png"));
		ballTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);

		dividerTexture = new Texture(Gdx.files.internal("data/separatorv.png"));

		paddleTexture = new Texture(Gdx.files.internal("data/paddle.png"));
		paddleTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
		world = new World(isOnePlayer,speedBall);

	}

	@Override
	public void dispose() {
		batch.dispose();
		ballTexture.dispose();
		paddleTexture.dispose();
	}

	@Override
	public void render() {		
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		world.getPaddle1().savePrevPosition();
		world.getPaddle2().savePrevPosition();
		
		// handle multitouch
		for (int i = 0; i < 20; i++) {
	        if (Gdx.input.isTouched(i) || Gdx.input.justTouched()){
	        	// If game was over, reset it
	        	if (world.isGameOver()) {
	        		world.reset();
	        	}
	            camera.unproject(touchPoint.set(Gdx.input.getX(i), Gdx.input.getY(i), 0));
	            if(paddle1Control.contains(touchPoint)){
	            	world.getPaddle1().move(touchPoint.y - 1);
	            }
	
	            if(!isOnePlayer && paddle2Control.contains(touchPoint)){
	            	world.getPaddle2().move(touchPoint.y - 1);
	            }
	            
	            if (null != world.getSpecialBrick() && randomBlock.contains(touchPoint)) {
	            	Gdx.app.log("Touched special Brick",world.getSpecialBrick().toString());
	    			world.specialBrickTouched();
	            }
	        }
		}
		
        if (!world.isGameOver()) {
        	world.update(Gdx.graphics.getDeltaTime());
        }
        playSounds();
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		
		batch.draw(dividerTexture,7f,0f,0.1f,10f);
		
		
		for (Ball ball : world.getBalls()) {
			batch.draw(ballTexture,ball.getPosition().x,ball.getPosition().y,0.5f,0.5f);
		}
		batch.draw(paddleTexture,world.getPaddle1().getPosition().x,world.getPaddle1().getPosition().y,0.5f,world.getPaddle1().getPaddleLength());
		batch.draw(paddleTexture,world.getPaddle2().getPosition().x,world.getPaddle2().getPosition().y,0.5f,world.getPaddle1().getPaddleLength());
		
		if (null != world.getSpecialBrick()) {
			setSpecialBrick(world.getSpecialBrick());
			batch.draw(specialBrickTexture,world.getSpecialBrick().getPosition().x,world.getSpecialBrick().getPosition().y,0.5f,1f);
		}
		
		// Paint score
		score1Font.setScale(0.09f);
        score1Font.draw(batch, ""+world.getPoints1(), 3f , 9.5f); 
		score2Font.setScale(0.09f);
        score2Font.draw(batch, ""+world.getPoints2(), 9f , 9.5f); 

        batch.end();
	}

	/**
	 * sets the special brick
	 * @param specialBrick
	 */
	private void setSpecialBrick(SpecialBrick specialBrick) {
		String[] colors = new String[]{"blue","green","red","white","yellow","orange","brown","pink"}; 
		
		specialBrickTexture = new Texture(Gdx.files.internal("data/"+colors[specialBrick.getBrickType()]+".png"));
		ballTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		randomBlock = new BoundingBox(specialBrick.getPosition(), new Vector3(specialBrick.getPosition().x+2, specialBrick.getPosition().y+1, 0));	
	}

	/**
	 * plays souns stored in world's sound vector
	 */
	private void playSounds() {
		for(String sound: world.getSoundsToPlay()) {
			Assets.getSoundByName(sound).play(1);
		}
		world.getSoundsToPlay().clear();
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
