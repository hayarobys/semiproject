package object;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;

import system.GameObject;
import system.MainFrame;
import system.SceneManager;

public class SnowSetObj extends GameObject {

	public static final double airResistance = 0.98;	// 공기저항 상수, 속도에 비례
	public static final double GRAVITY = 0.02;
	public static final int halfWidth = MainFrame.FRAME_WIDTH / 2;
	public static final int halfHeight = MainFrame.FRAME_HEIGHT / 2;
	
	private double windDirection = 70;	// 바람 방향, 0 ~ 360의 값을 가지며 0일때 3시방향이다. 여기선 바람이 위로 불지 않도록 0~180으로 제한한다.
	private double windPower = 0.01;
	private double addVx = Math.cos(Math.toRadians(windDirection)) *  windPower;	// 바람방향과 바람의 세기를 곱한 값.
	private double addVy = GRAVITY;// + Math.sin(Math.toRadians(windDirection)) *  windPower;
	private int windChangeCount = SceneManager.FPS * 5;
	
	private ArrayList<Snow> snowList;
	
	
	public SnowSetObj(int numOfGen, int minSnowSize, int maxSnowSize, int renderingPriority){
		setRenderingPriority(renderingPriority);
		
		snowList = new ArrayList<Snow>(numOfGen);
		
		int xRange = 0;
		int yRange = 0;
		for(int i = 0; i < numOfGen; i++){
			
			xRange = (int)(Math.random() * (MainFrame.FRAME_WIDTH * 2)) - halfWidth;
			yRange = -(int)(Math.random() * MainFrame.FRAME_HEIGHT * 2);
			snowList.add(new Snow(xRange, yRange, (int)(Math.random() * maxSnowSize) + minSnowSize));
		}
	}	// construct SnowSetObj()
	
	private void resetSnow(Snow snow){
		snow.resetSpeed();
		snow.setX((int)(Math.random() * (MainFrame.FRAME_WIDTH * 2)) - halfWidth);
		snow.setY(-(int)(Math.random() * halfHeight));
	}
	
	public void windChange(){
		if(--windChangeCount > 0){
			//System.out.println("Return windChangeCount : " + windChangeCount);
			return;
		}
		
		windChangeCount = (int)(Math.random() * (SceneManager.FPS * 7)) + (SceneManager.FPS * 3);
		System.out.println("windChangeCount : " + windChangeCount);
		windDirection = (int)(Math.random() * 180);
		windPower = ((int)(Math.random() * 5) + 5) / 1000d;	// 0.005 <= x < 0.01
		
		addVx = Math.cos(Math.toRadians(windDirection)) *  windPower;	// 바람방향과 바람의 세기를 곱한 값.
		//addVy = GRAVITY;
		
		System.out.println("windDirection : " + windDirection);
		System.out.println("windPower : " + windPower);
	}
	
	@Override
	public void update() {
		//System.out.println("SnowSetObj의 update호출");
		Iterator<Snow> iterator = snowList.iterator();
		Snow snow = null;
		
		while(iterator.hasNext()){
			snow = iterator.next();
			
			if(snow.getY() > MainFrame.FRAME_HEIGHT){
				resetSnow(snow);
			}
			
			snow.move(addVx, addVy);
			
			snow.setXSpeed(snow.getXSpeed() * airResistance);
			snow.setYSpeed(snow.getYSpeed() * airResistance);
			
			
		}	// while()
		
		windChange();
	}	// update()

	@Override
	public void draw(Graphics g) {
		//System.out.println("SnowSetObj의 draw(g) 호출");
		Color originalColor = g.getColor();
		g.setColor(Snow.getColor());
		
		Iterator<Snow> iterator = snowList.iterator();
		Snow snow = null;
		while(iterator.hasNext()){
			snow = iterator.next();
			//System.out.println("눈의 좌표 (" + snow.getX() + ", " + snow.getY() + ")");
			g.fillOval(snow.getRoundX(), snow.getRoundY(), snow.getSize(), snow.getSize());
		}
		
		g.setColor(originalColor);
	}	// draw(g)

}	// SnowSetObj{}
