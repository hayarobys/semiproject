package object;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

import system.GameObject;

public class CreditsBackgroundObj extends GameObject {
	private static Image backgroundImg;
	
	public CreditsBackgroundObj(){
		backgroundImg = new ImageIcon("rsc/background/thanks.png").getImage();
	}
	
	@Override
	public void update() {
		
	}

	@Override
	public void draw(Graphics g) {
		g.drawImage(backgroundImg, 0, 0, null);
	}

}
