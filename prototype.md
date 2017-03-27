# 原型模式 Prototype Pattern
  
####目的：複製一個物件而不是重新創建一個

###冒險者要寫履歷了!!! 可以直接複製上一份來改就好嗎?
當需要建例的新物件與原有物件很相似，想直接複製原有物件再修改就好，這時候就需要原型模式了。  
在JAVA中只要一個物件宣告implements Cloneable，Override clone()方法後，就能複製一個物件。
冒險者協會有專門的人力資源單位來管理冒險者的履歷，為了方便冒險者更新自己的履歷，協會提供方便的
複製履歷功能，實作的程式碼如下，在這邊冒險者的履歷Resume類別與冒險經歷AdventureExperience類別都
已經分別實作Cloneable介面，要修改的時候應該可以簡單的複製前一份作修改就好。

###類別圖  
![Prototype Class Diagram](image/prototype.gif)  
  
###程式碼
```
/**
 * 冒險者的履歷
 */
public class Resume implements Cloneable{
	private String name;
	private int level;
	private String profession;
	private AdventureExperience experience;

	public Resume(String name, String profession,  int level){
		this.name = name;
		this.level = level;
		this.profession = profession;
		experience = new AdventureExperience();
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		// 直接使用 super.clone()，不會得到新的 WorkExperinece實體
		return super.clone();
	}
	
	public void display(){
		System.out.printf("冒險者：%s-%s 等級:%d \n", name, profession, level);
		System.out.printf("冒險經歷: %s %s \n", experience.getDate(), experience.getLocation());
		System.out.println();
	}

	public void setExperience(String date, String location) {
		experience.setDate(date);
		experience.setLocation(location);
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public void setProfession(String profession) {
		this.profession = profession;
	}
}	

/**
 * 冒險者的冒險經歷
 */
public class AdventureExperience implements Cloneable {
	private String date;		// 日期
	private String location;	// 地點
	
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
	@Override
	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	} 
}

```  
測試碼
```  
/**
 * 原型模式-測式
 */
public class ResumeTest {
	@Test
	public void test() throws CloneNotSupportedException {
		System.out.println("============原型模式測試============");
		Resume resume = new Resume("傑克","見習道士",1);
		resume.setExperience("2011/01/01", "仙靈島");
		
		// 履歷表2跟1有許多相似的地方，因此直接複製履歷表1做修改
		Resume resume2  = (Resume) resume.clone();
		resume2.setLevel(5);
		resume2.setExperience("2012/03/31", "隱龍窟");
		
		// 履歷表3跟1有許多相似的地方，因此直接複製履歷表1做修改
		Resume resume3  = (Resume) resume2.clone();
		resume3.setProfession("殭屍道長");
		resume3.setExperience("2012/11/31", "赤鬼王血池");
		
		System.out.println("---第一份履歷---");
		resume.display();
		System.out.println("---第二份履歷(複製上一份修改)---");
		resume2.display();
		System.out.println("---第三份履歷(複製第一份修改)---");	
		resume3.display();
	}
}
```
測試結果(錯誤的結果)
```
============原型模式測試============
---第一份履歷---
冒險者：傑克-見習道士 等級:1 
冒險經歷: 2012/11/31 赤鬼王血池 

---第二份履歷(複製上一份修改)---
冒險者：傑克-見習道士 等級:5 
冒險經歷: 2012/11/31 赤鬼王血池 

---第三份履歷(複製第一份修改)---
冒險者：傑克-殭屍道長 等級:5 
冒險經歷: 2012/11/31 赤鬼王血池 
```

冒險經歷竟然全部都被蓋掉了，會出現這種情況是因為預設的clone方法是淺複製，
也就是只會複製String, int這些基本型態，冒險經歷experience被複製出來只是參照(reference)，
因此後面我們修改冒險經歷時也會一併修改前面的履歷，這種情況當然是不行的，以下我們稍為修改一下
clone這個方法，讓冒險經歷可以被實實在在的被複製一份。


```
/**
 * 冒險者的履歷
 */
public class Resume implements Cloneable{
    /*....省略未修改部分...*/
	
	private Resume(AdventureExperience experience) throws CloneNotSupportedException{
		this.experience = (AdventureExperience) experience.clone();
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		// 直接使用 super.clone()，不會得到新的 AdventureExperinece實體
		Resume clone = new Resume(experience);
		clone.setName(this.name);
		clone.setLevel(this.level);
		clone.setProfession(this.profession);
		return clone;
	}
```

測試結果
```
============原型模式測試============
---第一份履歷---
冒險者：傑克-見習道士 等級:1 
冒險經歷: 2011/01/01 仙靈島 

---第二份履歷(複製上一份修改)---
冒險者：傑克-見習道士 等級:5 
冒險經歷: 2012/03/31 隱龍窟 

---第三份履歷(複製第一份修改)---
冒險者：傑克-殭屍道長 等級:5 
冒險經歷: 2012/11/31 赤鬼王血池 
```

