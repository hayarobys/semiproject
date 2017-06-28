/*
 * GameButton과 마우스 이벤트의 관계를 정의한 클래스
 * 마우스 리스너를 구현한 Scene의 필드변수로 MouseState를 선언하고,
 * GameButton들을 모아서 MouseState.check(List<GameButton>)를 호출하면 된다.
 */

package system;


import java.awt.Point;
import java.util.Iterator;
import java.util.List;

import object.GameButtonObj;

public class MouseState {
	private Point mousePoint;	// 이벤트 리스너에서 마우스 좌표를 setMousePoint(int, int)로 저장.
	private boolean mousePressed; 
	//private Point mouseReleasedPoint;
	private Point mouseStartPoint;	// 마우스 클릭한 지점의 좌표. 드래그시에 이 좌표는 변하지 않는것을 이용한다.
	
	public MouseState() {
		mousePoint = new Point(0, 0);
		mouseStartPoint = new Point(0, 0);
	}
	

	public int getMouseStartPointX() {
		return (int)mouseStartPoint.getX();
	}
	
	public int getMouseStartPointY() {
		return (int)mouseStartPoint.getY();
	}
	
	public Point getMouseStartPoint() {
		return mouseStartPoint;
	}

	public void setMouseStartPoint(Point mouseStartPoint) {
		this.mouseStartPoint = mouseStartPoint;
	}

	
	public void  setStartPoint(int x, int y){
		mouseStartPoint.setLocation(x, y);
	}
	
	public boolean isMousePressed() {
		return mousePressed;
	}
	
	public void mouseInteractionCheck(List<? extends GameButtonObj> list){
		Iterator<? extends GameButtonObj> iterator2 = list.iterator();
		
		while(iterator2.hasNext()){
			GameButtonObj b = iterator2.next();
			boolean collision = b.isContains(mousePoint);
			if(collision == true){
			// 마우스가 이 객체위에 있는데,
				
				if(mousePressed == true){
				// 마우스가 눌려 있다면
					// 이 안에선 이전부터 객체위에 있었는지, 지금 enter했는지 체크한다.
					b.autoEnteredCheck();
					// 이 안에선 이전 프레임부터 눌려있었는지, 지금 눌린건지 체크한다.
					b.autoPressedCheck();
				}else if(mousePressed == false){
				// 마우스가 안눌려 있다면
					// 이 안에선 이전부터 객체위에 있었는지, 지금 enter했는지 체크한다.
					b.autoEnteredCheck();
					// 이 안에선 이전부터 release상태인지, 지금 release한건지 체크한다.
					b.autoReleasedCheck();
				}
				
			}else if(collision == false){
			// 마우스가 이 객체위에 없는데,
				
				if(b.getMouseEnteredState() == true){
				// 지난 프레임에 enter했던 객체라면

					if(mousePressed == true){
					// 마우스가 눌려 있다면
						b.autoExitedCheck();
					}else if(mousePressed == false){
					// 마우스가 안눌려 있다면
						// 이 안에선 entered를 false로 바꾸고, mouseExited()를 호출한다.
						b.setMouseExited();
					}
					
				}else if(b.getMouseEnteredState() == false){
				// 전혀 동떨어진 객체라면
					// 아무것도 안한다.
				}
				
			}
		}	// while(iterator.hasNext())
	}	// check()
	
	public void setPoint(int x, int y){
		mousePoint.setLocation(x, y);
	}
	
	public int getX(){
		return (int)mousePoint.getX();
	}
	
	public int getY(){
		return (int)mousePoint.getY();
	}
	
	public Point getPoint(){
		return mousePoint;
	}
	
	public void setMousePressed(boolean state){
		this.mousePressed = state;
	}
}
