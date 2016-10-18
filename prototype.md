# 原型模式 Prototype Pattern
  
####目的：複製一個物件而不是重新創建一個

###我不想create新物件，只想直接copy一個物件再修改就好   
當需要建例的新物件與原有物件很相似，想直接複製原有物件再修改就好，這時候就需要原型模式了。  
在JAVA中只要一個物件宣告implements Cloneable，Override clone()方法後，就能複製一個物件，以下是之前讀
[大話設計模式]寫的範例，寫履歷Resume的時候，一般都是複製上一份履歷內容再做修改，由於履歷中的工作經驗
也是一個類別，直接複製的話工作經驗的內容將不會被複製，因此工作經驗WorkExperience也需要實作Cloneable。


###類別圖  
![Prototype Class Diagram](image/prototype.gif)  
  
###程式碼
```
/**
 * 履歷
 */
public class Resume implements Cloneable{
	private String name;
	private String age;
	private String education;
	private WorkExperience work;	// 工作經驗
	
	
	public Resume(String name){
		this.name = name;
		work = new WorkExperience();
	}
	
	private Resume(WorkExperience work) throws CloneNotSupportedException{
		this.work = (WorkExperience) work.clone();
	}
	
	public void setProfile(String age, String education){
		this.age = age;
		this.education = education;
	}
	
	public void setWorkExperience(String workDate, String company){
		work.setWorkDate(workDate);
		work.setCompany(company);
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		// 直接使用 super.clone()，不會得到新的 WorkExperinece實體
		Resume clone = new Resume(this.work);
		clone.name = this.name;
		clone.age = this.age;
		clone.education = this.education;
		return clone;
	}
	
	
	public void display(){
		System.out.printf("%s %s %s \n", name,education,age);
		System.out.printf("工作經歷: %s %s \n", work.getWorkDate(), work.getCompany());
	}
}

/**
 * 履歷中的工作經驗
 *
 */
public class WorkExperience  implements Cloneable {
	private String workDate;
	private String company;
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getWorkDate() {
		return workDate;
	}
	public void setWorkDate(String workDate) {
		this.workDate = workDate;
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
		System.out.println("---第一份履歷---");
		Resume resume = new Resume("Yan");
		resume.setProfile("31", "master");
		resume.setWorkExperience("2016-2019", "FOYA");
		resume.display();
		
		System.out.println("---第二份履歷(複製上一份修改)---");
		// 履歷表2跟1有許多相似的地方，因此直接複製履歷表1做修改
		Resume resume2  = (Resume) resume.clone();
		resume2.setWorkExperience("2011-2016", "IISI");
		resume2.display();
		
		System.out.println("---第三份履歷(複製第一份修改)---");	
		// 履歷表3跟1有許多相似的地方，因此直接複製履歷表1做修改
		Resume resume3  = (Resume) resume.clone();
		resume3.setProfile("21", "bachelor");
		resume3.setWorkExperience("2005-2011", "TMD");
		resume3.display();

	}
}
```
測試結果
```
============原型模式測試============
---第一份履歷---
Yan master 31 
工作經歷: 2016-2019 FOYA 
---第二份履歷(複製上一份修改)---
Yan master 31 
工作經歷: 2011-2016 IISI 
---第三份履歷(複製第一份修改)---
Yan bachelor 21 
工作經歷: 2005-2011 TMD 
```