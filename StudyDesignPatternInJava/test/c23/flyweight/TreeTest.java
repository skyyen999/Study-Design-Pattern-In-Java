package c23.flyweight;

import org.junit.Test;

/**
 * 蠅量級模式 - 測試
 */
public class TreeTest {
	@Test
	public void test(){
		// 
		Tree rose = TreeManager.getTree("rose");
		rose.setOwner("rose");
		rose.display();
		Tree jRose = TreeManager.getTree("rose");
		jRose.setOwner("jacky");
		System.out.println("------jacky要一棵樹的時候，其實我們沒有創一棵新的給他，而是拿pool裡面那顆------");
		jRose.display();
		
		System.out.println();
		Tree hinoki = TreeManager.getTree("Hinoki");
		hinoki.setOwner("no one");
		hinoki.display();
	}
}
