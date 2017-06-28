/*
 * 작성자 : 김민규
 * 작성일 : 20170331
 * 정  보 : 중앙정보기술인재개발원 SemiProject 발표작품
 * 			게임엔진 구현과 그 기반으로 작성한 몇 가지 시뮬레이션 게임.
 */


package system;

public class MainMethod{
	public static void main(String[] args){
		MainFrame mainFrame = new MainFrame("세미 프로젝트");
		SceneManager manager = new SceneManager(mainFrame);
		manager.launch();
	}
}