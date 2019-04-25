package Game.GameStates;

import Main.Handler;

import Resources.Images;
import Display.UI.UIManager;
import Display.UI.UIStringButton;

import java.awt.*;
import java.awt.event.KeyEvent;
public class LuigiWinState extends State{

	   private UIManager uiManager;

	    public LuigiWinState(Handler handler) {
	        super(handler);
	        uiManager = new UIManager(handler);
	        handler.getMouseManager().setUimanager(uiManager);
	       

	        uiManager.addObjects(new UIStringButton(56, 223+(64+16), 128, 64, "Options", () -> {
	            handler.getMouseManager().setUimanager(null);
	            handler.setIsInMap(false);
	            State.setState(handler.getGame().menuState);
	        },handler,Color.WHITE));

	        uiManager.addObjects(new UIStringButton(56, (223+(64+16))+(64+16), 128, 64, "Title", () -> {
	            handler.getMouseManager().setUimanager(null);
	            handler.setIsInMap(false);
	            State.setState(handler.getGame().menuState);
	        },handler,Color.WHITE));

	    }

	    @Override
	    public void tick() {
	        handler.getMouseManager().setUimanager(uiManager);
	        uiManager.tick();
	      
	    }

	    @Override
	    public void render(Graphics g) {
	        g.drawImage(Images.Lwins,0,0,handler.getWidth(),handler.getHeight(),null);
	        uiManager.Render(g);
	    }
}
