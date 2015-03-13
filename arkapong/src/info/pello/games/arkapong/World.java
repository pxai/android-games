package info.pello.games.arkapong;



import java.util.Random;
// I forgot to remove this, It may be a clue... or a trap!!
import java.util.Vector;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.math.Vector3;

/**
 * Represents the world where the game takes place
 * @author Pello Altadill
 * @greetz for u
 */
public class World {
	private static final float SPECIAL_INTERVAL = 6f;
	private static final int TOTAL_SPECIAL_BRICKS = 8;
	private int maxPoints = 10;
	private Vector<Ball> balls;
	private Paddle paddle1;
	private Paddle paddle2;
	private float totalTime=0;
	private int points1 = 0;
	private int points2 = 0;
	private boolean gameOver;
	private SpecialBrick specialBrick;
	private Vector<String> availableBricks;
	private Vector<String> soundsToPlay;
	private Random random;
    private boolean isOnePlayer;
	private float speedBall;
	
	/**
	 * constructor
	 * @param player
	 */
	public World () {
		init();
		this.speedBall = Ball.DEFAULT_VELOCITY;
	}

	/**
	 * creates instance setting one or two player
	 * @param isOnePlayer
	 */
	public World(boolean isOnePlayer, float speedBall) {
	   	this.isOnePlayer = isOnePlayer;
	   	this.speedBall = speedBall;
	   	init();
	}
	
	/**
	 * 
	 */
	private void init() {
		random = new Random();
		balls = new Vector<Ball>();
		soundsToPlay = new Vector<String>();
		paddle1 = new Paddle(0,4);
		paddle2 = new Paddle(14,4);
		soundsToPlay.add("pong");
		reset();
	}


	/**
	 * reset the world
	 */
	public void reset () {
		gameOver = false;
		points1 = 0;
		points2 = 0;
		initAvailableBricks();
		spawnBall();
	}
	
	/**
	 * init available bricks
	 */
	private void initAvailableBricks() {
		availableBricks = new Vector<String>();
		
		for (int i = 0;i<TOTAL_SPECIAL_BRICKS;i++) {
			availableBricks.add(new Integer(i).toString());
		}
	}


	/**
	 * reset the world
	 */
	private void endPoint (int points1, int points2, int ballIndex) {
		this.points1 += points1;
		this.points2 += points2;
		paddle1.setActive(true);
		paddle2.setActive(true);
		balls.remove(ballIndex);
		initAvailableBricks();
		resetPaddles();
		if (this.points1 == maxPoints || this.points2 == maxPoints) {
			gameOver = true;
		} else if (balls.isEmpty()) {
			spawnBall();
		}
	}
	
	/**
	 * spawns a ball
	 */
	private void spawnBall() {
		// TODO Auto-generated method stub
		int pip = random.nextInt(2);
		if (points1!=0 || points2 != 0) { soundsToPlay.add("pip"+pip); }
		balls.add(new Ball(speedBall));
	}




	/**
	 * update position based adding direction to
	 * @param delta
	 */
	public void update(float delta){
		totalTime+=delta;
		Ball ball = null;
		
		for (int i = balls.size()-1; i>=0 ;i--) {
			ball = balls.get(i);
			ball.update(delta);
			
            //System.out.println("paddlezo : pel(" + ball.getPosition().x +","+ball.getPosition().y +") paddle (" + paddle1.getPosition().x +","+paddle1.getPosition().y +")");

            // Toca la paddle? ¡para arriba!
            if (ball.getPosition().y > paddle1.getPosition().y 
            		&& ball.getPosition().y <= paddle1.getPosition().y + paddle1.getPaddleLength()
            			&& ball.getPosition().x-0.5f < 0 && ball.getDirection().x < 0) {

                    // Alteramos la dirección
                    ball.getDirection().x = -ball.getDirection().x;
                    ball.getDirection().y = paddle1.getPrevDirection(ball.getDirection().y)*ball.getDirection().y;
                    //ball.getDirection().y = ball.getDirection().y;
            		soundsToPlay.add("pop"+random.nextInt(5));
                    System.out.println("paddlezo : pel(" + ball.getPosition().x +","+ball.getPosition().y +") paddle (" + paddle1.getPosition().x +","+paddle1.getPosition().y +")");

            } 
            
			  // Toca la paddle? ¡para arriba!
            if ( ball.getPosition().y > paddle2.getPosition().y 
            			&& ball.getPosition().y <= paddle2.getPosition().y + paddle2.getPaddleLength() 
            				&& ball.getPosition().x+0.5f > 14  && ball.getDirection().x > 0) {

                    // Alteramos la dirección
                    ball.getDirection().x = -ball.getDirection().x;
                    ball.getDirection().y = paddle2.getPrevDirection(ball.getDirection().y)*ball.getDirection().y;
                    //ball.getDirection().y = ball.getDirection().y;
            		soundsToPlay.add("pop"+random.nextInt(5));
                    System.out.println("paddlezo : pel(" + ball.getPosition().x +","+ball.getPosition().y +") paddle (" + paddle1.getPosition().x +","+paddle1.getPosition().y +")");

            } 
            
            // Toca el techo? ¡para abajo!
            if (ball.getPosition().y > 9) {
                    // Variamos el vector de dirección invirtendo la velocidad
                    ball.getDirection().y = -1;                 
                    System.out.println("Choque techo pel(" + ball.getPosition().x +","+ball.getPosition().y +") paddle (" + paddle1.getPosition().x +","+paddle1.getPosition().y +")");


            }
            
            // Tocamos el suelo?
            if (ball.getPosition().y < 0) {
                    // Variamos el vector de dirección invirtendo la velocidad
                    ball.getDirection().y = -ball.getDirection().y;
                    System.out.println("Choque suelo pel(" + ball.getPosition().x +","+ball.getPosition().y +") paddle (" + paddle1.getPosition().x +","+paddle1.getPosition().y +")");


            }

            
            // La ball cae por abajo!!
            if (ball.getPosition().x < 0) {
                    System.out.println("Cayó pel(" + ball.getPosition().x +","+ball.getPosition().y +") paddle (" + paddle1.getPosition().x +","+paddle1.getPosition().y +")");
                    // establecemos el final de la partida
            		soundsToPlay.add("fail");
                    endPoint(0,1, i);
            }

            // La ball cae por abajo!!
            if (ball.getPosition().x > 15) {
                    System.out.println("Cayó salió pel(" + ball.getPosition().x +","+ball.getPosition().y +") paddle (" + paddle1.getPosition().x +","+paddle1.getPosition().y +")");
                    // establecemos el final de la partida
            		soundsToPlay.add("fail");                    
                    endPoint(1,0,i);
            }

            // In case of onePlayer, move the second paddle
            // acording to ball and position
            if (isOnePlayer && (totalTime % 3 < 1) && ball.getDirection().x > 0) {
            	if (ball.getPosition().y > paddle2.getPosition().y) { 
            		paddle2.getPosition().y++;
            	} else {
            		paddle2.getPosition().y--;            		
            	}
            }

		}


		// From time to time...
		if(totalTime > SPECIAL_INTERVAL){
			System.out.println("Create SPECIAL BRICK");
			spawnSpecialBrick();
			totalTime=0;
		}

	}


	/**
	 * spawns a special Brick to create special effects
	 */
	private void spawnSpecialBrick() {
		// TODO Auto-generated method stub
		
		int brickType = random.nextInt(availableBricks.size());
		specialBrick = new SpecialBrick(Integer.parseInt(availableBricks.elementAt(brickType)));
		Vector3 position = new Vector3(random.nextInt(10)+2,random.nextInt(10),0);
		specialBrick.setPosition(position);
		System.out.println(availableBricks.toString());
	}


	/**
	 * doubleVelocity
	 * changes ball velocity
	 */
	public void doubleVelocity () {
		for (Ball ball: balls) {
			ball.setVelocity(ball.getVelocity() * 2);
		}
	}

	
	/**
	 * invertBalls
	 * invert ball direction i x and y
	 */
	public void invertBalls () {
		for (Ball ball: balls) {
			ball.getDirection().x = -ball.getDirection().x;
			ball.getDirection().y = -ball.getDirection().y;
		}
	}
	
	/**
	 * invertBalls
	 * invert ball direction in x and y
	 */
	public void invertBallY() {
		for (Ball ball: balls) {
			ball.getDirection().y = -ball.getDirection().y;
		}
	}
	/**
	 * tripleBall
	 * spawns to new balls
	 */
	public void tripleBall () {
		spawnBall();
		spawnBall();
	}

	/**
	 * reducePaddle
	 * reduces paddles width
	 */
	public void reducePaddle () {
		if (availableBricks.contains("4")) {
			paddle1.setPaddleLength(paddle1.getPaddleLength()/2);
			paddle2.setPaddleLength(paddle2.getPaddleLength()/2);
			availableBricks.remove(availableBricks.indexOf("4"));
		}
	}
	
	/**
	 * increasePaddle
	 * increases paddles width
	 */
	public void increasePaddle () {
		if (availableBricks.contains("0")) {
			paddle1.setPaddleLength(paddle1.getPaddleLength()*2);
			paddle2.setPaddleLength(paddle2.getPaddleLength()*2);
			availableBricks.remove(availableBricks.indexOf("0"));
		} 
	}
	
	/**
	 * increasePaddle
	 * increases paddles width
	 */
	public void minimizePaddle () {
		if (availableBricks.contains("6")) {
			paddle1.setPaddleLength(paddle1.DEFAULT_PADDLE_LENGTH/4);
			paddle2.setPaddleLength(paddle2.DEFAULT_PADDLE_LENGTH/4);
			availableBricks.remove(availableBricks.indexOf("6"));
		} 
	}
	/**
	 * resetPaddles
	 * resets paddles width
	 */
	public void resetPaddles () {
		paddle1.setDefaultPaddleLength();
		paddle2.setDefaultPaddleLength();
	}

	/**
	 * movePaddles
	 * Move paddles randomly
	 */
	public void movePaddles () {
		paddle1.move(random.nextInt(10));
		paddle2.move(random.nextInt(10));
	}
	
	/**
	 * @return the balls
	 */
	public Vector<Ball> getBalls() {
		return balls;
	}



	/**
	 * @param balls the balls to set
	 */
	public void setBalls(Vector<Ball> balls) {
		this.balls = balls;
	}



	/**
	 * @return the paddle1
	 */
	public Paddle getPaddle1() {
		return paddle1;
	}



	/**
	 * @param paddle1 the paddle1 to set
	 */
	public void setPaddle1(Paddle paddle1) {
		this.paddle1 = paddle1;
	}



	/**
	 * @return the paddle2
	 */
	public Paddle getPaddle2() {
		return paddle2;
	}



	/**
	 * @param paddle2 the paddle2 to set
	 */
	public void setPaddle2(Paddle paddle2) {
		this.paddle2 = paddle2;
	}



	/**
	 * @return the totalTime
	 */
	public float getTotalTime() {
		return totalTime;
	}



	/**
	 * @param totalTime the totalTime to set
	 */
	public void setTotalTime(float totalTime) {
		this.totalTime = totalTime;
	}


	/**
	 * @return the gameOver
	 */
	public boolean isGameOver() {
		return gameOver;
	}


	/**
	 * @param gameOver the gameOver to set
	 */
	public void setGameOver(boolean gameOver) {
		this.gameOver = gameOver;
	}


	/**
	 * @return the points1
	 */
	public int getPoints1() {
		return points1;
	}


	/**
	 * @return the poinst2
	 */
	public int getPoints2() {
		return points2;
	}
	
	/**
	 * increments points of player1
	 */
	public void incPoints1() {
		points1++;
	} 
	
	/**
	 * increments points of player2
	 */
	public void incPoints2() {
		points2++;
	}


	public SpecialBrick getSpecialBrick() {
		return specialBrick;
	}


	public void setSpecialBrick(SpecialBrick specialBrick) {
		this.specialBrick = specialBrick;
	}


	/**
	 * special brick was touched
	 * call method to change world behaviour
	 */
	public void specialBrickTouched() {
		switch (specialBrick.getBrickType()) {
			case 0:	
						soundsToPlay.add("blue");
						increasePaddle();
						break;		
			case 1:
						soundsToPlay.add("green");
						doubleVelocity();
						break;
			case 2:
						soundsToPlay.add("red");
						invertBalls();
						break;
						
			case 3:		
						soundsToPlay.add("white");
						tripleBall();
						break;
						
			case 4:								
						soundsToPlay.add("yellow");
						reducePaddle();
						break;
			case 5:								
						soundsToPlay.add("orange");
						invertBallY();
						break;
			case 6:								
						soundsToPlay.add("brown");
						minimizePaddle();
						break;
			case 7:								
						soundsToPlay.add("pink");
						movePaddles();
						break;
			default: 	break;
		}
		specialBrick = null;
		
	}
	
	/**
	 * returns sounds to be played
	 * @return
	 */
	public Vector<String> getSoundsToPlay () {
		return soundsToPlay;
	}
}
