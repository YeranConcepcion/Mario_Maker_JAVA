package Resources;

import javax.imageio.ImageIO;
import javax.swing.*;

import Game.Entities.StaticEntities.BreakBlock;
import Game.World.Map;
import Game.World.MapBuilder;
import Main.Handler;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Created by AlexVR on 7/1/2018.
 */
public class Images {


    public static BufferedImage[] butstart;
    public static BufferedImage[] backgrounds;
    public static BufferedImage[] backgrounds2;

    public static BufferedImage[] marioSmallWalkLeft;
    public static BufferedImage[] marioSmallWalkRight;
    public static BufferedImage[] marioSmallJumpLeft;//also store ide sprites like looking up or down
    public static BufferedImage[] marioSmallJumpRight;

    public static BufferedImage[] marioBigWalkLeft;
    public static BufferedImage[] marioBigWalkRight;
    public static BufferedImage[] marioBigRunLeft;
    public static BufferedImage[] marioBigRunRight;
    public static BufferedImage[] marioBigJumpLeft;//also store ide sprites like looking up or down and left<->right transition
    public static BufferedImage[] marioBigJumpRight;
    
    public static BufferedImage[] item;
    public static BufferedImage[] enemy;
    public static BufferedImage[] hitWall;
    public static BufferedImage[] enemyFG;
    public static BufferedImage[] enemyGB1;
    public static BufferedImage[] enemyGB2;
    public static BufferedImage[] enemyGB3;
    public static BufferedImage enemyBL;
    public static BufferedImage enemyPS;
    public static BufferedImage enemyHT;
    public static BufferedImage enemySmash;


    public static BufferedImage[] goomba;
    public static BufferedImage[] turtle;
    public static BufferedImage[] turtleReverse;
    public static BufferedImage title;
    public static BufferedImage Pause;
  // to make the game over state
    public static BufferedImage GameOver;
    public static BufferedImage Lwins;
    public static BufferedImage Mwins;
    
    public static BufferedImage Cursor;

    public static BufferedImage testMap;
    public static BufferedImage testMaptwo;
    
    public static BufferedImage starMap;
    
    public static BufferedImage HelloMap;
    
 
 // by yeran to add the new blocks
    public static BufferedImage rainbowBlock;
    public static BufferedImage blackHoleBlock;

    public static BufferedImage breakBlock;
    public static BufferedImage misteryBlock;
    public static BufferedImage surfaceBlock;
    public static BufferedImage boundBlock;
    public static BufferedImage mushroom;
    public static BufferedImage star;
    public static BufferedImage coin;
    public static BufferedImage goombaDies;
    public static BufferedImage turtleDies;
   

    private SpriteSheet mainmenuSpriteSheet;
    private SpriteSheet backgroundSpriteSheet;
    private SpriteSheet interactableSpriteSheet;
    private SpriteSheet playerSpriteSheet;
    private SpriteSheet blockSpriteSheet;
    private SpriteSheet goombaSpriteSheet;
   // to create the new enemy
    private SpriteSheet turtleSpriteSheet;
    private SpriteSheet SSpriteSheet;
    private SpriteSheet SAttackSpriteSheet;
	public static BufferedImage starRaceMap;

    public Images() {

        butstart = new BufferedImage[3];

        backgrounds = new BufferedImage[9];
        backgrounds2 = new BufferedImage[6];

        marioSmallWalkLeft = new BufferedImage[2];
        marioSmallWalkRight = new BufferedImage[2];
        marioSmallJumpLeft = new BufferedImage[4];
        marioSmallJumpRight = new BufferedImage[4];
        item = new BufferedImage[19];
        enemy = new BufferedImage[9];
        hitWall = new BufferedImage[44];
        enemyFG = new BufferedImage[9];
        enemyGB1 = new BufferedImage[3];
        enemyGB2 = new BufferedImage[2];
        enemyGB3 = new BufferedImage[3];


        marioBigWalkLeft = new BufferedImage[2];
        marioBigWalkRight = new BufferedImage[2];
        marioBigRunLeft = new BufferedImage[2];
        marioBigRunRight = new BufferedImage[2];
        marioBigJumpLeft = new BufferedImage[5];
        marioBigJumpRight = new BufferedImage[5];
        goomba = new BufferedImage[2];
        turtle = new BufferedImage[4];
        turtleReverse = new BufferedImage[4];



        try {


            //spriteSheets
            mainmenuSpriteSheet = new SpriteSheet(ImageIO.read(getClass().getResourceAsStream("/Sheets/mainmenuSheet.png")));
            backgroundSpriteSheet = new SpriteSheet(ImageIO.read(getClass().getResourceAsStream("/Sheets/backgroundSheet.png")));
            interactableSpriteSheet = new SpriteSheet(ImageIO.read(getClass().getResourceAsStream("/Sheets/interactablesSheet.png")));
            playerSpriteSheet = new SpriteSheet(ImageIO.read(getClass().getResourceAsStream("/Sheets/marioSNESSheet.png")));
            blockSpriteSheet = new SpriteSheet(ImageIO.read(getClass().getResourceAsStream("/Sheets/blocksSheet.png")));
            goombaSpriteSheet = new SpriteSheet(ImageIO.read(getClass().getResourceAsStream("/Sheets/goombaSprite.png")));
            turtleSpriteSheet = new SpriteSheet(ImageIO.read(getClass().getResourceAsStream("/Sheets/turtles.png")));
            SSpriteSheet = new SpriteSheet(ImageIO.read(getClass().getResourceAsStream("/Sheets/Sheets.png")));
            SAttackSpriteSheet = new SpriteSheet(ImageIO.read(getClass().getResourceAsStream("/Sheets/enemySheet2.png")));


            //Images
            title = mainmenuSpriteSheet.crop(16,16,256,224);
            Pause = ImageIO.read(getClass().getResourceAsStream("/Sheets/Pause.png"));
            GameOver = ImageIO.read(getClass().getResourceAsStream("/Sheets/gameOverMario.jpg"));
            Lwins = ImageIO.read(getClass().getResourceAsStream("/Sheets/LugiWinBK.png"));
            Mwins = ImageIO.read(getClass().getResourceAsStream("/Sheets/MarioWinBK.jpg"));
            
            Cursor = ImageIO.read(getClass().getResourceAsStream("/Sheets/cursor.png"));
            butstart[0]= ImageIO.read(getClass().getResourceAsStream("/Buttons/NormBut.png"));//normbut
            butstart[1]= ImageIO.read(getClass().getResourceAsStream("/Buttons/HoverBut.png"));//hoverbut
            butstart[2]= ImageIO.read(getClass().getResourceAsStream("/Buttons/ClickedBut.png"));//clickbut

            backgrounds[0] = backgroundSpriteSheet.crop(2,2,512,432);
            backgrounds[1] = backgroundSpriteSheet.crop(516,2,512,432);
            backgrounds[2] = backgroundSpriteSheet.crop(2,438,512,432);
            backgrounds[3] = backgroundSpriteSheet.crop(516,438,512,432);
            backgrounds[4] = backgroundSpriteSheet.crop(2,872,512,432);
            backgrounds[5] = backgroundSpriteSheet.crop(516,872,512,432);
            backgrounds[6] = backgroundSpriteSheet.crop(2,1306,512,432);
            backgrounds[7] = backgroundSpriteSheet.crop(516,1306,512,432);
            backgrounds[8] = backgroundSpriteSheet.crop(2,1740,512,432);
            
            backgrounds2[0] = ImageIO.read(getClass().getResourceAsStream("/Sheets/GrasslandBackground.png"));
            backgrounds2[1] = ImageIO.read(getClass().getResourceAsStream("/Sheets/DarkBackground.png"));
            backgrounds2[2] = ImageIO.read(getClass().getResourceAsStream("/Sheets/CanyonBackground.png"));
            backgrounds2[3] = ImageIO.read(getClass().getResourceAsStream("/Sheets/DesertBackground.png"));
            backgrounds2[4] = ImageIO.read(getClass().getResourceAsStream("/Sheets/Snow1Background.png"));
            backgrounds2[5] = ImageIO.read(getClass().getResourceAsStream("/Sheets/Snow2Background.png"));


            //player sprites
            //Small
            marioSmallWalkLeft[0] = playerSpriteSheet.crop(169,0,14,20);
            marioSmallWalkLeft[1] = playerSpriteSheet.crop(49,0,15,19);

            marioSmallWalkRight[0] = playerSpriteSheet.crop(209,0,14,20);
            marioSmallWalkRight[1] = playerSpriteSheet.crop(328,0,15,19);

            marioSmallJumpLeft[0] = playerSpriteSheet.crop(168,39,16,22);
            marioSmallJumpLeft[1] = playerSpriteSheet.crop(128,40,16,20);
            marioSmallJumpLeft[2] = playerSpriteSheet.crop(49,39,15,21);//up
            marioSmallJumpLeft[3] = playerSpriteSheet.crop(89,43,15,14);//down

            marioSmallJumpRight[0] = playerSpriteSheet.crop(208,39,16,22);
            marioSmallJumpRight[1] = playerSpriteSheet.crop(248,40,16,20);
            marioSmallJumpRight[2] = playerSpriteSheet.crop(328,39,15,21);//up
            marioSmallJumpRight[3] = playerSpriteSheet.crop(288,43,15,14);//down

            // Big
            marioBigWalkLeft[0] = playerSpriteSheet.crop(169,76,15,28);
            marioBigWalkLeft[1] = playerSpriteSheet.crop(8,76,16,28);

            marioBigWalkRight[0] = playerSpriteSheet.crop(208,76,15,28);
            marioBigWalkRight[1] = playerSpriteSheet.crop(368,76,16,28);

            marioBigRunLeft[0] = playerSpriteSheet.crop(169,76,15,28);
            marioBigRunLeft[1] = playerSpriteSheet.crop(48,76,16,27);

            marioBigRunRight[0] = playerSpriteSheet.crop(208,76,15,28);
            marioBigRunRight[1] = playerSpriteSheet.crop(328,76,16,27);

            marioBigJumpLeft[0] = playerSpriteSheet.crop(168,114,16,31);
            marioBigJumpLeft[1] = playerSpriteSheet.crop(128,115,16,29);
            marioBigJumpLeft[2] = playerSpriteSheet.crop(129,196,15,27);//up
            marioBigJumpLeft[3] = playerSpriteSheet.crop(88,122,16,15);//down
            marioBigJumpLeft[4] = playerSpriteSheet.crop(8,115,16,29);//change

            marioBigJumpRight[0] = playerSpriteSheet.crop(208,114,16,31);
            marioBigJumpRight[1] = playerSpriteSheet.crop(248,115,16,29);
            marioBigJumpRight[2] = playerSpriteSheet.crop(248,196,15,27);//up
            marioBigJumpRight[3] = playerSpriteSheet.crop(287,122,16,15);//down
            marioBigJumpRight[4] = playerSpriteSheet.crop(368,115,16,29);//Change
            
            item[0] =  ImageIO.read(getClass().getResourceAsStream("/Sheets/item/SL1.png"));
            item[1] =  ImageIO.read(getClass().getResourceAsStream("/Sheets/item/SL2.png"));
            item[2] =  ImageIO.read(getClass().getResourceAsStream("/Sheets/item/SL3.png"));
            item[3] =  ImageIO.read(getClass().getResourceAsStream("/Sheets/item/SL4.png"));
            item[4] =  ImageIO.read(getClass().getResourceAsStream("/Sheets/item/SL5.png"));
            item[5] =  ImageIO.read(getClass().getResourceAsStream("/Sheets/item/SL6.png"));
            item[6] =  ImageIO.read(getClass().getResourceAsStream("/Sheets/item/SL7.png"));
            item[7] =  ImageIO.read(getClass().getResourceAsStream("/Sheets/item/SL8.png"));
            item[8] =  ImageIO.read(getClass().getResourceAsStream("/Sheets/item/SL9.png"));
            item[9] =  ImageIO.read(getClass().getResourceAsStream("/Sheets/item/SL10.png"));
            item[10] =  item[9];
            item[11] =  item[8];
            item[12] =  item[7];
            item[13] =  item[6];
            item[14] =  item[5];
            item[15] =  item[4];
            item[16] =  item[3];
            item[17] =  item[2];
            item[18] =  item[1];
          
            hitWall[0] = ImageIO.read(getClass().getResourceAsStream("/Sheets/KO/KO1.png"));
            hitWall[1] = ImageIO.read(getClass().getResourceAsStream("/Sheets/KO/KO2.png"));
            hitWall[2] = ImageIO.read(getClass().getResourceAsStream("/Sheets/KO/KO3.png"));
            hitWall[3] = ImageIO.read(getClass().getResourceAsStream("/Sheets/KO/KO4.png"));
            hitWall[4] = ImageIO.read(getClass().getResourceAsStream("/Sheets/KO/KO5.png"));
            hitWall[5] = ImageIO.read(getClass().getResourceAsStream("/Sheets/KO/KO6.png"));
            hitWall[6] = ImageIO.read(getClass().getResourceAsStream("/Sheets/KO/KO7.png"));
            hitWall[7] = ImageIO.read(getClass().getResourceAsStream("/Sheets/KO/KO8.png"));
            hitWall[8] = ImageIO.read(getClass().getResourceAsStream("/Sheets/KO/KO9.png"));
            hitWall[9] = ImageIO.read(getClass().getResourceAsStream("/Sheets/KO/KO10.png"));
            hitWall[10] = ImageIO.read(getClass().getResourceAsStream("/Sheets/KO/KO11.png"));
            hitWall[11] = ImageIO.read(getClass().getResourceAsStream("/Sheets/KO/KO12.png"));
            hitWall[12] = ImageIO.read(getClass().getResourceAsStream("/Sheets/KO/KO13.png"));
            hitWall[13] = ImageIO.read(getClass().getResourceAsStream("/Sheets/KO/KO14.png"));
            hitWall[14] = ImageIO.read(getClass().getResourceAsStream("/Sheets/KO/KO15.png"));
            hitWall[15] = ImageIO.read(getClass().getResourceAsStream("/Sheets/KO/KO17.png"));
            hitWall[16] = ImageIO.read(getClass().getResourceAsStream("/Sheets/KO/KO18.png"));
            hitWall[17] = ImageIO.read(getClass().getResourceAsStream("/Sheets/KO/KO19.png"));
            hitWall[18] = ImageIO.read(getClass().getResourceAsStream("/Sheets/KO/KO20.png"));
            hitWall[19] = ImageIO.read(getClass().getResourceAsStream("/Sheets/KO/KO21.png"));
            hitWall[20] = ImageIO.read(getClass().getResourceAsStream("/Sheets/KO/KO22.png"));
            hitWall[21] = ImageIO.read(getClass().getResourceAsStream("/Sheets/KO/KO23.png"));
            hitWall[22] = ImageIO.read(getClass().getResourceAsStream("/Sheets/KO/KO24.png"));
            hitWall[23] = ImageIO.read(getClass().getResourceAsStream("/Sheets/KO/KO25.png"));
            hitWall[24] = ImageIO.read(getClass().getResourceAsStream("/Sheets/KO/KO26.png"));
            hitWall[25] = ImageIO.read(getClass().getResourceAsStream("/Sheets/KO/KO27.png"));
            hitWall[26] = ImageIO.read(getClass().getResourceAsStream("/Sheets/KO/KO28.png"));
            hitWall[27] = ImageIO.read(getClass().getResourceAsStream("/Sheets/KO/KO29.png"));
            hitWall[28] = ImageIO.read(getClass().getResourceAsStream("/Sheets/KO/KO30.png"));
            hitWall[29] = ImageIO.read(getClass().getResourceAsStream("/Sheets/KO/KO31.png"));
            hitWall[30] = ImageIO.read(getClass().getResourceAsStream("/Sheets/KO/KO32.png"));
            hitWall[31] = ImageIO.read(getClass().getResourceAsStream("/Sheets/KO/KO33.png"));
            hitWall[32] = ImageIO.read(getClass().getResourceAsStream("/Sheets/KO/KO35.png"));
            hitWall[33] = ImageIO.read(getClass().getResourceAsStream("/Sheets/KO/KO36.png"));
            hitWall[34] = ImageIO.read(getClass().getResourceAsStream("/Sheets/KO/KO37.png"));
            hitWall[35] = ImageIO.read(getClass().getResourceAsStream("/Sheets/KO/KO38.png"));
            hitWall[36] = ImageIO.read(getClass().getResourceAsStream("/Sheets/KO/KO39.png"));
            hitWall[37] = ImageIO.read(getClass().getResourceAsStream("/Sheets/KO/KO40.png"));
            hitWall[38] = ImageIO.read(getClass().getResourceAsStream("/Sheets/KO/KO41.png"));
            hitWall[39] = ImageIO.read(getClass().getResourceAsStream("/Sheets/KO/KO42.png"));
            hitWall[40] = ImageIO.read(getClass().getResourceAsStream("/Sheets/KO/KO43.png"));
            hitWall[41] = ImageIO.read(getClass().getResourceAsStream("/Sheets/KO/KO44.png"));
            hitWall[42] = ImageIO.read(getClass().getResourceAsStream("/Sheets/KO/KO45.png"));
            hitWall[43] = ImageIO.read(getClass().getResourceAsStream("/Sheets/KO/KO46.png"));

            enemy[0] = SSpriteSheet.crop(8,14,64,45);
            enemy[1] = SSpriteSheet.crop(88,21,60,38);
            enemy[2] = SSpriteSheet.crop(165,25,55,37);
            enemy[3] = SSpriteSheet.crop(242,28,50,35);
            enemy[4] = SSpriteSheet.crop(315,28,48,34);
            enemy[5] = SSpriteSheet.crop(381,27,57,35);
            enemy[6] = SSpriteSheet.crop(454,25,61,35);
            enemy[7] = SSpriteSheet.crop(525,19,62,37);
            enemy[8] = SSpriteSheet.crop(75,89,51,41);

            enemyFG[0] = SAttackSpriteSheet.crop(3,115,66,45);
            enemyFG[1] = SAttackSpriteSheet.crop(72,103,71,55);
            enemyFG[2] =  SAttackSpriteSheet.crop(149,101,64,62);
            enemyFG[3] =  SAttackSpriteSheet.crop(215,102,80,62);
            enemyFG[4] =  SAttackSpriteSheet.crop(5,167,79,62);
            enemyFG[5] =  SAttackSpriteSheet.crop(90,167,93,62);
            enemyFG[6] = SAttackSpriteSheet.crop(188,167,99,72);
            enemyFG[7] =  enemyFG[1];
            enemyFG[8] =  enemyFG[0];

            enemyGB1[0] = SAttackSpriteSheet.crop(3,248,53,48);
            enemyGB1[1] = SAttackSpriteSheet.crop(68,247,61,46);
            enemyGB1[2] =  SAttackSpriteSheet.crop(147,252,48,40);


            enemyGB2[0] = SAttackSpriteSheet.crop(205,250,34,41);
            enemyGB2[1] = SAttackSpriteSheet.crop(252,251,45,44);


            enemyGB3[0] = enemyGB1[2];
            enemyGB3[1] = enemyGB1[1];
            enemyGB3[2] =  enemyGB1[0];

            enemyPS= SAttackSpriteSheet.crop(68,358,29,64);
            enemyHT= SAttackSpriteSheet.crop(178,357,52,62);
            enemySmash= SAttackSpriteSheet.crop(9,302,51,41);
            enemyBL= SAttackSpriteSheet.crop(188,184,41,55);

            //maps
            testMap = ImageIO.read(getClass().getResourceAsStream("/maps/testmap1.png"));
            testMaptwo = ImageIO.read(getClass().getResourceAsStream("/maps/testmap2.png"));
            starMap = ImageIO.read(getClass().getResourceAsStream("/maps/StarRace.png"));
            HelloMap = ImageIO.read(getClass().getResourceAsStream("/maps/Hello World.png"));
            starRaceMap = ImageIO.read(getClass().getResourceAsStream("/maps/StarRace.png"));
            //blocks
            boundBlock = interactableSpriteSheet.crop(12,73,16,16);
            misteryBlock = interactableSpriteSheet.crop(32,93,16,16);
            surfaceBlock = interactableSpriteSheet.crop(112,93,16,16);
            
            //breakBlock = blockSpriteSheet.crop(272,112,16,16);
            breakBlock = ImageIO.read(getClass().getResourceAsStream("/Sheets/brick.png"));
            rainbowBlock = ImageIO.read(getClass().getResourceAsStream("/Sheets/rainbow.png"));
            blackHoleBlock = ImageIO.read(getClass().getResourceAsStream("/Sheets/blackhole.png"));

            //items
            mushroom = interactableSpriteSheet.crop(112,34,16,16);
            star = ImageIO.read(getClass().getResourceAsStream("/Sheets/star.png"));
            coin = ImageIO.read(getClass().getResourceAsStream("/Sheets/coin.png"));

            //enemy
            goomba[0]=goombaSpriteSheet.crop(119,40,162,162);
            goomba[1]= goombaSpriteSheet.crop(329,40,162,162);
            goombaDies=goombaSpriteSheet.crop(539,100,162,81);
            
            // turtle // remember do the animation of it when it dies
//            turtle[0]=turtleSpriteSheet.crop(x, y, width, height)
            
            turtle[0]=turtleSpriteSheet.crop(107, 4, 16, 24);//walking to the left
            
            turtle[1]=turtleSpriteSheet.crop(17, 4, 16, 24);//walking to the left
            
            turtle[2]=turtleSpriteSheet.crop(47, 4, 16, 24);//walking to the right
            
            turtle[3]=turtleSpriteSheet.crop(17, 4, 16, 24);//walking to the right
            
            turtleDies = turtleSpriteSheet.crop(287, 9, 14, 24); // when mario jump on it
            
            // reverse animation
            
            turtleReverse[0]=turtleSpriteSheet.crop(137, 4, 16, 24);//walking to the left
            turtleReverse[1]=turtleSpriteSheet.crop(227, 4, 16, 24);//walking to the left
            turtleReverse[2]=turtleSpriteSheet.crop(197, 4, 16, 24);//walking to the left
            turtleReverse[3]=turtleSpriteSheet.crop(227, 4, 16, 24);//walking to the left


        }catch (IOException e) {
        e.printStackTrace();
    }


    }

    public static BufferedImage loadImage(String path) {
        try {
            return ImageIO.read(Images.class.getResourceAsStream(path));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        return null;
    }   
    public static  void makeMap(int i, int j, int k, int z,  Map map, Handler h) {
    	for(int x = i; x < k; x++) {
    		map.addBlock(new BreakBlock( x * j, z * j, j, j, h));
    	}	
    }

    public static BufferedImage tint(BufferedImage src, float r, float g, float b) {

        // Copy image ( who made that so complicated :< )
        BufferedImage newImage = new BufferedImage(src.getWidth(), src.getHeight(), BufferedImage.TRANSLUCENT);
        Graphics2D graphics = newImage.createGraphics();
        graphics.drawImage(src, 0, 0, null);
        graphics.dispose();

        // Color image
        for (int i = 0; i < newImage.getWidth(); i++) {
            for (int j = 0; j < newImage.getHeight(); j++) {
                int ax = newImage.getColorModel().getAlpha(newImage.getRaster().getDataElements(i, j, null));
                int rx = newImage.getColorModel().getRed(newImage.getRaster().getDataElements(i, j, null));
                int gx = newImage.getColorModel().getGreen(newImage.getRaster().getDataElements(i, j, null));
                int bx = newImage.getColorModel().getBlue(newImage.getRaster().getDataElements(i, j, null));
                rx *= r;
                gx *= g;
                bx *= b;
                newImage.setRGB(i, j, (ax << 24) | (rx << 16) | (gx << 8) | (bx << 0));
            }
        }
        return newImage;
    }



}
