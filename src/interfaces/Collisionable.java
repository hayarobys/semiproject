/*
 * 점 또는 객체와 충돌 검사가 가능함을 명시한다.
 */

package interfaces;

import java.awt.Point;

public interface Collisionable {
	
	// 마우스같은 점 좌표와의 충돌 여부를 검사.
	public abstract boolean isContains(Point targetPoint);
	
	// 또다른 충돌검사가 가능한 객체와의 충돌 여부를 검사.
	public abstract boolean isContains(Collisionable targetObject);
}
