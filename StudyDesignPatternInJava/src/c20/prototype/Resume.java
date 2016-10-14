package c20.prototype;
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
