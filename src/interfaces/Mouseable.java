/*
 * 마우스와 상호작용이 가능한 객체임을 명시.
 */
package interfaces;

public interface Mouseable extends Collisionable{
	public abstract void mouseEntered();		// 마우스가 버튼안으로(hover) 들어올때의 동작 정의
	
	public abstract void mouseExited();		// 마우스가 버튼 밖으로 나가는 순간의 동작 정의
	
	public abstract void mousePressed();		// 마우스 버튼을 누르는 순간의 동작 정의
	
	public abstract void mouseReleased();	// 마우스 버튼을 떼는 순간의 동작 정의
}
