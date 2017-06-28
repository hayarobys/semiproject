package system;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

public abstract class GameObject{
	private double x, y;
	private int width, height;
	private int renderingPriority = 10;	// 수가 작을 수록 먼저 그려진다. 수가 클수록 가장 위에 그려진다. 음수입력이 가능하다.
	
	protected GameObject(){
	}
	
	public abstract void update();
	
	public abstract void draw(Graphics g);
	
	public void setRenderingPriority(int renderingPriority){
		this.renderingPriority = renderingPriority;
	}
	
	public int getRenderingPriority(){
		return renderingPriority;
	}
	
	public Rectangle getRect(){
		return new Rectangle((int)x, (int)y, width, height);
	}
	
	public void setRect(int x, int y, int width, int height){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	public Point getLocation(){
		return new Point((int)x, (int)y);
	}
	
	public void setLocation(double x, double y){
		this.x = x;
		this.y = y;
	}
	
	public void setLocation(int x, int y){
		this.x = x;	this.y = y;
	}
	
	public void addLocation(double addX, double addY){
		x += addX;
		y += addY;
	}
	
	public int getRoundX(){
		return (int)Math.round(x);
	}
	
	public double getX(){
		return x;
	}
	
	public void setX(double x){
		this.x = x;
	}
	
	public void addX(double addX){
		x += addX;
	}

	public int getRoundY(){
		return (int)Math.round(y);
	}
	
	public double getY(){
		return y;
	}
	
	public void setY(double y){
		this.y = y;
	}
	
	public void addY(double addY){
		y += addY;
	}
	
	public int getWidth(){
		return width;
	}
	
	public void setWidth(int width){
		this.width = width;
	}
	
	public int getHeight(){
		return height;
	}
	
	public void setHeight(int height){
		this.height = height;
	}
}	// class GameObject{}