package Game.World;

import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

import Display.UI.UIListener;
import Display.UI.UIPointer;
import Game.Entities.DynamicEntities.BaseDynamicEntity;
import Game.Entities.DynamicEntities.Goomba;
import Game.Entities.DynamicEntities.Item;
import Game.Entities.DynamicEntities.Luigi;
import Game.Entities.DynamicEntities.Mario;
import Game.Entities.DynamicEntities.Star;
import Game.Entities.DynamicEntities.Turtle;
import Game.Entities.StaticEntities.BaseStaticEntity;
import Game.Entities.StaticEntities.BlackHole;
import Game.Entities.StaticEntities.BoundBlock;
import Game.Entities.StaticEntities.Wall;
import Game.GameStates.State;
import Main.Handler;
import Resources.Images;

public class Map {

	ArrayList<BaseStaticEntity> blocksOnMap;
	ArrayList<BaseDynamicEntity> enemiesOnMap;
	Handler handler;
	private double bottomBorder;
	private UIListener listener;
	private Background hand;
	private Random rand;
	private Wall walls;
	private int mapBackground;

	public Map(Handler handler) {
		this.handler=handler;
		this.rand = new Random();
		this.hand = new Background(this.handler);
		this.listener = new UIListener( this.handler);
		this.walls = new Wall(this.handler);
		this.blocksOnMap = new ArrayList<>();
		this.enemiesOnMap = new ArrayList<>();
		bottomBorder=handler.getHeight();
		this.mapBackground = this.rand.nextInt(6);
	}

	public void addBlock(BaseStaticEntity block){

		blocksOnMap.add(block);

	}

	public void addEnemy(BaseDynamicEntity entity){

		if(entity instanceof Mario){
			handler.setMario((Mario) entity);
			handler.getCamera().setX(handler.getMario().x- (MapBuilder.pixelMultiplier*6));
			handler.getCamera().setY(handler.getMario().y - (MapBuilder.pixelMultiplier*10));
			bottomBorder=handler.getHeight()+handler.getMario().y;
		}
		if(entity instanceof Luigi && handler.multiForLuigi){
			handler.setLuigi((Luigi) entity);
			handler.getCamera2().setX(handler.getLuigi().x- (MapBuilder.pixelMultiplier*6));
			handler.getCamera2().setY(handler.getLuigi().y - (MapBuilder.pixelMultiplier*10));
			bottomBorder=handler.getHeight()+handler.getLuigi().y;
		}
		else {
			enemiesOnMap.add(entity);
		}
	}

	public void drawMap(Graphics2D g2) {

		handler.setIsInMap(true);

		Point camLocation = new Point((int)handler.getCamera().getX(), (int)handler.getCamera().getY());
		g2.translate(-camLocation.x, -camLocation.y);
		g2.drawImage(Images.backgrounds2[this.mapBackground], camLocation.x, camLocation.y, this.handler.getWidth(), this.handler.getHeight(),null);

		for (BaseStaticEntity block:blocksOnMap) {
			g2.drawImage(block.sprite,block.x,block.y,block.width,block.height,null);
		}

		//by yeran to implement the game over state  

		for (BaseStaticEntity blocks: blocksOnMap) {
			if(blocks instanceof BoundBlock || blocks instanceof  BlackHole && !handler.multiForLuigi ) {
				if(blocks.getBounds().intersects(handler.getMario().getBounds()) ) {
					State.setState(handler.getGame().GameOver);
				}
			}
		}
		if(handler.multiForLuigi) {
			for (BaseStaticEntity blocks: blocksOnMap) {
				if(blocks instanceof BoundBlock || blocks instanceof  BlackHole) {
					if(blocks.getBounds().intersects(handler.getMario().getBounds())   ) {
						State.setState(handler.getGame().GameOver);
					}
				}
			}
		}

		for (BaseDynamicEntity entity:enemiesOnMap) {
			if(entity instanceof Item){
				if(!((Item)entity).used){
					g2.drawImage(entity.sprite, entity.x, entity.y, entity.width, entity.height, null);
				}
			}else if(entity instanceof Goomba && !entity.ded){
				g2.drawImage(((Goomba)entity).anim.getCurrentFrame(), entity.x, entity.y, entity.width, entity.height, null);
				//by yeran to invoke the game over state
				if(handler.multiForLuigi) {
					if(entity.getRightBounds().intersects(handler.getMario().getLeftBounds())) {
						State.setState(handler.getGame().GameOver);
					}
					else if(entity.getLeftBounds().intersects(handler.getMario().getRightBounds()) ) {
						State.setState(handler.getGame().GameOver);
					}}

				if(!handler.multiForLuigi) {
					if(entity.getRightBounds().intersects(handler.getMario().getLeftBounds()) ) {
						State.setState(handler.getGame().GameOver);
					}
					else if(entity.getLeftBounds().intersects(handler.getMario().getRightBounds()) ) {
						State.setState(handler.getGame().GameOver);
					}	
				}
			}
			//by yeran to choose the correct animation         
			else if(entity instanceof Turtle && !entity.ded){
				if(handler.multiForLuigi) {
					if(entity.getRightBounds().intersects(handler.getMario().getLeftBounds()) ) {
						State.setState(handler.getGame().GameOver);

					}}

				if(!handler.multiForLuigi) {
					if(entity.getRightBounds().intersects(handler.getMario().getLeftBounds()) ) {
						State.setState(handler.getGame().GameOver);
					}
				}
				if(handler.multiForLuigi) {
					if(entity.getLeftBounds().intersects(handler.getMario().getRightBounds())  ) {
						State.setState(handler.getGame().GameOver);
					}}

				if(!handler.multiForLuigi) {
					if(entity.getLeftBounds().intersects(handler.getMario().getRightBounds())) {
						State.setState(handler.getGame().GameOver);
					}
				}
				if(entity.getDirection().equals("Right")) {

					g2.drawImage(((Turtle)entity).reverse.getCurrentFrame(), entity.x, entity.y, entity.width, entity.height, null);}
				else {
					g2.drawImage(((Turtle)entity).anim.getCurrentFrame(), entity.x, entity.y, entity.width, entity.height, null);
				}
			}
			else if(entity instanceof UIPointer ){
				((UIPointer) entity).render(g2);
			}else {
				g2.drawImage(entity.sprite, entity.x, entity.y, entity.width, entity.height, null);
			}
		}
		handler.getMario().drawMario(g2);
		if(this.listener != null && MapBuilder.mapDone) {
			this.listener.render(g2);
			this.hand.render(g2);
			this.walls.render(g2);
		} 
		if(handler.multiForLuigi) {
			handler.getLuigi().drawmario(g2);
		}

	}
	public void drawMapforLuigi(Graphics2D g2) {

		handler.setIsInMap(true);
		Point camLocation2 = null;
		if(handler.multiForLuigi) {
			camLocation2 = new Point((int)handler.getCamera2().getX(), (int)handler.getCamera2().getY());
			g2.translate(-camLocation2.x, -camLocation2.y);
			g2.drawImage(Images.backgrounds2[this.mapBackground], camLocation2.x, camLocation2.y, this.handler.getWidth(), this.handler.getHeight(),null);
		}

		for (BaseStaticEntity block:blocksOnMap) {
			g2.drawImage(block.sprite,block.x,block.y,block.width,block.height,null);
		}

		// by yeran to implement the game over state  

	
		if(handler.multiForLuigi) {
			for (BaseStaticEntity blocks: blocksOnMap) {
				if(blocks instanceof BoundBlock || blocks instanceof  BlackHole) {
					if(blocks.getBounds().intersects(handler.getLuigi().getBounds())  ) {
						State.setState(handler.getGame().GameOver);
					}
				}
			}
		}

		for (BaseDynamicEntity entity:enemiesOnMap) {
			if(entity instanceof Item){

				if(!((Item)entity).used){
					g2.drawImage(entity.sprite, entity.x, entity.y, entity.width, entity.height, null);
				}
			}else if(entity instanceof Goomba && !entity.ded){
				g2.drawImage(((Goomba)entity).anim.getCurrentFrame(), entity.x, entity.y, entity.width, entity.height, null);
// by yeran to invoke the game over state
				if(handler.multiForLuigi) {
					if(entity.getRightBounds().intersects(handler.getLuigi().getLeftBounds())) {
						State.setState(handler.getGame().GameOver);
					}
					else if(entity.getLeftBounds().intersects(handler.getLuigi().getRightBounds())) {
						State.setState(handler.getGame().GameOver);
					}}

			

			}
			// by yeran to choose the correct animation         
			else if(entity instanceof Turtle && !entity.ded){
				if(handler.multiForLuigi) {
					if( entity.getRightBounds().intersects(handler.getLuigi().getLeftBounds()) ) {
						State.setState(handler.getGame().GameOver);
					}}

			
				if(handler.multiForLuigi) {
					if(entity.getLeftBounds().intersects(handler.getLuigi().getRightBounds()) ) {
						State.setState(handler.getGame().GameOver);
					}}
			
				if(entity.getDirection().equals("Right")) {

					g2.drawImage(((Turtle)entity).reverse.getCurrentFrame(), entity.x, entity.y, entity.width, entity.height, null);}
				else {
					g2.drawImage(((Turtle)entity).anim.getCurrentFrame(), entity.x, entity.y, entity.width, entity.height, null);
				}
			}
			else if(entity instanceof UIPointer ){
				((UIPointer) entity).render(g2);
			}else {
				g2.drawImage(entity.sprite, entity.x, entity.y, entity.width, entity.height, null);
			}
		}
		handler.getMario().drawMario(g2);
		if(this.listener != null && MapBuilder.mapDone) {
			this.listener.render(g2);
			this.hand.render(g2);
			this.walls.render(g2);
		} 
		if(handler.multiForLuigi) {
			handler.getLuigi().drawmario(g2);
			handler.getMario().drawMario(g2);

		}
		if(handler.multiForLuigi) {
			g2.translate(camLocation2.x, camLocation2.y);}

	}

	public ArrayList<BaseStaticEntity> getBlocksOnMap() {
		return blocksOnMap;
	}

	public ArrayList<BaseDynamicEntity> getEnemiesOnMap() {
		return enemiesOnMap;
	}

	public double getBottomBorder() {
		return bottomBorder;
	}

	public UIListener getListener() {
		return this.listener;
	}
	public Background getHand() {
		return this.hand;
	}
	public Wall getWalls() {
		return this.walls;
	}

	public void reset() {
	}
}
