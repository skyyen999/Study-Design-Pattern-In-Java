package c23.flyweight;

import org.junit.Test;

/**
 * 蠅量級模式 - 測試
 */
public class TreeTest {
	@Test
	public void test(){
		System.out.println("============蠅量級模式測試============");

		Tree rose = TreeManager.getTree("玫瑰");
		rose.setOwner("rose");
		rose.display();
		Tree jRose = TreeManager.getTree("玫瑰");
		jRose.setOwner("jacky");
		System.out.println("------jacky要一棵玫瑰的時候，其實我們沒有創一棵的給他，而是拿pool裡面那棵------");
		jRose.display();
		
		System.out.println();
		Tree hinoki = TreeManager.getTree("Hinoki");
		hinoki.setOwner("no one");
		hinoki.display();
	}
}
