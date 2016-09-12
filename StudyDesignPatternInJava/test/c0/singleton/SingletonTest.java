package c0.singleton;

import c0.singleton.Singleton;

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
    	// 兩個執行序同時執行
        Thread t1 = new SingletonTest("執行序T1"); // 產生Thread物件
        Thread t2 = new SingletonTest("執行序T2"); // 產生Thread物件
        t1.start(); // 開始執行t1.run()
        t2.start();
    }
}
