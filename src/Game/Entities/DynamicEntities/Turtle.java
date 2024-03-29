package Game.Entities.DynamicEntities;

import Main.Handler;

import Resources.Animation;
import Resources.Images;

import java.awt.*;

public class Turtle extends BaseDynamicEntity {

    public Animation anim;
    public Animation reverse;

    public Turtle(int x, int y, int width, int height, Handler handler) {
        super(x, y, width, height, handler, Images.turtle[0]);
        anim = new Animation(160,Images.turtle);
        reverse = new Animation(160,Images.turtleReverse);
    }

    public Animation getAnim() {
		return anim;
	}

	public void setAnim(Animation anim) {
		this.anim = anim;
	}

	public Animation getReverse() {
		return reverse;
	}

	public void setReverse(Animation reverse) {
		this.reverse = reverse;
	}

	@Override
    public void tick(){
        if(!ded && dedCounter==0) {
            super.tick();
            anim.tick();
            if (falling) {
                y = (int) (y + velY);
                velY = velY + gravityAcc;
                checkFalling();
            }
            checkHorizontal();
            move();
        }else if(ded&&dedCounter==0){
            y++;
            height--;
            setDimension(new Dimension(width,height));
            if (height==0){
                dedCounter=1;
                y=-10000;
            }
        }
    }

    @Override
    public void kill() {
        sprite = Images.turtleDies;
        ded=true;
    }
}
