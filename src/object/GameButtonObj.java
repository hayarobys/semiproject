package object;

import java.awt.Point;

import interfaces.Collisionable;
import interfaces.Mouseable;
import system.GameObject;

public abstract class GameButtonObj extends GameObject implements Mouseable {
	private boolean mouseEntered;
	private boolean mousePressed;
	
	@Override
	public boolean isContains(Point targetPoint) {
		//System.out.println("isContains(Point)");
		return		(targetPoint.x > getX() &&	targetPoint.x < getX() + getWidth())
				&&	(targetPoint.y > getY() &&	targetPoint.y < getY() + getHeight());
		
	}

	@Override
	public boolean isContains(Collisionable targetObject) {
		//System.out.println("isContains(Collisionable)");
		return false;
	}
	
	public boolean getMousePressedState(){
		return mousePressed;
	}
	
	public boolean getMouseEnteredState(){
		return mouseEntered;
	}
	
	public void setMouseExited(){
		mouseEntered = false;
		mouseExited();
	}
	
	public void autoPressedCheck(){
	// mousePressed가 true였는지 false였는지에 따라 수행 여부를 결정한다.
		if(mousePressed){
		// 이전 프레임에 press 된 경우
			// 아무것도 안한다.
		}else{
		// 이번 프레임에 press 한 경우
			mousePressed = true;
			mousePressed();
		}
	}
	
	public void autoReleasedCheck(){
	// 이 안에선 이전부터 release상태인지, 지금 release한건지 체크한다.
		if(mousePressed){
		// 이번 프레임에 release 한 경우,
			mousePressed = false;
			mouseReleased();
		}else{
		// 이전 프레임부터 release 상태인 경우,
			// 아무것도 안한다.
		}
	}
	
	public void autoExitedCheck(){
		if(mouseEntered){
		// 이번 프레임에 나간 경우
			mouseEntered = false;
			mousePressed = false;
			mouseExited();
		}
	}
	
	public void autoEnteredCheck(){
	// mouseEntered가 true였는지 false였는지에 따라 수행 여부를 결정한다.
		if(mouseEntered){
		// 이전 프레임에 enter 된 경우
			// 아무것도 안한다.
		}else{
		// 이번 프레임에 enter 한 경우
			mouseEntered = true;
			mouseEntered();
		}
	}
}	// class GameButtonObj{}
