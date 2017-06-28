package object;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.geom.AffineTransform;

import javax.swing.ImageIcon;

import system.SceneManager;

public class AntObj extends GameButtonObj{
	private static Image antImgs[] = new Image[3];
	public enum AntType {black, red, white};
	
	private AntType type;
	private Image antImg;
	
	private Point target;				// 마우스 클릭으로 정해진 목표 지점.
	private double speed;				// 이동속도
	private double speedX, speedY;	// 이동속도에서 x축, y축 으로 분리한 이동속도
	private int step;
	
	private boolean isSelected;		// 마우스로 선택되었는가?
	private static Color selectColor = new Color(44, 98, 188);
	private static Color hoverColor = new Color(130, 200, 255, 90);
	
	private int middleX, middleY;
	public final int selectOvalSize;
	
	private Graphics2D g2d;
	
	public AntObj(AntType type, int x, int y, Graphics g){
		this.type = type;
		target = new Point(0, 0);
		setLocation(x, y);
		g2d = (Graphics2D)g;
		
		if(antImgs[0] == null || antImgs[1] == null || antImgs[2] == null){
			
			antImgs[0] = new ImageIcon("rsc/ant/antblack.png").getImage();
			antImgs[1] = new ImageIcon("rsc/ant/antred.png").getImage();
			antImgs[2] = new ImageIcon("rsc/ant/antwhite.png").getImage();
			
		}
		
		switch(type){
		case black:
			antImg = antImgs[0];
			break;
		case red:
			antImg = antImgs[1];
			break;
		case white:
			antImg = antImgs[2];
			break;
		}
		
		setWidth(antImg.getWidth(null));
		setHeight(antImg.getHeight(null));
		
		middleX = (int)Math.round(getX() + getWidth() / 2d); 
		middleY = (int)Math.round(getY() + getHeight() / 2d);
		
		selectOvalSize = Math.max(getWidth(), getHeight());
		
		resetSpeed();
	}	// construct AntObj()
	
	
	@Override
	public void update() {
		if(target.x == 0 || target.y == 0){
			move();
		}else{
			attack();
		}
	}

	@Override
	public void draw(Graphics g) {
		Color originalColor = g.getColor();
		if(isSelected){
			g.setColor(selectColor);
			g.drawOval((int)getX(), (int)getY(), selectOvalSize, selectOvalSize);
			
		}else if(getMouseEnteredState()){
			g.setColor(hoverColor);
			g.fillOval((int)getX(), (int)getY(), selectOvalSize, selectOvalSize);
		}
		
		
		AffineTransform origXform = g2d.getTransform();
		AffineTransform newXform = (AffineTransform)(origXform.clone());
		
		newXform.rotate(	Math.atan2(	(getY() + speedY) - getY(), (getX() + speedX) - getX()	), getMiddleX(), getMiddleY()	);
		g2d.setTransform(newXform);
		g2d.drawImage(antImg, (int)getX(), (int)getY(), null);
		g2d.setTransform(origXform);
		
		//g.drawImage(antImg, getRoundX(), getRoundY(), null);
		
		g.setColor(originalColor);
	}	// draw(g)
	
	public void attack(){
		if(getDistance(getLocation(), target) < 20){
			target.setLocation(0, 0);
		}else{
			addLocation(speedX, speedY);
		}
	}
	
	public void move(){
		if(step-- <= 0){
			resetSpeed();
		}
		
		addLocation(speedX, speedY);
	}
	
	public static double getDistance(int x1, int y1, int x2, int y2){
		return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
	}
	
	public static double getDistance(Point me, Point target){
		return getDistance(me.x, me.y, target.x, target.y);
	}
	
	public void setTarget(int x, int y){
		target.setLocation(x, y);
		speed = ((int)(Math.random() * 6) + 24) / 100d;
		double distance = getDistance(getRoundX(), getRoundY(), x, y);
		if(distance == 0)	 distance = 1;
		speedX = (x - getX()) * speed / distance;
		speedY = (y - getY()) * speed / distance;
	}
	
	public void setTarget(Point target){
		setTarget(target.x, target.y);
	}
	
	
	public void resetSpeed(){
		speedX = ((int)(Math.random() * 2) + 2) / 15d;
		speedY = ((int)(Math.random() * 2) + 2) / 15d;
		
		if((int)(Math.random() * 2) > 0)	speedX *= -1;
		if((int)(Math.random() * 2) > 0)	speedY *= -1;
		
		step = (int)(Math.random() * SceneManager.FPS) + 10;
	}


	public AntType getType() {
		return type;
	}


	public void setType(AntType type) {
		this.type = type;
	}


	public boolean isSelected() {
		return isSelected;
	}


	public void setSelected(boolean isSelected) {
		this.isSelected = isSelected;
	}


	public Point getTarget() {
		return target;
	}
	
	


	public int getMiddleX() {
		return (int)Math.round(getX() + getWidth() / 2d); 
		
	}


	public void setMiddleX(int middleX) {
		this.middleX = middleX;
	}


	public int getMiddleY() {
		return (int)Math.round(getY() + getHeight() / 2d);
	}


	public void setMiddleY(int middleY) {
		this.middleY = middleY;
	}


	@Override
	public void mouseEntered() {
		System.out.println("Ant mouseEntered");
		
	}


	@Override
	public void mouseExited() {
		System.out.println("Ant mouseExited");
		
		
	}


	@Override
	public void mousePressed() {
		System.out.println("Ant mousePressed");
		
	}


	@Override
	public void mouseReleased() {
		System.out.println("Ant mouseReleased");
		
	}



	
	
}	// class AntObj{}












