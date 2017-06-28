package object;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

import system.GameObject;

public class TitleBackgroundObj extends GameObject {
	private static Image[] backgroundImg = new Image[3];
	private Image currentBackgroundImg = null;
	
	public TitleBackgroundObj(int screenWidth, int screenHeight){
		setWidth(screenWidth);
		setHeight(screenHeight);
		
		if(backgroundImg[0] == null){
			for(int i=0; i<backgroundImg.length; i++){
				//backgroundImg[i] = new ImageIcon("rsc" + File.pathSeparator + "background" + File.pathSeparator + "bg" + i + ".png").getImage();
				backgroundImg[i] = new ImageIcon("rsc/background/bg" + i + ".png").getImage();
			}
		}
		currentBackgroundImg = backgroundImg[(int)(Math.random()*3)];
		
		setRenderingPriority(-10);
	}
	
	@Override
	public void update() {
		
		
	}

	@Override
	public void draw(Graphics g) {
		//System.out.println("호출");
		//g.drawImage(currentBackgroundImg, getX(), getY(), getWidth(), getHeight(), null);
		g.drawImage(currentBackgroundImg, 0, 0, getWidth(), getHeight(), null);
	}

}
