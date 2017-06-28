package object;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import interfaces.Collisionable;
import system.GameObject;
import system.MainFrame;
import system.MyKeyListener;

public class DodgePlayerObj extends GameObject implements Collisionable{
	private int size;
	private double speed;	// 현재 이동속도
	
	Color color;
	
	private final double FAST_SPEED = 5;
	private final double SLOW_SPEED = 3.1;
	
	public DodgePlayerObj(int x, int y) {
		super();
		color = new Color(255, 0, 89);
		
		setRenderingPriority(0);
		setLocation(x, y);
		size = 12;
		speed = FAST_SPEED;
		setWidth(size);
		setHeight(size);
	}
	
	public void move(double addX, double addY){
		double originalX = getX();
		double originalY = getY();
		
		double moveX = originalX + addX;
		double moveY = originalY + addY;
		
		if(moveX < 0 || moveX > MainFrame.FRAME_WIDTH)	moveX = originalX;
		if(moveY < 0 || moveY > MainFrame.FRAME_HEIGHT)	moveY = originalY;
		
		setX(moveX);
		setY(moveY);
	}
	
	@Override
	public void update() {
		if(MyKeyListener.getKeySlowBuff() != 0){
			speed = SLOW_SPEED;
		}else{
			speed = FAST_SPEED;
		}
		
		
		
		switch(MyKeyListener.getKeyBuff()){
		case 1:	// 상
			//setY(getY() - speed);
			move(0, -speed);
			break;
		case 2:	// 하
			//setY(getY() + speed);
			move(0, speed);
			break;
		case 4:	// 좌
			//setX(getX() - speed);
			move(-speed, 0);
			break;
		case 8:	// 우
			//setX(getX() + speed);
			move(speed, 0);
			break;
		case 5:	// 좌측 상 대각선
			//setLocation(getX() - speed, getY() - speed);
			move(-speed, -speed);
			break;
		case 9:	// 우측 상 대각선
			//setLocation(getX() + speed, getY() - speed);
			move(speed, -speed);
			break;
		case 6:	// 좌측 하 대각선
			//setLocation(getX() - speed, getY() + speed);
			move(-speed, speed);
			break;
		case 10:	// 우측 하 대각선
			//setLocation(getX() + speed, getY() + speed);
			move(speed, speed);
			break;
		case 13:	// 좌상우
			//setY(getY() - speed);
			move(0, -speed);
			break;
		case 14:	// 좌하우
			//setY(getY() + speed);
			move(0, speed);
			break;
		case 7:	//	상좌하
			//setX(getX() - speed);
			move(-speed, 0);
			break;
		case 11:	// 상우하
			//setX(getX() + speed);
			move(speed, 0);
			break;
		default:
			
			break;
		}
	}

	@Override
	public void draw(Graphics g) {
		//System.out.println("DodgePlayerObj의 draw(g) 호출");
		Color originalColor = g.getColor();
		g.setColor(color);
		g.fillOval((int)Math.round(getX()) - getWidth()/2, (int)Math.round(getY()) - getHeight()/2, getWidth(), getHeight());
		
		//g.setColor(Color.black);
		//g.drawLine((int)getX(), (int)getY(), (int)(getX() + getRadius()), (int)getY());
		
		g.setColor(originalColor);
		
	}

	@Override
	public boolean isContains(Point targetPoint) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isContains(Collisionable targetObject) {
		if(targetObject instanceof BulletObj){
			BulletObj b = (BulletObj)targetObject;
			
			// 두 점 사이의 거리가 두 원의 반지름의 합보다 작으면 충돌.
			return	Math.sqrt(	( b.getX() - getX() ) * ( b.getX() - getX() )	+
										( b.getY() - getY() ) * ( b.getY() - getY() )
									  )	<  ( b.getSize() + getSize() ) / 2;
		}
		return false;
	}
	
	public double getRadius(){
		return size/2d;
	}
	
	public int getSize(){
		return size;
	}
}
