package object;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import system.GameObject;
import system.MainFrame;

public class DodgeBackParticleObj extends GameObject {
	ArrayList<Particle> particleList;
	
	
	public DodgeBackParticleObj() {
		setRenderingPriority(-10);
		particleList = new ArrayList<Particle>();
		
		
		Particle p = null;
		for(int i = 0; i < 50; i++){
			p = new Particle();
			p.setRandomLocation();
			p.setRandomColor();
			p.setRandomSize();
			particleList.add(p);
		}
	}
	
	@Override
	public void update() {
		
		
	}

	@Override
	public void draw(Graphics g) {
		Color originalColor = g.getColor();
		
		g.setColor(Color.black);
		g.fillRect(0, 0, MainFrame.FRAME_WIDTH, MainFrame.FRAME_HEIGHT);
		
		for(Particle p : particleList){
			g.setColor(p.getColor());
			g.fillOval(p.getX(), p.getY(), p.getSize(), p.getSize());
		}
		
		g.setColor(originalColor);
	}

}
