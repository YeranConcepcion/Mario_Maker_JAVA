package Game.Entities.DynamicEntities;

import Main.Handler;


import Resources.Images;

public class Coin extends Item {

    public Coin(int x, int y, int width, int height, Handler handler) {
        super(x, y, width, height, handler, Images.coin);
    }

    @Override
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
