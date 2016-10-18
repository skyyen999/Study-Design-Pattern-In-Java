# 代理模式 Proxy Pattern

####目的：為一個物件提供代理物件
代理物件常見的用途如下：
* 虛擬代理(Virtual Proxy):用比較不消耗資源的代理物件來代替實際物件，實際物件只有在真正需要才會被創造
* 遠程代理(Remote Proxy):在本地端提供一個代表物件來存取遠端網址的物件	
* 保護代理(Protect Proxy): 限制其他程式存取權限
* 智能代理(Smart Reference Proxy)：為被代理的物件增加一些動作  

####遊戲讀取中....
要開啟我們的冒險者遊戲其實要花費一翻很大的功夫，如果在讀取的過程畫面跟國防布一樣完全沒有畫面，玩家會
懷疑遊戲是不是壞了，因此我們用一個代理類別，讓遊戲還沒讀取完成之前先跟玩家說，遊戲讀取中...  

上面這個範例跟裝飾模式(Decorator)看起來有九成像，雖然很認真的搜尋各網誌來看看這種應用的代理模式跟裝飾模式有什麼不同，
結論就是代理模式一般只會包一層，裝飾模式可能會包很多層，就像[Head First Design Pattern]一書所講一樣，你可以把裝飾模式
當成一種特化版的代理模式來看待。

###類別圖  
![Proxy Class Diagram](image/proxy.gif)  

###程式碼
```
// 遊戲顯示介面
public interface GameDisplay {
	/**
	 * 顯示畫面
	 */
	void display();
}


/**
 * 被代理的類別
 */
public class RealGameDisplay implements GameDisplay{
	@Override
	public void display() {
		System.out.println("顯示遊戲畫面");
	}
}

/**
 * 代理類別
 */
public class ProxyGameDisplay implements GameDisplay{
	private RealGameDisplay realGameDisplay;
	
	public ProxyGameDisplay(RealGameDisplay realGameDisplay){
		this.realGameDisplay = realGameDisplay;
	}
	
	@Override
	public void display() {
		System.out.println("遊戲讀取中...");
		realGameDisplay.display();
	}
}
```  
測試碼
```  
/**
 * 代理模式(動態代理)-測試
 */
public class GameLoaderTest {
	@Test
	public void test(){
		System.out.println("============代理模式(動態代理)測試============");

		
		// 沒使用代理
		System.out.println("---沒使用代理---");
		new RealGameDisplay().display();
		System.out.println();
		// 使用代理
		System.out.println("---使用代理---");
		new ProxyGameDisplay(new RealGameDisplay()).display();
	}
}
```  
測試結果
```  
============代理模式(動態代理)測試============
---沒使用代理---
顯示遊戲畫面

---使用代理---
遊戲讀取中...
顯示遊戲畫面
```  