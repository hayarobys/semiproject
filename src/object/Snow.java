/*
 * 
 * 
 * 
 * 작성자 : 김민규
 * 작성일 : 20170331
 * 정  보 : 중앙정보기술인재개발원 SemiProject 발표작품
 * 			게임엔진 구현과 그 기반으로 작성한 몇 가지 시뮬레이션 게임.
 * 
 * 
 */
package object;

import java.awt.Color;

public class Snow {
	public static final Color color = Color.white;
	
	private int size;
	private double x, y;		// 현재 위치
	private double xSpeed;	// x축 속도
	private double ySpeed;	// y축 속도
	
	
	public Snow(int x, int y, int size){
		//System.out.println("Snow의 생성자 호출");
		this.x = x;
		this.y = y;
		this.size = size;
	}
	
	public Snow(int x, int y){
		this(x, y, 3);
	}
	
	public void move(double addVx, double addVy){
		//System.out.println("Snow의 move호출");
		// 현재 속도에 중력가속도를 고려해 최종적으로 추가되어야할 값을 더한다.
		xSpeed += addVx;
		ySpeed += addVy;
		
		// 공기저항을 감안한다.
		//xSpeed *= SnowSetObj.airResistance;
		//ySpeed *= SnowSetObj.airResistance;
		
		// 최종계산된 속도를 현재 좌표에 더한다.
		x += xSpeed;
		y += ySpeed;
		
		// System.out.print("(" + getX() + ", " + getY() + ") ");
	}
	
	public void resetSpeed(){
		xSpeed = 0;
		ySpeed = 0;
	}
	
	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getRoundX(){
		return (int)Math.round(x);
	}
	
	public int getRoundY(){
		return (int)Math.round(y);
	}
	
	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getXSpeed() {
		return xSpeed;
	}

	public void setXSpeed(double xSpeed) {
		this.xSpeed = xSpeed;
	}

	public double getYSpeed() {
		return ySpeed;
	}

	public void setYSpeed(double ySpeed) {
		this.ySpeed = ySpeed;
	}

	public static Color getColor() {
		return color;
	}
	
	
}
