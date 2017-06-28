package system;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.Date;

import scene.AntScene;
import scene.CreditsScene;
import scene.DodgeScene;
import scene.RankScene;
import scene.SnowScene;
import scene.TestScene;
import scene.TitleScene;

public class SceneManager{
	public static final int FPS = 62;	// 이 프로그램은 1초에 [FPS]번 동작 합니다.
	
	private MainFrame mainFrame;
	private BufferStrategy mainBuffer;
	private Graphics g;	// main frame의 graphics
	
	private static String pendingSceneChange;
	
	private ArrayList<Scene> sceneList;
	private Scene currentScene;
	
	FpsDelayer fpsDelayer = new FpsDelayer(FPS);
	// 초당 60프레임을 원함
	FpsCounter fpsCounter = new FpsCounter();
	// 현재 FPS를 측정하고 보여주는 클래스
	
	public SceneManager(MainFrame mainFrame){
		this.mainFrame = mainFrame;
		this.mainBuffer = mainFrame.getMainBufferStrategy(); 
		g = mainBuffer.getDrawGraphics();
		sceneList = new ArrayList<Scene>();
		
		initSceneList();
		
		enterScene("TitleScene");
		//enterScene("TestScene", g);
	}	// construct SceneManager()
	
	// 존재하는 모든 Scene들을 인스턴스화하여 목록에 저장한다.
	private void initSceneList(){
		sceneList.add(new DodgeScene(this));
		sceneList.add(new TestScene(this));
		sceneList.add(new TitleScene(this));
		sceneList.add(new RankScene(this, g));
		sceneList.add(new SnowScene(this));
		sceneList.add(new CreditsScene(this));
		sceneList.add(new AntScene(this));
	}
	
	public static void sceneChange(String sceneName){
		pendingSceneChange = sceneName;
	}
	
	public void enterScene(String sceneName){
		pendingSceneChange = null;
		System.out.println("SceneManager의 enterScene("+sceneName+", g) 호출");
		// 현재 Scene에서 나간다.
		if(currentScene != null){
			System.out.println("SceneManager의 enterScene()에서 "+currentScene.getSceneName()+"의 leaveScene() 호출");
			currentScene.leaveScene();
		}
		
		for(Scene scene : sceneList){
			System.out.println("SceneManager의 enterScene()에서 for문 들어옴. 반복중");
			
			// sceneList에서 sceneName과 일치하는게 있는지 찾아본다.
			if( sceneName.equals(scene.getSceneName()) ){
				System.out.println("찾았다!");
				
				// 찾았다면 해당 Scene으로 들어간다.
				if(g != null){
					System.out.println("g 가 비어있지 않군.");
					scene.enterScene(g);
					System.out.println("진입성공");
					currentScene = scene;
					
					break;
				}else{
					System.out.println("g가 비어있습니다.");
				}
				
				return;
			}
		}
	}	// enterScene()
	
	public void launch(){
		long diffTime = 0;
		long startTime = 0, finishTime = 0;
		
		while(true){
			startTime = new Date().getTime();
			
			update();
			draw(g);
			
			finishTime = new Date().getTime();
			diffTime = finishTime - startTime;
			
			fpsDelayer.delay((int)diffTime);		// 이번 루프의 연산시간을 인자로 줌
			fpsCounter.count();						// FPS를 카운트하도록 매 반복마다 호출.

		}	// while
	}	// launch()
	
	void update(){
		//System.out.println("SceneManager의 update() called");
		
		if(pendingSceneChange != null){
			System.out.println("Scene변경 요청이 들어왔다!");
			enterScene(pendingSceneChange);
			pendingSceneChange = null;
		}
		
		
		currentScene.update();
	}	// update()
	
	void draw(Graphics g){
		//System.out.println("SceneManager의 draw() called");
	
		currentScene.draw(g);
		
		Color originalColor = g.getColor();
		g.setColor(Color.white);
		
		g.drawString(Integer.toString(fpsCounter.getFps()), MainFrame.FRAME_WIDTH - 50, 35);
		//g.drawString(Integer.toString(fpsDelayer.getDelay()), 30, 75);
		mainBuffer.show();
		
		g.setColor(originalColor);
		g.clearRect(0, 0, mainFrame.getWidth(), mainFrame.getHeight());
	}	// draw()
	
	public MainFrame getMainFrame(){
		return mainFrame;
	}
	
	public Dimension getScreenSize(){
		
		return new Dimension(mainFrame.getScreenWidth(), mainFrame.getScreenHeight());
	}
}	// class SceneManager{}