package system;

import java.awt.Graphics;

import interfaces.GameObjectRenderingComparator;

public abstract class Scene{
	// 상속받은 sceneName은 다른 Scene들과 중복되지 않게 주의 할 것.
	private String sceneName;
	protected Graphics g;
	protected GameObjectRenderingComparator gorc;
	
	protected Scene(){
		// 상속받는 클래스는 생성자에서 super();를 반드시 호출하라.
		// 상속받는 클래스는 생성자에서 setSceneName(String sceneName); 을 반드시 호출하라.
		gorc = new GameObjectRenderingComparator();
	}
	
	public void leaveScene(){
		System.out.println("leaved " + getSceneName());
	}
	
	public void enterScene(Graphics g){
		System.out.println("entered " + getSceneName());
		this.g = g;
	}
	
	// 각 Scene에 정의된 컬렉션에 객체를 저장하도록 구현할 것. 뒤이어 lineupObjectToRenderingPriority()를 호출하는 것이 좋음.
	public abstract void addGameObject(GameObject o);
	
	public abstract void update();
	
	public abstract void draw(Graphics g);
	
	// RenderingPriority순으로 객체 정렬. Scene마다 컬렉션 프레임웤이 다르기에, 구현만 강제하고 인자는 비워두었다.
	public abstract void lineupObjectToRenderingPriority();
	
	public String getSceneName(){
		System.out.println(sceneName + " 의 getSceneName() 호출");
		return sceneName;
	}
	
	public void setSceneName(String sceneName){
		System.out.println(sceneName + " 의 setSceneName() 호출");
		this.sceneName = sceneName;
	}
}