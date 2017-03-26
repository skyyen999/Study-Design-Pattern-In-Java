# 代理模式的實際應用-Spring AOP

####保護代理
AOP全名為Aspect-Oriented Programming，翻譯後為面向導向程式設計，無法從名子看出AOP到底是什麼對吧!?
這邊簡單介紹一下AOP，所謂的AOP就是在一段程式執行前、中、後插入其他想執行的程式。    
***什麼!!你說為什麼不直接把要插入執行的程式碼直接寫到程式碼裡面就好***  
AOP的重點就是插入的這段程式碼跟我們原本程式的業務邏輯沒有關係，因此我們希望不要修改原本的業務邏輯
也能達到相同的效果。  
  
下面我們有一個戰鬥管理類別FightManager，doFight這個方法會負責管理每一場戰鬥，一開始這個方法只是單
純管理玩家與怪物的戰鬥，接下來我們希望在戰鬥開始之前標註時間，馬上我們給它加上記錄時間的了程式碼，可以發
現這段程式碼只是單純的記錄時間，與doFight原本的程式碼關係不大。這時候就可以用代理模式來達到這樣的效果。


###程式碼
```
/**
 * 戰鬥管理類別(加入時間註記前)
 */
public class FightManager {
	public void doFight(String userName){	
		//  可以直接插入這行記錄時間用的程式碼，不過這樣就會汙染了本來只負責戰鬥管理的doFight方法
		// 	system.out.println("開始時間:"  +  new Date().toLocaleString());
 		
		System.out.println(userName + "帶領冒險者們與無辜的怪物戰鬥");
		System.out.println("....以下省略戰鬥過程");
		System.out.println(userName + "帶領冒險者們洗劫了怪物的家，結束一場慘無妖道的屠殺");
	}	
}

/**
 * 戰鬥管理類別(代理)
 */
public class ProxyFightManager extends FightManager{
	private FightManager source;
	public ProxyFightManager(FightManager source){
		this.source = source;
	}
	public void doFight(String userName){
		//這段完全就只是記錄用，與戰鬥過程沒關係
		System.out.println(">開始時間:"  +  new Date().toLocaleString()); 		
		source.doFight(userName);
	}	
}
```  

測試碼
```  
/**
 * 代理模式(保護代理)-測試
 */
public class PersonTest {
	@Test
	public void test(){
		System.out.println("============代理模式(保護代理)測試============");

		// 沒使用代理
		System.out.println("---沒使用代理---");
		Person realPerson = new PersonBean();
		realPerson.setLikeCount(10);
		System.out.println("like " +realPerson.getLikeCount());
		
		
		// 使用代理
		System.out.println("---使用代理---");
		Person proxy = new ProxyPersonBean(new PersonBean());
		proxy.setLikeCount(10); // 代理會使這個程式無法被調用
		System.out.println("like " +proxy.getLikeCount());

	}
}
```
測試結果  
```
====代理模式(AOP)測試====
---沒使用代理----
煞氣A阿龐帶領冒險者們與無辜的怪物戰鬥
....以下省略戰鬥過程
煞氣A阿龐帶領冒險者們洗劫了怪物的家，結束一場慘無妖道的屠殺

---使用代理----
>開始時間:2017/3/26 下午 06:17:38
煞氣A阿龐帶領冒險者們與無辜的怪物戰鬥
....以下省略戰鬥過程
煞氣A阿龐帶領冒險者們洗劫了怪物的家，結束一場慘無妖道的屠殺
```