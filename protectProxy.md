# 代理模式-保護代理

####保護代理
有些情況我們不希望修改一個類別的內容，但希望這類別有些屬性或是操作在某些條件下可以被保護起來，被免被亂用。  
以下是一個簡單的範例，我們有一個個人資料類別(PersonBean Class)，一開始設計是每個人都可以增加或減少喜歡的數字，
不過現在在某些情況下不希望這個數字被修改，可是又不想修改PersonBean的內容，這時候可以用一個代理ProxyPersonBean
來幫助我們達成這個目的。  

###程式碼
```
/**
 * 個人資料介面
 */
public interface Person {
	void setLikeCount(int like);
	int getLikeCount();
	String getName();
	void setName(String name);
}

/**
 * 一般使用的個人資料Bean
 */
public class PersonBean implements Person{
	private String name ;
	private int likeCount;
	
	@Override
	public void setLikeCount(int like) {
		this.likeCount = like;
	}

	public int getLikeCount() {
		return this.likeCount;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}

/**
 * 個人資料代理-使setLikeCount方法被保護起來不能使用
 */
public class ProxyPersonBean implements Person {
	PersonBean person;

	public ProxyPersonBean(PersonBean personBean){
		this.person = personBean;
	};
	
	public String name ;
	public int likeCount;
	
	@Override
	public void setLikeCount(int like) {
		System.out.println("無權限修改like數");
	}
	
	public int getLikeCount() {
		return this.person.getLikeCount();
	}
	
	public String getName() {
		return this.person.getName();
	}

	public void setName(String name) {
		this.person.setName(name);
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
============代理模式(保護代理)測試============
---沒使用代理---
like 10
---使用代理---
無權限修改like數
like 0

```