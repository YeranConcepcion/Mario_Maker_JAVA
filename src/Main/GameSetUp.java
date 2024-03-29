package Main;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;

import Display.DisplayScreen;
import Display.MultiPScreen;
import Display.UI.UIPointer;
import Game.Entities.DynamicEntities.Luigi;
import Game.Entities.DynamicEntities.Mario;
import Game.Entities.DynamicEntities.Player;
import Game.Entities.StaticEntities.BreakBlock;
import Game.GameStates.GameOverState;
import Game.GameStates.GameState;
import Game.GameStates.LuigiWinState;
import Game.GameStates.MarioWinState;
import Game.GameStates.MenuState;
import Game.GameStates.PauseState;
import Game.GameStates.State;
import Game.World.Map;
import Game.World.MapBuilder;
import Input.Camera;
import Input.KeyManager;
import Input.MouseManager;
import Input.SecondCamera;
import Resources.Images;
import Resources.MusicHandler;


/**
 * Created by AlexVR on 7/1/2018.
 */

public class GameSetUp implements Runnable {
	public DisplayScreen display;
	public MultiPScreen displayTwo;
	public String title;


	private boolean multiAc = false;
	private boolean running = false;
	private Thread thread;
	public static boolean threadB;

	private BufferStrategy bs;
	private Graphics g;

	private BufferStrategy bsl;
	private Graphics gl;

	public UIPointer pointer;
	// pointer for luigi
	public UIPointer pointerL;

	//Input
	public KeyManager keyManager;
	public MouseManager mouseManager;
	public MouseManager initialmouseManager;

	//Handler
	private Handler handler;

	//States
	public State gameState;
	public State menuState;
	public State pauseState;
	public State GameOver;
	public State MarioWins;
	public State LuigiWins;

	//Res.music
	private MusicHandler musicHandler;

	public GameSetUp(String title,Handler handler) {
		this.handler = handler;
		this.title = title;
		threadB=false;

		keyManager = new KeyManager();
		mouseManager = new MouseManager();
		initialmouseManager = mouseManager;
		musicHandler = new MusicHandler(handler);
		handler.setCamera(new Camera());
		handler.setCamera2(new SecondCamera());

	}

	private void init(){
		display = new DisplayScreen(title, handler.width, handler.height);
		display.getFrame().addKeyListener(keyManager);
		display.getFrame().addMouseListener(mouseManager);
		display.getFrame().addMouseMotionListener(mouseManager);
		display.getCanvas().addMouseListener(mouseManager);
		display.getCanvas().addMouseMotionListener(mouseManager);



		Images img = new Images();

		musicHandler.restartBackground();

		gameState = new GameState(handler);
		menuState = new MenuState(handler);
		pauseState = new PauseState(handler);
		GameOver = new GameOverState(handler);
		MarioWins = new MarioWinState(handler);
		LuigiWins = new LuigiWinState(handler);

		State.setState(menuState);

	}

	public void reStart(){
		gameState = new GameState(handler);
	}

	public synchronized void start(){
		if(running)
			return;
		running = true;
		//this runs the run method in this  class
		thread = new Thread(this);
		thread.start();
	}

	public void run(){

		//initiallizes everything in order to run without breaking
		init();

		int fps = 60;
		double timePerTick = 1000000000 / fps;
		double delta = 0;
		long now;
		long lastTime = System.nanoTime();
		long timer = 0;
		int ticks = 0;

		while(running){
			//makes sure the games runs smoothly at 60 FPS
			now = System.nanoTime();
			delta += (now - lastTime) / timePerTick;
			timer += now - lastTime;
			lastTime = now;

			if(delta >= 1){
				//re-renders and ticks the game around 60 times per second
				tick();
				render();
				ticks++;
				delta--;
			}
			if(timer >= 1000000000){
				ticks = 0;
				timer = 0;
			}
		}

		stop();

	}

	private void tick(){
		//checks for key types and manages them

		keyManager.tick();

		if(musicHandler.ended()){
			musicHandler.restartBackground();
		}

		//game states are the menus
		if(State.getState() != null)
			State.getState().tick();
		if (handler.isInMap()) {
			updateCamera();
		}


		if(	handler.multiOn) {
			displayTwo = new MultiPScreen(title, handler.width, handler.height);
			displayTwo.getFrame().addKeyListener(keyManager);
			displayTwo.getFrame().addMouseListener(mouseManager);
			displayTwo.getFrame().addMouseMotionListener(mouseManager);
			displayTwo.getCanvas().addMouseListener(mouseManager);
			displayTwo.getCanvas().addMouseMotionListener(mouseManager);
			handler.setMultiOn(false);
			handler.setMultiForLuigi(true);
			multiAc = true;

		}
		if (handler.isInMap()&& handler.multiForLuigi) {
			updateCamera2();
		}


	}

	private void updateCamera() {
		Player mario = handler.getMario();
		double marioVelocityX = mario.getVelX();
		double marioVelocityY = mario.getVelY();
		double shiftAmount = 0;
		double shiftAmountY = 0;

		if (marioVelocityX > 0 && mario.getX() - 2*(handler.getWidth()/3) > handler.getCamera().getX()) {
			shiftAmount = marioVelocityX;
		}
		if (marioVelocityX < 0 && mario.getX() +  2*(handler.getWidth()/3) < handler.getCamera().getX()+handler.width) {
			shiftAmount = marioVelocityX;
		}
		if (marioVelocityY > 0 && mario.getY() - 2*(handler.getHeight()/3) > handler.getCamera().getY()) {
			shiftAmountY = marioVelocityY;
		}
		if (marioVelocityX < 0 && mario.getY() +  2*(handler.getHeight()/3) < handler.getCamera().getY()+handler.height) {
			shiftAmountY = -marioVelocityY;
		}
		handler.getCamera().moveCam(shiftAmount,shiftAmountY);
	}

	private void updateCamera2() {
		Player luigi = handler.getLuigi();
		double marioVelocityX = luigi.getVelX();
		double marioVelocityY = luigi.getVelY();
		double shiftAmount = 0;
		double shiftAmountY = 0;

		if (marioVelocityX > 0 && luigi.getX() - 2*(handler.getWidth()/3) > handler.getCamera2().getX()) {
			shiftAmount = marioVelocityX;
		}
		if (marioVelocityX < 0 && luigi.getX() +  2*(handler.getWidth()/3) < handler.getCamera2().getX()+handler.width) {
			shiftAmount = marioVelocityX;
		}
		if (marioVelocityY > 0 && luigi.getY() - 2*(handler.getHeight()/3) > handler.getCamera2().getY()) {
			shiftAmountY = marioVelocityY;
		}
		if (marioVelocityX < 0 && luigi.getY() +  2*(handler.getHeight()/3) < handler.getCamera2().getY()+handler.height) {
			shiftAmountY = -marioVelocityY;
		}
		handler.getCamera2().moveCam(shiftAmount,shiftAmountY);
	}

	private void render(){
		bs = display.getCanvas().getBufferStrategy();
		if(bs == null){
			display.getCanvas().createBufferStrategy(3);
			return;
		}

		g = bs.getDrawGraphics();

		//Clear Screen
		g.clearRect(0, 0,  handler.width, handler.height);

		//Draw Here!
		Graphics2D g2 = (Graphics2D) g.create();

		if(State.getState() != null)
			State.getState().render(g);

		//End Drawing!
		bs.show();

		g.dispose();
		// by yeran to draw on the second screen//

		if(multiAc) {
			bsl = displayTwo.getCanvas().getBufferStrategy();

			if(bsl == null){
				displayTwo.getCanvas().createBufferStrategy(3);
				return;
			}


			gl = bsl.getDrawGraphics();

			//Clear Screen
			gl.clearRect(0, 0,  handler.width, handler.height);

			//Draw Here!
			g2 = (Graphics2D) g.create();



			if(State.getState() != null) {
				if(State.getState().equals(gameState)){                                                                    
					((GameState) gameState).renderL(gl);
				}
				else if(State.getState() != null && !(State.getState().equals(menuState)))
					State.getState().render(gl);
				}

			//End Drawing!
			bsl.show();

			gl.dispose();}
	}
	
	public Map getMap() {
		Map map = new Map(this.handler);
		Images.makeMap(0, MapBuilder.pixelMultiplier, 31, 200, map, this.handler);
		for(int i = 195; i < 200; i++) {
			map.addBlock(new BreakBlock(0, i*MapBuilder.pixelMultiplier, 48,48, this.handler));
			map.addBlock(new BreakBlock(30*MapBuilder.pixelMultiplier, i*MapBuilder.pixelMultiplier, 48,48, this.handler));
		}

		Mario mario = new Mario(24 * MapBuilder.pixelMultiplier, 196 * MapBuilder.pixelMultiplier, 48,48, this.handler);
		map.addEnemy(mario);


		// for luigi        
		if(multiAc) {
			Luigi luigi = new Luigi(24 * MapBuilder.pixelMultiplier, 196 * MapBuilder.pixelMultiplier, 48,48, this.handler);
			map.addEnemy(luigi);

		}
		map.addEnemy(pointer);
		threadB=true;
		return map;
	}

	public synchronized void stop(){
		if(!running)
			return;
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public KeyManager getKeyManager(){
		return keyManager;
	}

	public MusicHandler getMusicHandler() {
		return musicHandler;
	}


	public MouseManager getMouseManager(){
		return mouseManager;
	}

}

