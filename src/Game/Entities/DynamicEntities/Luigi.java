package Game.Entities.DynamicEntities;

import Main.Handler;

import Resources.Animation;
import Resources.Images;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

public class Luigi extends Player{

	private boolean hit = false;
	public boolean grabbed =false;

	public Luigi(int x, int y, int width, int height, Handler handler) {
		super(x, y, width, height, handler, Images.marioSmallWalkRight[0]
				,new Animation(175,Images.marioSmallWalkLeft)
				, new Animation(175,Images.marioSmallWalkRight)
				, new Animation(150,Images.marioBigWalkLeft)
				, new Animation(150,Images.marioBigWalkRight)
				, new Animation(115,Images.marioBigRunLeft)
				, new Animation(115,Images.marioBigRunRight));
		if(isBig){
			this.y-=8;
			this.height+=8;
			setDimension(new Dimension(width, this.height));
		}
	}

	@Override
	public void tick(){
	    if(!grabbed) {
            super.tick();
            if (!this.hit) {
                if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_SPACE) && !handler.getKeyManager().up && !handler.getKeyManager().down) {
                    this.jump();
                }

                if (handler.getKeyManager().right && !handler.getKeyManager().up && !handler.getKeyManager().down) {
                    if (handler.getKeyManager().runbutt) {
                        velX = 6;
                        running = true;
                    } else {
                        velX = 3;
                        running = false;
                    }
                    if (facing.equals("Left")) {
                        changeDirrection = true;
                    }
                    facing = "Right";
                    moving = true;
                } else if (handler.getKeyManager().left && !handler.getKeyManager().up && !handler.getKeyManager().down) {
                    if (handler.getKeyManager().runbutt) {
                        velX = -6;
                        running = true;
                    } else {
                        velX = -3;
                        running = false;
                    }
                    if (facing.equals("Right")) {
                        changeDirrection = true;
                    }
                    facing = "Left";
                    moving = true;
                } else {
                    velX = 0;
                    moving = false;
                }
                if (jumping && velY <= 0) {
                    jumping = false;
                    falling = true;
                } else if (jumping) {
                    velY = velY - gravityAcc;
                    y = (int) (y - velY);
                }

                if (falling) {
                    y = (int) (y + velY);
                    velY = velY + gravityAcc;
                }
                x += velX;
            } else {
                this.setX(this.getX() - 30);
                this.setY(this.getY() - 30);
            }
        }
	}

	public void drawmario(Graphics2D g2) {
		if(!grabbed) {
			if (!isBig) {
				if (handler.getKeyManager().up) {
					if (facing.equals("Left")) {
						g2.drawImage(Images.tint(Images.marioSmallJumpLeft[2], 0, 1, 0), x, y, width, height, null);
					} else {
						g2.drawImage(Images.tint(Images.marioSmallJumpRight[2], 0, 1, 0), x, y, width, height, null);
					}
				} else if (handler.getKeyManager().down) {
					if (facing.equals("Left")) {
						g2.drawImage(Images.tint(Images.marioSmallJumpLeft[3], 0, 1, 0), x, y, width, height, null);
					} else {
						g2.drawImage(Images.tint(Images.marioSmallJumpRight[3], 0, 1, 0), x, y, width, height, null);
					}
				} else if (!jumping && !falling) {
					if (facing.equals("Left") && moving) {
						g2.drawImage(playerSmallLeftAnimation.getCurrentFrame(), x, y, width, height, null);
					} else if (facing.equals("Right") && moving) {
						g2.drawImage(playerSmallRightAnimation.getCurrentFrame(), x, y, width, height, null);
					}
					if (facing.equals("Left") && !moving) {
						g2.drawImage(Images.tint(Images.marioSmallWalkLeft[0], 0, 1, 0), x, y, width, height, null);
					} else if (facing.equals("Right") && !moving) {
						g2.drawImage(Images.tint(Images.marioSmallWalkRight[0], 0, 1, 0), x, y, width, height, null);
					}
				} else {
					if (jumping) {
						if (facing.equals("Left")) {
							g2.drawImage(Images.tint(Images.marioSmallJumpLeft[0], 0, 1, 0), x, y, width, height, null);
						} else {
							g2.drawImage(Images.tint(Images.marioSmallJumpRight[0], 0, 1, 0), x, y, width, height, null);
						}

					} else {
						if (facing.equals("Left")) {
							g2.drawImage(Images.tint(Images.marioSmallJumpLeft[1], 0, 1, 0), x, y, width, height, null);
						} else {
							g2.drawImage(Images.tint(Images.marioSmallJumpRight[1], 0, 1, 0), x, y, width, height, null);
						}
					}
				}
			} else {
				if (!changeDirrection) {
					if (handler.getKeyManager().up) {
						if (facing.equals("Left")) {
							g2.drawImage(Images.tint(Images.marioBigJumpLeft[4], 0, 1, 0), x, y, width, height, null);
						} else {
							g2.drawImage(Images.tint(Images.marioBigJumpRight[4], 0, 1, 0), x, y, width, height, null);
						}
					} else if (handler.getKeyManager().down) {
						if (facing.equals("Left")) {
							g2.drawImage(Images.tint(Images.marioBigJumpLeft[3], 0, 1, 0), x, y, width, height, null);
						} else {
							g2.drawImage(Images.tint(Images.marioBigJumpRight[3], 0, 1, 0), x, y, width, height, null);
						}
					} else if (!jumping && !falling) {
						if (facing.equals("Left") && moving && running) {
							g2.drawImage(playerBigLeftRunAnimation.getCurrentFrame(), x, y, width, height, null);
						} else if (facing.equals("Left") && moving && !running) {
							g2.drawImage(playerBigLeftWalkAnimation.getCurrentFrame(), x, y, width, height, null);
						} else if (facing.equals("Left") && !moving) {
							g2.drawImage(Images.tint(Images.marioBigWalkLeft[0], 0, 1, 0), x, y, width, height, null);
						} else if (facing.equals("Right") && moving && running) {
							g2.drawImage(playerBigRightRunAnimation.getCurrentFrame(), x, y, width, height, null);
						} else if (facing.equals("Right") && moving && !running) {
							g2.drawImage(playerBigRightWalkAnimation.getCurrentFrame(), x, y, width, height, null);
						} else if (facing.equals("Right") && !moving) {
							g2.drawImage(Images.tint(Images.marioBigWalkRight[0], 0, 1, 0), x, y, width, height, null);
						}
					} else {
						if (jumping) {
							if (facing.equals("Left")) {
								g2.drawImage(Images.tint(Images.marioBigJumpLeft[0], 0, 1, 0), x, y, width, height, null);
							} else {
								g2.drawImage(Images.tint(Images.marioBigJumpRight[0], 0, 1, 0), x, y, width, height, null);
							}

						} else {
							if (facing.equals("Left")) {
								g2.drawImage(Images.tint(Images.marioBigJumpLeft[1], 0, 1, 0), x, y, width, height, null);
							} else {
								g2.drawImage(Images.tint(Images.marioBigJumpRight[1], 0, 1, 0), x, y, width, height, null);
							}
						}
					}
				} else {
					if (!running) {
						changeDirrection = false;
						changeDirectionCounter = 0;
						drawmario(g2);
					}
					if (facing.equals("Right")) {
						g2.drawImage(Images.tint(Images.marioBigJumpRight[4], 0, 1, 0), x, y, width, height, null);
					} else {
						g2.drawImage(Images.tint(Images.marioBigJumpLeft[4], 0, 1, 0), x, y, width, height, null);
					}
				}
			}
		}
	}
	public boolean getHit() {
		return this.hit;
	}
	public void setHit(Boolean hit) {
		this.hit = hit;
	}
}