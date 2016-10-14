# 原型模式 Prototype Pattern
  
####目的：複製一個物件而不是重新創建一個

###我不想create新物件，只想直接copy一個物件再修改就好   
當需要建例的新物件與原有物件很相似，想直接複製原有物件再修改就好，這時候就需要原型模式了。  
在JAVA中只要一個物件宣告implements Cloneable，Override clone()方法後，就能複製一個物件，以下是之前讀
[大話設計模式]寫的範例，寫履歷Resume的時候，一般都是複製上一份履歷內容再做修改，由於履歷中的工作經驗
也是一個類別，直接複製的話工作經驗的內容將不會被複製，因此工作經驗WorkExperience也需要實作Cloneable。


類別圖：  
![Prototype Class Diagram](image/prototype.gif)  
  
程式碼：
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
