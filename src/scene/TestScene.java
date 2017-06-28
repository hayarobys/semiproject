package scene;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import system.GameObject;
import system.Scene;
import system.SceneManager;

public class TestScene extends Scene implements MouseListener, MouseMotionListener{
	int count = 0;
	SceneManager sceneManager;
	
	public TestScene(SceneManager sceneManager){
		super();
		System.out.println("TestScene 의 생성자 호출");
		this.sceneManager = sceneManager;
		setSceneName("TestScene");
	}
	
	@Override
	public void leaveScene() {
		super.leaveScene();
		
		System.out.println("리스너 제거!");
		sceneManager.getMainFrame().removeMouseListener(this);
		sceneManager.getMainFrame().removeMouseMotionListener(this);
	}

	@Override
	public void enterScene(Graphics g) {
		super.enterScene(g);
		
		System.out.println("리스너 등록!");
		sceneManager.getMainFrame().addMouseListener(this);
		sceneManager.getMainFrame().addMouseMotionListener(this);
	}

	@Override
	public void update() {
		System.out.println("TestScene-update() called");
		count++;
		
	}

	@Override
	public void draw(Graphics g) {
		System.out.println("TestScene-draw() called");
		g.drawString(count+"", 200, 200);
	}

	@Override
	public void lineupObjectToRenderingPriority() {
		
	}

	@Override
	public void addGameObject(GameObject o) {
		
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		System.out.println("마우스 클릭");
	}

	@Override
	public void mousePressed(MouseEvent e) {
		System.out.println("마우스 프레쓰");
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		System.out.println("마우스 릴리즈");
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		System.out.println("마우스 엔터");
	}

	@Override
	public void mouseExited(MouseEvent e) {
		System.out.println("마우스 엑시트");
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		System.out.println("마우스 드래그");
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		System.out.println("마우스 무브");
	}
}	// class SnowScene{}