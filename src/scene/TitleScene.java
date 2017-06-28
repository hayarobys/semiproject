package scene;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;

import object.GameButtonObj;
import object.TitleBackgroundObj;
import object.TitleMenuObj;
import system.GameObject;
import system.MouseState;
import system.Scene;
import system.SceneManager;

public class TitleScene extends Scene implements MouseListener, MouseMotionListener{
	private LinkedList<GameObject> objectList;
	private SceneManager sceneManager;
	private MouseState mouse;
	private ArrayList<GameButtonObj> gameButtonList;
	
	private int titleMenuObjSize;
	private final int TITLE_MENU_X = 35;
	private final int TITLE_MENU_Y = 370;
	
	public TitleScene(SceneManager sceneManager){
		super();
		System.out.println("TitleScene 의 생성자 호출");
		setSceneName("TitleScene");
		mouse = new MouseState();
		this.sceneManager = sceneManager;
		objectList = new LinkedList<GameObject>();
		gameButtonList = new ArrayList<GameButtonObj>();
	}
	
	@Override
	public void leaveScene() {
		super.leaveScene();
		System.out.println("리스너 제거!");
		sceneManager.getMainFrame().removeMouseListener(this);
		sceneManager.getMainFrame().removeMouseMotionListener(this);
		objectList.clear();
		gameButtonList.clear();
		titleMenuObjSize = 0;
		mouse = null;
	}

	@Override
	public void enterScene(Graphics g) {
		super.enterScene(g);
		System.out.println("리스너 등록!");
		sceneManager.getMainFrame().addMouseListener(this);
		sceneManager.getMainFrame().addMouseMotionListener(this);
		mouse = new MouseState();
		Dimension screenSize = sceneManager.getScreenSize();
		addMenu("Dodge", g);
		addMenu("Snow", g);
		addMenu("Ant", g);
		addMenu("Credits", g);
		addMenu("Quit", g);
		
		addGameObject( new TitleBackgroundObj((int)screenSize.getWidth(), (int)screenSize.getHeight()) );
		
		// GameButton들만 따로 리스트에 담아둔다.
		// 이후 update메서드에서도 주의하여 관리하라.
		GameObject o = null;
		Iterator<GameObject> iterator = objectList.iterator();
		while(iterator.hasNext()){
			o = iterator.next();
			if(o instanceof GameButtonObj){
				gameButtonList.add((GameButtonObj)o);
			}
		}
	}	// enterScene(Graphic)
	
	public void addMenu(String name, Graphics g){
		TitleMenuObj temp = new TitleMenuObj(name, g);
		temp.setFont(new Font("DIALOG_INPUT", Font.PLAIN, 17));
		temp.setLocation(TITLE_MENU_X, (int)Math.round(TITLE_MENU_Y + titleMenuObjSize * temp.getHeight() * 1.6));
		temp.setSceneManager(sceneManager);
		
		addGameObject(temp);
		titleMenuObjSize++;
	}
	
	@Override
	public void update() {
		//System.out.println("TitleScene-update() called");
		
		Iterator<GameObject> iterator = objectList.iterator();
		while(iterator.hasNext()){
			iterator.next().update();
		}
		
		mouse.mouseInteractionCheck(gameButtonList);
	}

	@Override
	public void draw(Graphics g) {
		//System.out.println("TitleScene-draw() called");
		Iterator<GameObject> iterator = objectList.iterator();
		while(iterator.hasNext()){
			iterator.next().draw(g);
		}
		
		//Color originalColor = g.getColor();
		//g.setColor(Color.white);
		//g.drawString(mouse.getPoint().toString(), 20, 90);
		//g.setColor(originalColor);
	}

	@Override
	public void lineupObjectToRenderingPriority() {
		System.out.println("TitleScene-lineupObjectToRenderingPriority() called");
		Collections.sort(objectList, gorc);
	}

	@Override
	public void addGameObject(GameObject o) {
		System.out.println("TitleScene-addGameObject() called");
		objectList.add(o);
		lineupObjectToRenderingPriority();
	}
	
	/*-----------------------------------
	 * 			MouseEvent
	 * -----------------------------------
	 */

	public void setMousePoint(int x, int y){
		mouse.setPoint(x, y);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		System.out.println("마우스 클릭");
	}

	@Override
	public void mousePressed(MouseEvent e) {
		System.out.println("마우스 프레쓰");
		mouse.setMousePressed(true);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		System.out.println("마우스 릴리즈");
		mouse.setMousePressed(false);
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
		//System.out.println("마우스 드래그");
		mouse.setPoint(e.getX(), e.getY());
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		//System.out.println("마우스 무브");
		mouse.setPoint(e.getX(), e.getY());
	}
}	// class TitleScene{}