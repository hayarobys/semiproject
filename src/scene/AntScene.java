package scene;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

import object.AntObj;
import object.AntObj.AntType;
import object.BackgroundObj;
import object.DragBoxObj;
import object.GameButtonObj;
import system.GameObject;
import system.MouseState;
import system.MyKeyListener;
import system.Scene;
import system.SceneManager;

public class AntScene extends Scene implements MouseListener, MouseMotionListener{
	private ArrayList<GameObject> addList;
	private ArrayList<GameObject> objectList;
	private ArrayList<GameButtonObj> antList;
	private ArrayList<AntObj> antList2;
	private DragBoxObj dragBox;
	
	private SceneManager sceneManager;
	private MyKeyListener myKeyListener;
	
	private MouseState mouse;
	private Point rightMousePressedPoint;
	
	private Graphics graphics;
	
	public static final String GUIDE = "개미 생성 : 마우스 휠 / 이동 : 드래그 후 우클릭";
	public static final Color GUIDE_COLOR = Color.black;
	
	public AntScene(SceneManager sceneManager){
		this.sceneManager = sceneManager;
		
		setSceneName("AntScene");
	}
	
	@Override
	public void leaveScene() {
		super.leaveScene();
		sceneManager.getMainFrame().removeMouseListener(this);
		sceneManager.getMainFrame().removeMouseMotionListener(this);
		sceneManager.getMainFrame().removeKeyListener(myKeyListener);
		myKeyListener = null;
		rightMousePressedPoint = null;
		objectList.clear();
		objectList = null;
		addList.clear();
		addList = null;
		dragBox = null;
		antList2.clear();
		antList2 = null;
		antList.clear();
		antList = null;
		graphics = null;
	}

	@Override
	public void enterScene(Graphics g) {
		super.enterScene(g);
		mouse = new MouseState();
		graphics = g;
		dragBox = new DragBoxObj(mouse);
		
		rightMousePressedPoint = new Point();
		myKeyListener = new MyKeyListener();
		sceneManager.getMainFrame().addKeyListener(myKeyListener);
		sceneManager.getMainFrame().addMouseListener(this);
		sceneManager.getMainFrame().addMouseMotionListener(this);
		
		antList = new ArrayList<GameButtonObj>(100);
		objectList = new ArrayList<GameObject>();
		antList2 = new ArrayList<AntObj>(100);
		addList = new ArrayList<GameObject>(80);
		
		addGameObject(new BackgroundObj("rsc/background/antground.png", -10));
		addGameObject(dragBox);
		
		
	}

	@Override
	public void addGameObject(GameObject o) {
		addList.add(o);
		
		//lineupObjectToRenderingPriority();
	}

	@Override
	public void update() {
		if(MyKeyListener.getKeyEscBuff() != 0){
			System.out.println("ESC키 감지!");
			SceneManager.sceneChange("TitleScene");
			MyKeyListener.setKeyEscBuff(0);
		}
	
		Iterator<GameObject> iterator = null;
		GameObject obj = null;
		if(addList.size() != 0){
			 iterator = addList.iterator();
			while(iterator.hasNext()){
				obj = iterator.next();
				objectList.add(obj);
				if(obj instanceof AntObj){
					System.out.println("개미 생성");
					antList2.add((AntObj)obj);
					antList.add((GameButtonObj)obj);
				}
			}
			
			addList.clear();
		}
		
		iterator = objectList.iterator();
		while(iterator.hasNext()){
			obj = iterator.next();
			obj.update();
		}
		
		
		mouse.mouseInteractionCheck(antList);
		
		if(mouse.isMousePressed()){
			Iterator<AntObj> it = antList2.iterator();
			AntObj ant = null;
			while(it.hasNext()){
				ant = it.next();
				if(dragBox.isContains(ant)){
					ant.setSelected(true);
				}else{
					ant.setSelected(false);
				}
			}
		}
		
		
		
		if(rightMousePressedPoint.x != 0 && rightMousePressedPoint.y != 0){
			// 아직 처리하지 않은 마우스 오른쪽 버튼의 릴리즈가 감지됐다면
			System.out.println("타겟팅 작업중");
			AntObj ant = null;
			Iterator<AntObj> it = antList2.iterator();
			while(it.hasNext()){
				ant = it.next();
				System.out.println(ant.isSelected());
				if(ant.isSelected()){
					ant.setTarget(rightMousePressedPoint.x, rightMousePressedPoint.y);
					System.out.println("타게팅" + rightMousePressedPoint.x + ", " + rightMousePressedPoint.y);
				}
			}	
			
			rightMousePressedPoint.setLocation(0, 0);
		}
	}	// update()

	@Override
	public void draw(Graphics g) {
		Color originalColor = g.getColor();
		
		Iterator<GameObject> iterator = objectList.iterator();
		while(iterator.hasNext()){
			iterator.next().draw(g);
		}
		
		g.drawString("개체 수: " + antList2.size() + " 마리", 25, 45);
	//	g.drawString("(" + mouse.getX() + ", " + mouse.getY() + ")", 40, 130);
	//	g.drawString("(" + (int)mouse.getMouseStartPointX() + ", " + (int)mouse.getMouseStartPointY() + ")", 40, 145);
	//	g.drawString(mouse.isMousePressed() + "", 40, 160);
		
		g.setColor(GUIDE_COLOR);
		g.drawString(GUIDE, 25, 28);
		
		g.setColor(originalColor);
		
	}	// draw(g)

	@Override
	public void lineupObjectToRenderingPriority() {
		Collections.sort(objectList, gorc);
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
		mouse.setStartPoint(e.getX(), e.getY());
		
		if(e.getButton() == MouseEvent.BUTTON2){
			if(g != null){
				for(int i = 0; i < 2; i++){
					addGameObject(new AntObj(AntType.black, e.getX(), e.getY(), g));
					addGameObject(new AntObj(AntType.red, e.getX(), e.getY(), g));
					addGameObject(new AntObj(AntType.white, e.getX(), e.getY(), g));
				}
			}
		}else if(e.getButton() == MouseEvent.BUTTON3){
			System.out.println("우클릭 감지");
			rightMousePressedPoint.setLocation(e.getX(), e.getY());
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		System.out.println("마우스 릴리즈 " + e.getButton());
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
}	// class AntScene{}
