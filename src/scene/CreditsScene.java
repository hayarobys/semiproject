package scene;

import java.awt.FontMetrics;
import java.awt.Graphics;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

import db.CreditDAO;
import db.CreditDTO;
import object.CreditsBackgroundObj;
import object.CreditsDataObj;
import system.GameObject;
import system.MainFrame;
import system.MyKeyListener;
import system.Scene;
import system.SceneManager;

public class CreditsScene extends Scene {
	private ArrayList<GameObject> objectList;
	private SceneManager sceneManager;
	private MyKeyListener myKeyListener;
	private CreditDAO creditDAO;
	private ArrayList<CreditDTO> dataList;
	
	public CreditsScene(SceneManager sceneManager){
		super();
		setSceneName("CreditsScene");
		this.sceneManager = sceneManager;
	}
	
	@Override
	public void leaveScene() {
		super.leaveScene();
		
		System.out.println("CreditScene의 leaveScene(g) 호출");
		
		sceneManager.getMainFrame().removeKeyListener(myKeyListener);
		myKeyListener = null;
		
		objectList.clear();
		objectList = null;
		
		dataList.clear();
		dataList = null;
		
		creditDAO = null;
	}

	@Override
	public void enterScene(Graphics g) {
		super.enterScene(g);
		
		System.out.println("CreditScene의 enterScene(g) 호출");
		
		myKeyListener = new MyKeyListener();
		sceneManager.getMainFrame().addKeyListener(myKeyListener);
		
		objectList = new ArrayList<GameObject>();
		
		objectList.add(new CreditsBackgroundObj());
		
		creditDAO = new CreditDAO();
		dataList = creditDAO.loading("rsc" + File.separator + "credit" + File.separator+ "credits.txt");
		
		int rightFontGap = 20;
		FontMetrics fontMetrics = g.getFontMetrics(CreditsDataObj.font);
		int ySet = MainFrame.FRAME_HEIGHT;
		CreditsDataObj obj = null;
		for(CreditDTO dto : dataList){
			obj = new CreditsDataObj(dto);
			
			obj.setX(MainFrame.FRAME_WIDTH - dto.getImg().getWidth(sceneManager.getMainFrame()) - rightFontGap);
			obj.setY(ySet);
			
			obj.setFontWidth(fontMetrics.stringWidth(dto.getDescription()));
			obj.setFontHeight(fontMetrics.getHeight());
			obj.setFontAscent(fontMetrics.getAscent());
			
			obj.setFontDescriptionX(MainFrame.FRAME_WIDTH - obj.getFontWidth() - rightFontGap);
			obj.setFontDescriptionY((int)obj.getY() + dto.getImg().getHeight(sceneManager.getMainFrame()) + obj.getFontAscent());
			
			ySet = ySet + obj.getHeight() + obj.getFontHeight() * 5;
			
			addGameObject(obj);
			
		}
		
	}

	@Override
	public void addGameObject(GameObject o) {
		objectList.add(o);
		lineupObjectToRenderingPriority();
	}

	@Override
	public void update() {
		if(MyKeyListener.getKeyEscBuff() != 0){
			System.out.println("ESC키 감지!");
			SceneManager.sceneChange("TitleScene");
			MyKeyListener.setKeyEscBuff(0);
		}
		
		Iterator<GameObject> iterator = objectList.iterator();
		while(iterator.hasNext()){
			iterator.next().update();
		}
	}

	@Override
	public void draw(Graphics g) {
		Iterator<GameObject> iterator = objectList.iterator();
		while(iterator.hasNext()){
			iterator.next().draw(g);
		}
	}

	@Override
	public void lineupObjectToRenderingPriority() {
		Collections.sort(objectList, gorc);
	}

}
