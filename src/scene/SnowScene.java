package scene;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.ImageIcon;

import object.BackgroundObj;
import object.SnowSetObj;
import system.GameObject;
import system.MainFrame;
import system.MyKeyListener;
import system.Scene;
import system.SceneManager;

public class SnowScene extends Scene {
	ArrayList<GameObject> objectList;
	
	SceneManager sceneManager;
	MainFrame main;
	//Image backgroundImg;
	MyKeyListener key;
	
	public SnowScene(SceneManager sceneManager){
		super();
		setSceneName("SnowScene");
		this.sceneManager = sceneManager;
		main = sceneManager.getMainFrame();
	}
	
	@Override
	public void leaveScene() {
		super.leaveScene();
		
		objectList.clear();
		objectList = null;
		//backgroundImg = null;
		MyKeyListener.clear();
		sceneManager.getMainFrame().removeKeyListener(key);
		key = null;
	}

	@Override
	public void enterScene(Graphics g) {
		super.enterScene(g);
		
		//backgroundImg = new ImageIcon("rsc/background/struct.png").getImage();
		key = new MyKeyListener();
		sceneManager.getMainFrame().addKeyListener(key);
		
		objectList = new ArrayList<GameObject>();
		
		addGameObject(new SnowSetObj(250, 1, 2, -2));
		addGameObject(new SnowSetObj(180, 1, 3, -1));
		addGameObject(new BackgroundObj("rsc/background/struct.png", 0));
		addGameObject(new SnowSetObj(100, 2, 4, 1));
		addGameObject(new SnowSetObj(30, 3, 4, 2));
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
		
		for(GameObject obj : objectList){
			obj.update();
		}
		
	}

	@Override
	public void draw(Graphics g) {
		Color originalColor = g.getColor();
		
		// 배경을 그린다.
		g.setColor(Color.black);
		g.fillRect(0, 0, MainFrame.FRAME_WIDTH, MainFrame.FRAME_HEIGHT);
		//g.drawImage(backgroundImg, 0, 0, main);
		
		
		// 눈을 그린다.
		for(GameObject obj : objectList){
			obj.draw(g);
		}
		
		g.setColor(originalColor);
	}

	@Override
	public void lineupObjectToRenderingPriority() {
		System.out.println("SnowScene-lineupObjectToRenderingPriority() called");
		Collections.sort(objectList, gorc);
	}

}	// SnowScene{}
