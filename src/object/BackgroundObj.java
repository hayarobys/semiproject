package object;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

import system.GameObject;

public class BackgroundObj extends GameObject {
	public static Image img;
	
	public BackgroundObj(String dir, int renderingPriority){
		super();
		setRenderingPriority(renderingPriority);
		img = new ImageIcon(dir).getImage();
	}
	
	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		
		g.drawImage(img, 0, 0, null);
	}

}
