package object;

import java.awt.Color;

import system.MainFrame;

public class Particle {
	public static final int MIN_SIZE = 4;
	public static final int MAX_SIZE = 5;
	
	private int x, y, size = 2;
	private Color color;
	
	public Particle(int x, int y, Color color){
		this.x = x;
		this.y = y;
		this.color = color;
	}
	
	public Particle(){
		this(0, 0, Color.blue);
	}
	
	public void setLocation(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public void setRandomSize(){
		setSize(getRandomArea(MIN_SIZE, MAX_SIZE - MIN_SIZE));
	}
	
	public void setSize(int size){
		this.size = size;
	}
	
	public int getSize(){
		return size;
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	public void setRandomLocation(){
		setLocation(getRandomArea(0, MainFrame.FRAME_WIDTH), getRandomArea(0, MainFrame.FRAME_HEIGHT));
	}
	
	public void setRandomColor(){
		color = new Color(getRandomArea(0, 255), getRandomArea(0, 255), getRandomArea(0, 255));
		color = color.brighter();
	}
	
	public void setColor(Color newColor){
		this.color = newColor;
	}
	
	public Color getColor(){
		return color;
	}
	
	public void brighter(){
		color = color.brighter();
	}
	
	public static int getRandomArea(int start, int numOfArea){
		return	(int)(Math.random() * numOfArea) + start;
		
	}
}
