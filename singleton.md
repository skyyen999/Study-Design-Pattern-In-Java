# 單例模式 Singleton  

####目的：保證一個類別會產生一個物件，而且要提供存取該物件的統一方法  

單例模式是一個簡單易懂的模式，下面的code很簡單的就達到這樣的需求，
因為一開始我們就已經直接建例(new)物件，所以以下的寫法也稱為貪婪單例模式。  
  
```
public class SingletonGreed {
	// 一開始就建立物件，這樣只要一直回傳這個物件就是簡單的singleton
	private static SingletonGreed instance  = new SingletonGreed();
	
	// private constructor，這樣其他物件就沒辦法直接用new來取得新的實體
	private SingletonGreed(){}
	
	// 因為constructor已經private，所以需要另外提供方法讓其他程式調用這個類別
	public static SingletonGreed getInstance(){
		return instance;
	}
}
```  

假如建立這個物件需要耗費很多資源，可是程式運行中不一定會需要它，我們希望只有在第一次getInstance被呼叫的時候才花費資源來建立物件，code就要修改一下  
```
public class Singleton {
	private static Singleton instance;
	
	
	private Singleton(){
		// 這裡面跑很了多code，建立物件需要花費很多資源
	}
	
	
	public static Singleton getInstance(){
		// 第一次被呼叫的時候再建立物件
		if(instance == null){
			instance = new Singleton();
		} 
		return instance;
	}
}
```  

以上程式看起來沒問題，不過如果是多執行的情況下呼叫getInstance，可能第一個執行序跑到instance = new Singleton()時，將時間讓給第二個
執行序，因此第二個執行序也執行了instance = new Singleton()，造成同時new了兩個新的物件。

```
/**
 * 單例模式測試
 */
public class SingletonTest extends Thread {
    String myId;
    public SingletonTest(String id) {
        myId = id;
    }
    
    // 執行序執行的時候就去呼叫Singleton.getInstance()
    public void run() {
    	Singleton singleton = Singleton.getInstance();
    	if(singleton != null){
    		// 用hashCode判斷前後兩次取到的Singleton物件是否為同一個
            System.out.println(myId+"產生 Singleton:" + singleton.hashCode());       		
    	}
    }
    
    public static void main(String[] argv) {
		/*
    	// 單執行序的時候，s1與s2確實為同一個物件
    	Singleton s1  =  Singleton.getInstance();
    	Singleton s2  =  Singleton.getInstance();
    	System.out.println("s1:"+s1.hashCode() + " s2:" + s2.hashCode());
    	System.out.println();
    	*/
		
    	// 兩個執行序同時執行
        Thread t1 = new SingletonTest("執行序T1"); // 產生Thread物件
        Thread t2 = new SingletonTest("執行序T2"); // 產生Thread物件
        t1.start(); // 開始執行t1.run()
        t2.start();
    }
}
```  
為了解決這樣的問題，可以用synchronized修飾來解決這個問題，讓getInstance方法被調用的時候被lock住，就不會同時產生兩個物件。

```  
public class Singleton {
	private static Singleton instance;
	
	
	private Singleton(){
		// 這裡面跑很了多code，建立物件需要花費很多資源
	}
	
	// 多執行緒時使用synchronized保證Singleton一定是單一的 
	public static synchronized Singleton getInstance(){
		if(instance == null){
			instance = new Singleton();
		} 
		return instance;
	}
}
```  
上面這樣的寫法，synchronized整個方法會造成執行效能會變差，實際上需要lock住的只有創造物件的過程，也就是new Singleton這段程式碼而已，
因此可以將synchronized搬到getInstance方法內來加快程式的效能。

```  
public class Singleton {
	private static Singleton instance;
	
	
	private Singleton(){
		// 這裡面跑很了多code，建立物件需要花費很多資源
	}
	
	// 多執行緒時，當物件需要被建立時才使用synchronized保證Singleton一定是單一的 ，增加程式校能
	public static Singleton getInstance(){
		if(instance == null){
			synchronized(Singleton.class){
				if(instance == null){
					instance = new Singleton();
				}	
			}
		} 
		return instance;
	}
}
```  
由這個簡單的單例模式可以看到，一樣的設計模式在不同的情況也是會有不同的變化。
設計模式不會是一段固定的程式碼，而是一種如何解決特定問題的概念。  

