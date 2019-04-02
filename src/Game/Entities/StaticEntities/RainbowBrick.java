package Game.Entities.StaticEntities;

import Main.Handler;

import Resources.Images;


public class RainbowBrick extends BaseStaticEntity {
	 public RainbowBrick(int x, int y, int width, int height, Handler handler) {
	        super(x, y, width, height,handler, Images.rainbowBlock);
	    }

}
