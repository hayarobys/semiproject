package object;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Point;

import system.SceneManager;

public class TitleMenuObj extends GameButtonObj{
	private String menuName;
	private SceneManager sceneManager;
	private Color fontColor;
	private Font menuFont;
	private FontMetrics fontMetrics;
	private Graphics g;
	private boolean selected;	// 마우스가 이 객체를 가리키고 있는지 판단.
	
	public TitleMenuObj(String menuName, Graphics g, int renderingPriority){
		super();
		
		this.menuName = menuName;
		setRenderingPriority(renderingPriority);
		
		// GameButtonObj.setRect(int x, int y, int width, int height);
		this.g = g;
		fontColor = Color.gray;
		menuFont = new Font("DIALOG_INPUT", Font.PLAIN, 17);
		
		fontMetrics = g.getFontMetrics(menuFont);
		setLocation(80, 200);
		setWidth(fontMetrics.stringWidth(menuName));
		setHeight(fontMetrics.getHeight());
	}
	
	public TitleMenuObj(String menuName, Graphics g){
		this(menuName, g, 0);
	}
	
	public void setFont(Font f){
		menuFont = f;
		
		fontMetrics = g.getFontMetrics(menuFont);
		setWidth(fontMetrics.stringWidth(menuName));
		setHeight(fontMetrics.getHeight());
	}
	
	public void setSceneManager(SceneManager sceneManager){
		this.sceneManager = sceneManager;
	}
	
	@Override
	public void mouseEntered() {
		System.out.println("메뉴mouseEntered");
		selected = true;
		fontColor = Color.lightGray;
	}

	@Override
	public void mouseExited() {
		System.out.println("메뉴mouseExited");
		selected = false;
		fontColor = Color.gray;
	}

	@Override
	public void mousePressed() {
		System.out.println("메뉴mousePressed");
		fontColor = Color.darkGray;
		
		switch(menuName){
		case "Dodge":
			System.out.println("Dodge가 선택됨.");
			SceneManager.sceneChange("DodgeScene");
			break;
		case "Snow":
			System.out.println("Snow가 선택됨.");
			SceneManager.sceneChange("SnowScene");
			break;
		case "Ant":
			System.out.println("Ant가 선택됨.");
			SceneManager.sceneChange("AntScene");
			break;
		case "Credits":
			System.out.println("Credits가 선택됨.");
			SceneManager.sceneChange("CreditsScene");
			break;
		case "Quit":
			System.exit(0);
			break;
		default:
			
			break;
		}
	}

	@Override
	public void mouseReleased() {
		System.out.println("메뉴mouseReleased");
		if(getMouseEnteredState()){
			fontColor = Color.lightGray;
		}else{
			fontColor = Color.gray;
		}
	}

	@Override
	public void update() {
		//System.out.println("TitleMenuObject update called");
		
	}

	@Override
	public void draw(Graphics g) {
		//System.out.println("TitleMenuObject draw called");
		if(g == null)	System.out.println("g가 왜 비었지?");
		
		Color originalColor = g.getColor();
		Font originalFont = g.getFont();
		
		g.setColor(fontColor);
		g.setFont(menuFont);
		
		g.drawString(menuName, (int)getX(), (int)getY() + fontMetrics.getAscent());
		
		g.setColor(originalColor);
		g.setFont(originalFont);
		
		//System.out.println(getX() + ", " + getY() + ", " + getWidth() + ", " + getHeight());
		
		if(selected){
			g.setColor(Color.lightGray);
			g.drawLine((int)getX(), (int)getY() + getHeight(), (int)getX() + getWidth(), (int)getY() + getHeight());
		}
		g.setColor(originalColor);
		
	}

	public String getMenuName(){
		return menuName;
	}

	@Override
	public boolean isContains(Point targetPoint) {
		
		return super.isContains(targetPoint);
	}

	
}	// class TextButton{}
