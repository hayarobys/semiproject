package object;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import interfaces.Collisionable;
import system.GameObject;
import system.MouseState;

public class DragBoxObj extends GameObject implements Collisionable {
	private MouseState mouse;
	public static final Color dragBoxFillColor = new Color(130, 200, 255, 50);	// 드래그 박스의 내부 색상
	public static final Color dragBoxBorderColor = new Color(0, 155, 255);		// 드래그 박스의 테두리 색상
	
	private int boxX, boxY,boxEX, boxEY;
	
	public DragBoxObj(MouseState mouse){
		setRenderingPriority(100);
		this.mouse = mouse;
	}
	
	@Override
	public void update() {
		
	}	// update()

	@Override
	public void draw(Graphics g) {
		Color originialColor = g.getColor();
		
		if(mouse.isMousePressed()){
			
			boxX = Math.min(mouse.getMouseStartPointX(), mouse.getX());
			boxY = Math.min(mouse.getMouseStartPointY(), mouse.getY());
			
			boxEX = Math.max(mouse.getMouseStartPointX(), mouse.getX());
			boxEY = Math.max(mouse.getMouseStartPointY(), mouse.getY());
			
			g.setColor(dragBoxFillColor);
			g.fillRect(boxX, boxY, boxEX - boxX, boxEY - boxY);
			
			g.setColor(dragBoxBorderColor);
			g.drawRect(boxX, boxY, boxEX - boxX, boxEY - boxY);
		}
		g.setColor(originialColor);
	}	// draw(g)

	@Override
	public boolean isContains(Point targetPoint) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isContains(Collisionable targetObject) {
		
		if(targetObject instanceof AntObj){
			AntObj ant = (AntObj)targetObject;
			
			return	(	boxX < ant.getMiddleX()	&&	boxEX > ant.getMiddleX()	)	&&
						(	boxY < ant.getMiddleY()	&&	boxEY > ant.getMiddleY()	);
		}
		
		return false;
	}

}
