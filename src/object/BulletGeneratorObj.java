package object;

import java.awt.Graphics;

import scene.DodgeScene;
import system.GameObject;
import system.MainFrame;

public class BulletGeneratorObj extends GameObject {
	private int bulletGenCount;
	private static int countResetValue;
	private int count;
	DodgeScene dodgeScene;
	
	public BulletGeneratorObj(DodgeScene dodgeScene) {
		super();
		this.dodgeScene = dodgeScene;
		bulletGenCount = 1;
		countResetValue = 17;
		count = countResetValue;
		
	}
	
	@Override
	public void update() {
		// TODO Auto-generated method stub
		if(--count <= 0 && bulletGenCount < 90){
			
			int xLeft			= (int)(Math.random() * BulletObj.GEN_SPACE);
			int xRight		= (int)(Math.random() * BulletObj.GEN_SPACE) + (BulletObj.GEN_AREA_WIDTH - BulletObj.GEN_SPACE);
			int genPosX	= (int)(Math.random() * 2) == 0 ? xLeft : xRight;
			
			int yTop			= (int)(Math.random() * BulletObj.GEN_SPACE);
			int yBottom	= (int)(Math.random() * BulletObj.GEN_SPACE) + (BulletObj.GEN_AREA_HEIGHT - BulletObj.GEN_SPACE);
			int genPosY	= (int)(Math.random() * 2) == 0 ? yTop : yBottom;
			
			
			double speedX= Math.random() * BulletObj.MAX_SPEED + BulletObj.MIN_SPEED;
			double speedY= Math.random() * BulletObj.MAX_SPEED + BulletObj.MIN_SPEED;
			dodgeScene.addGameObject(new BulletObj(genPosX, genPosY, speedX, speedY, 30));
			
			bulletGenCount++;
			count = countResetValue;
		}
		
		
	}

	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		g.drawString(bulletGenCount + "", 30, 130);
	}

}
