package Game.Entities.DynamicEntities;
import Main.Handler;

import Resources.Images;

public class Star extends Item {
	 public Star(int x, int y, int width, int height, Handler handler) {
	        super(x, y, width, height, handler, Images.star);
	    }
	 
	 public void tick(){
	        if(!used) {
	            if (falling) {
	                y = (int) (y + velY);
	                velY = velY + gravityAcc;
	                checkFalling();
	            }
	        
	        }
	    }
}
