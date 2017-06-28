package scene;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

import javax.swing.JOptionPane;

import db.DodgeRankDAO;
import db.DodgeRankDTO;
import object.BulletGeneratorObj;
import object.BulletObj;
import object.DodgeBackParticleObj;
import object.DodgePlayerObj;
import object.TimeCounterObj;
import system.GameObject;
import system.MyKeyListener;
import system.Scene;
import system.SceneManager;

public class DodgeScene extends Scene{
	private boolean pause;	// 게임 일시정지/ 오버
	
	private ArrayList<GameObject> objectList;
	private ArrayList<GameObject> addList;
	private SceneManager sceneManager;
	
	private MyKeyListener myKeyListener;
	
	public static final String GUIDE = "move : W, A, S, D or ← ↑ ↓ → /  slow : Shift or Space";
	public static final Color GUIDE_COLOR = Color.white;
	
	public DodgeScene(SceneManager sceneManager){
		super();
		System.out.println("DodgeScene 의 생성자 호출");
		setSceneName("DodgeScene");
		this.sceneManager = sceneManager;
	}
	
	@Override
	public void leaveScene() {
		super.leaveScene();
		System.out.println("DodgeScene의 leaveScene(g) 호출");
		
		sceneManager.getMainFrame().removeKeyListener(myKeyListener);
		objectList.clear();
		addList.clear();
		
		objectList = null;
		addList = null;
		myKeyListener = null;
	}

	@Override
	public void enterScene(Graphics g) {
		super.enterScene(g);
		
		System.out.println("DodgeScene의 enterScene(g) 호출");
		
		myKeyListener = new MyKeyListener();
		sceneManager.getMainFrame().addKeyListener(myKeyListener);
		
		objectList = new ArrayList<GameObject>();
		addList = new ArrayList<GameObject>(100);
		
		reset();
	}

	public void reset(){
		pause = false;
		objectList.clear();
		addList.clear();
		Dimension screenSize = sceneManager.getScreenSize();
		
		addGameObject(new DodgeBackParticleObj());
		addGameObject(new DodgePlayerObj((int)screenSize.getWidth()/2, (int)screenSize.getHeight()/2));
		addGameObject(new BulletGeneratorObj(this));
		addGameObject(new TimeCounterObj());
		
		
		
		lineupObjectToRenderingPriority();
		TimeCounterObj.startTimer();
		MyKeyListener.clear();
	}
	
	@Override
	public void update() {
		
		if(MyKeyListener.getKeyEscBuff() != 0){
			System.out.println("ESC키 감지!");
			SceneManager.sceneChange("TitleScene");
			MyKeyListener.setKeyEscBuff(0);
		}
		
		if(pause){
			if(MyKeyListener.getKeySlowBuff() != 0){
				pause = false;
				reset();
			}else{
				return;
			}
		}
		
		//System.out.println(MyKeyListener.getKeyEscBuff());
		for(GameObject obj : addList){
			objectList.add(obj);
		}
		//lineupObjectToRenderingPriority();
		addList.clear();
		
		// DodgePlaterObj와 BulletObj들의 충돌검사
		
		
		collisionCheck();
		//System.out.println("DodgeScene에서 DodgePlayerObj객체를 찾지 못했습니다.");
		
		
		
		Iterator<GameObject> iterator = objectList.iterator();
		while(iterator.hasNext()){
			GameObject obj = iterator.next();
			obj.update();
			
		}
		
	} // update()

	private void collisionCheck(){
		DodgePlayerObj player = null;
		for(GameObject obj : objectList){
			if(obj instanceof DodgePlayerObj){
				player = (DodgePlayerObj)obj;
				
				break;
			}
		}
		
		for(GameObject obj : objectList){
			if(obj instanceof BulletObj){
				if(player.isContains((BulletObj)obj)){
				// 이 객체와 플레이어가 충돌했을 경우!
					System.out.println("부딪혔어요!");
					TimeCounterObj.stopTimer();
					pause = true;
					if(saveRank() != -1){
						SceneManager.sceneChange("RankScene");
					}else{
						SceneManager.sceneChange("TitleScene");
					}
					break;
				}
			}
		}
	} // collisionCheck()
	
	@Override
	public void draw(Graphics g) {
		Color originalColor = g.getColor();
		
		
		Iterator<GameObject> iterator = objectList.iterator();
		while(iterator.hasNext()){
			iterator.next().draw(g);
		}
		
		//g.drawString(MyKeyListener.getKeyBuff()+"", 30, 90);
		//g.drawString(MyKeyListener.getKeySlowBuff()+"", 30, 105);
		g.setColor(GUIDE_COLOR);
		g.drawString(GUIDE, 25, 30);
		
		g.setColor(originalColor);
	}

	private int saveRank(){
		//System.out.println();
		
		String input = "";
		String error = "";
		while (true) {
			input = JOptionPane.showInputDialog(error + "이름을 입력하세요. (" + TimeCounterObj.getTimeRecord() + " second)");
			System.out.println("\"" + input + "\"" + "을 입력하였습니다.");
			
			if (input == null || input.equals("")){
				input = "익명";
				break;
			}
			
			if(input.length() > 8){
				error = "너무 깁니다. ";
				continue;
			}
			
			break;
		}
		
		DodgeRankDAO dao = new DodgeRankDAO();
		DodgeRankDTO dto = new DodgeRankDTO();
		dto.setDname(input);
		//dto.setDdate("");
		dto.setDrecord((int)TimeCounterObj.getTimeRecord());
		
		int result = dao.insert(dto); 
		if(result == -1){
			System.out.println("\n\nDB접속에 실패했습니다. 랭킹목록을 보기위해선 DB가 설치되었는지 확인하십시오.");
		}
		
		return result;
	}	// saveRank();
	
	@Override
	public void lineupObjectToRenderingPriority() {
		System.out.println("DodgeScene-lineupObjectToRenderingPriority() called");
		Collections.sort(objectList, gorc);
	}

	@Override
	public void addGameObject(GameObject o) {
		System.out.println("DodgeScene-addGameObject() called");
		addList.add(o);
		
	/*	if(o instanceof BulletObj){
			return;
		}*/
		
		//lineupObjectToRenderingPriority();
	}
}	// class DodgeScene{}