/*
 * Scene의 lineupObjectToRenderingPriority(); 에서 사용하기 위한 코드.
 * GameObject들을 화면에 그려줄 우선순위에 따라 정렬한다.
 */
package interfaces;

import java.util.Comparator;

import system.GameObject;

public class GameObjectRenderingComparator implements Comparator<GameObject> {

	@Override
	public int compare(GameObject o1, GameObject o2) {
		// rp = RenderingPriority
		int o1rp = o1.getRenderingPriority();
		int o2rp = o2.getRenderingPriority();
		
		// 주어진 객체를 비교하여서 작으면 음수, 같으면 0, 더 크다면 양수로 반환.
		return (o1rp > o2rp) ? 1 : ( (o1rp == o2rp) ? 0 : -1 );
	}

}
