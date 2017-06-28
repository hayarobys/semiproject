package object;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import interfaces.Collisionable;
import system.GameObject;
import system.MainFrame;

public class BulletObj extends GameObject implements Collisionable {
	public static final int GEN_SPACE = 4;
	public static final int GEN_AREA_X = -GEN_SPACE;
	public static final int GEN_AREA_Y = -GEN_SPACE;
	public static final int GEN_AREA_WIDTH = MainFrame.FRAME_WIDTH + GEN_SPACE * 2;
	public static final int GEN_AREA_HEIGHT = MainFrame.FRAME_HEIGHT + GEN_SPACE * 2;
	
	
	public static final double MAX_SPEED = 1.8;
	public static final double MIN_SPEED = 1;
	
	protected Color color = new Color(189, 255, 84);
	
	protected int size;
	private double speedX;	// x축 이동속도
	private double speedY;	// y축 이동속도
	
	public BulletObj(int x, int y, double speedX, double speedY, int renderingPriority){
		super();
		setLocation(x, y);
		this.speedX = speedX;
		this.speedY = speedY;
		
		
		size = 7;
		
		setRenderingPriority(renderingPriority);
	}
	
	public void setColor(Color color){
		this.color = color;
	}
	
	@Override
	public boolean isContains(Point targetPoint) {
		return false;
	}

	@Override
	public boolean isContains(Collisionable targetObject) {
		// 모든 충돌은 DodgePlayerObj에서 처리한다.
		return false;
	}

	public void move(double addX, double addY){
		double originalX = getX();
		double originalY = getY();
		
		double moveX = originalX + addX;
		double moveY = originalY + addY;
		
		if(moveX < GEN_AREA_X || moveX > GEN_AREA_WIDTH){
			moveX = originalX;
			speedX *= -1;
		}
		
		if(moveY < GEN_AREA_Y || moveY > GEN_AREA_HEIGHT){
			moveY = originalY;
			speedY *= -1;
		}
		
		setX(moveX);
		setY(moveY);
	}
	
	@Override
	public void update() {
		move(speedX, speedY);
	}

	@Override
	public void draw(Graphics g) {
		//System.out.println("Bullet의 draw(g) 호출 - speed(" + (int)speedX + ", " + (int)speedY + ") location [" + (int)getX() + ", " + (int)getY() + "]");
		Color originalColor = g.getColor();
		g.setColor(color);
		g.fillOval((int)(getX() - size/2d), (int)(getY() - size/2d), size, size);
		
		//g.setColor(Color.black);
		//g.drawLine((int)getX(), (int)getY(), (int)(getX() + getRadius()), (int)getY());
		
		g.setColor(originalColor);
	}

	public double getRadius(){
		return size/2d;
	}
	
	public int getSize(){
		return size;
	}
}
