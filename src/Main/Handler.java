package Main;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import Game.Entities.DynamicEntities.Luigi;
import Game.Entities.DynamicEntities.Mario;
import Game.World.Map;
import Input.Camera;
import Input.KeyManager;
import Input.MouseManager;
import Input.SecondCamera;


/**
 * Created by AlexVR on 7/1/2018.
 */

public class Handler {

    private static final GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
    public static final int DEFAULTWIDTH = gd.getDisplayMode().getWidth();
    public static final int DEFAULTHEIGHT = gd.getDisplayMode().getHeight();
    public boolean multiOn = false;
    public boolean multiForLuigi = false;
    

    public boolean isMultiForLuigi() {
		return multiForLuigi;
	}

	public void setMultiForLuigi(boolean multiForLuigi) {
		this.multiForLuigi = multiForLuigi;
	}

	public boolean isMultiOn() {
		return multiOn;
	}

	public void setMultiOn(boolean multiOn) {
		this.multiOn = multiOn;
	}

	int width,height;

    private GameSetUp game;
    private Mario mario;
    private Luigi luigi;
    private Map map;
    private boolean marioInMap =false;

    private Camera camera;
    private SecondCamera camera2;


    public Handler(){

        height=2*(DEFAULTHEIGHT/3)  ;
        width =height;

    }

    public int getWidth(){
        return width;
    }

    public int getHeight(){
        return height;
    }

    public GameSetUp getGame() {
        return game;
    }

    public void setGame(GameSetUp game) {
        this.game = game;
    }

    public KeyManager getKeyManager(){
        return game.getKeyManager();
    }

    public MouseManager getMouseManager(){
        return game.getMouseManager();
    }


    ///TO CHange
    public Mario getMario() {
        return mario;
    }

    public void setMario(Mario mario) {
        this.mario = mario;
    }
//for luigi
    public Luigi getLuigi() {
        return luigi;
    }

    public void setLuigi(Luigi luigi) {
        this.luigi = luigi;
    }


    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }

    public boolean isInMap() {
        return marioInMap;
    }

    public void setIsInMap(boolean is) {
        marioInMap = is;
    }

    public Camera getCamera() {
        return camera;
    }

    public void setCamera(Camera camera) {
        this.camera = camera;
    }
    
    public SecondCamera getCamera2() {
        return camera2;
    }

    public void setCamera2(SecondCamera camera) {
        this.camera2 = camera;
    }
}
