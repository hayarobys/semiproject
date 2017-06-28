package system;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MyKeyListener implements KeyListener {
	private static int keyAllBuff;	// 모든 키보드 입력을 담는 버퍼
	
	private static int keyBuff;	// 필요한 키보드 입력을 담는 버퍼
	private static int keySlowBuff;	// 슬로우 버튼 입력을 담는 버퍼
	private static int keyEscBuff;	// ESC 버튼 입력을 담는 버퍼
	
	
	public static final int CONTROL_UP			= 0x0001;
	public static final int CONTROL_DOWN	= 0x0002;
	public static final int CONTROL_LEFT		= 0x0004;
	public static final int CONTROL_RIGHT	= 0x0008;
	public static final int CONTROL_SLOW	= 0x0010;
	public static final int CONTROL_ESC		= 0x0011;

	public MyKeyListener(){
		clear();
	}
	
	public static void clear(){
		System.out.println("모든 keyBuffer클리어");
		keyAllBuff = 0;
		keyBuff = 0;
		keySlowBuff = 0;
		keyEscBuff = 0;
	}
	
	public static int getKeyAllBuff(){
		return keyAllBuff;
	}
	
	public static int getKeyBuff(){
		return keyBuff;
	}
	
	public static int getKeySlowBuff(){
		return keySlowBuff;
	}
	
	public static int getKeyEscBuff(){
		return keyEscBuff;
	}
	
	public static void setKeyEscBuff(int keyCode){
		keyEscBuff = keyCode;
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		
		
	}

	// 키입력에 관한 처리는 이곳을 참조.
	// http://icegeo.egloos.com/311481
	@Override
	public void keyPressed(KeyEvent e) {
		System.out.println(e.getKeyCode() + " 키 프레쓰 감지");
		keyAllBuff = e.getKeyCode();
		
		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP:
		case KeyEvent.VK_W:
			keyBuff |= CONTROL_UP;
			break;
		case KeyEvent.VK_DOWN:
		case KeyEvent.VK_S:
			keyBuff |= CONTROL_DOWN;
			break;
		case KeyEvent.VK_LEFT:
		case KeyEvent.VK_A:
			keyBuff |= CONTROL_LEFT;
			break;
		case KeyEvent.VK_RIGHT:
		case KeyEvent.VK_D:
			keyBuff |= CONTROL_RIGHT;
			break;
		case KeyEvent.VK_SPACE:
		case KeyEvent.VK_SHIFT:
			keySlowBuff = CONTROL_SLOW;
			break;
		case KeyEvent.VK_ESCAPE:
			// 아무것도 하지마라. 누르는 동안 계속 호출된다.
			break;
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		System.out.println(e.getKeyCode() + " 키 릴리즈감지");
		keyAllBuff = 0;
		
		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP:
		case KeyEvent.VK_W:
			keyBuff &=~ CONTROL_UP;
			break;
		case KeyEvent.VK_DOWN:
		case KeyEvent.VK_S:
			keyBuff &=~ CONTROL_DOWN;
			break;
		case KeyEvent.VK_LEFT:
		case KeyEvent.VK_A:
			keyBuff &=~ CONTROL_LEFT;
			break;
		case KeyEvent.VK_RIGHT:
		case KeyEvent.VK_D:
			keyBuff &=~ CONTROL_RIGHT;
			break;
		case KeyEvent.VK_SPACE:
		case KeyEvent.VK_SHIFT:
			keySlowBuff = 0;
			break;
		case KeyEvent.VK_ESCAPE:
			keyEscBuff = CONTROL_ESC;
			System.out.println("MyKeyListener에서 ESC키 감지 " + keySlowBuff);
			break;
		}
	}
	
}	// class MyKeyListener{}
