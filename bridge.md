# 橋梁模式 Bridge Pattern

####目的：將抽象介面與實作類別切開，使兩者可以各自變化而不影響彼此  

###冷氣機與搖控器
年紀跟筆者差不多或小一點的讀者應該會知道，以前的冷氣機上面除了電源開關之外，還有旋鈕可以定時，調整溫度等等功能，不過
。

###類別圖 
![Bridge Class Diagram](image/bridge.gif)   

###程式碼 
```
/**
 * 搖控器介面
 */
public abstract class IRomote {
	protected ITelevision tv;
	
	public IRomote(ITelevision tv){
		this.tv = tv;
	}
	
	/**
	 * 開關電源
	 */
	public void powerOn(){
		System.out.println("打開電視機");
		tv.powerOn();
	} ;
	public void powerOff(){
		System.out.println("關閉電視機");
		tv.powerOff();
	} ;
	
	/**
	 * 下一個頻道
	 */
	public void nextChannel(){
		tv.nextChannel();
	}
}


/**
 * 陽春的搖控器，沒什麼額外的功能
 */
public class SonyRemote2000 extends IRomote {
	public SonyRemote2000(ITelevision tv) {
		super(tv);
	}
}

/**
 * 新版搖控器，增加了直接選台的功能
 */
public class SonyRemote2015 extends IRomote {
	public SonyRemote2015(ITelevision tv) {
		super(tv);
	}

	/**
	 * 新型搖控器可以直接選頻道
	 */
	public void selectChannel(int i){
		if(i > 10 || i < 1){
			return;
		}
		
		// ITelevision沒有 setChannel，跑迴圈找頻道
		while( i != tv.channel){
			super.tv.nextChannel();
		}
	}
}

/**
 * 電視機介面
 */
public abstract class  ITelevision{

	private boolean isPowerOn; // 電視機電源
	protected int channel = 1;   // 現在頻道
	/**
	 * 開關電源
	 */
	public void powerOn(){
		this.isPowerOn = true;
	} ;
	public void powerOff(){
		this.isPowerOn = false;
	} ;
	
	/**
	 * 切換到下一個頻道
	 */
	public void nextChannel(){
		if(channel > 10){
			this.channel = 1;
		} else {
			channel++;
		}
	} ;
	
	public void dispaly(){
		// 電源開啟才有辦法顯示頻道
		if(isPowerOn){
			System.out.println("目前頻道 = " + channel);
		}
	}
}

/**
 * 一般的電視
 */
public class SonyTV extends ITelevision {

}

/**
 * 高畫質電視
 */
public class SonyHD extends ITelevision {
	@Override
	public void powerOn(){
		super.powerOn();
		System.out.println("展示高畫值影片");
	};
}

```  
測試碼
```  
/**
 * 橋接模式-測試
 */
public class RemoteTest {

	@Test
	public void test(){
		System.out.println("============橋接模式測試============");

		SonyTV tv = new SonyTV();
		SonyRemote2000 remote2000 = new SonyRemote2000(tv);
		System.out.println("------測試電視------");
		tv.powerOn();
		System.out.println("電視打開");
		tv.dispaly();
		System.out.println("下一個頻道");
		tv.nextChannel();
		tv.dispaly();
		System.out.println("------測試搖控器------");
		remote2000.nextChannel();
		remote2000.nextChannel();
		System.out.println("連按兩下下一個頻道");
		tv.dispaly();

		System.out.println("------測試高畫值電視------");
		SonyHD hdTv = new SonyHD();
		hdTv.dispaly();
		
		System.out.println("----------測試新型搖控器---------------");
		SonyRemote2015 remote2015 = new SonyRemote2015(hdTv);
		remote2015.powerOn();
		hdTv.dispaly();
		System.out.println("---直接切到頻道10---");
		remote2015.selectChannel(10);
		hdTv.dispaly();
	}
}

```  
測試結果
``` 
============橋接模式測試============
------測試電視------
電視打開
目前頻道 = 1
下一個頻道
目前頻道 = 2
------測試搖控器------
連按兩下下一個頻道
目前頻道 = 4
------測試高畫值電視------
----------測試新型搖控器---------------
打開電視機
展示高畫值影片
目前頻道 = 1
---直接切到頻道10---
目前頻道 = 10
``` 