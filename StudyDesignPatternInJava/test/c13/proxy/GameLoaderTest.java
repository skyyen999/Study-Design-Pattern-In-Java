package c13.proxy;

import org.junit.Test;

import c13.proxy.game.ProxyGameDisplay;
import c13.proxy.game.RealGameDisplay;

public class GameLoaderTest {
	@Test
	public void test(){
		// 沒使用代理
		new RealGameDisplay().display();
		System.out.println();
		// 使用代理
		new ProxyGameDisplay(new RealGameDisplay()).display();
	}
}
