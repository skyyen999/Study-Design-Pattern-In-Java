package c20.prototype;

import org.junit.Test;

/**
 * 履歷-測式(原型模式)
 */
public class ResumeTest {
	@Test
	public void test() throws CloneNotSupportedException {
		Resume resume = new Resume("Yan");
		resume.setProfile("31", "master");
		resume.setWorkExperience("2016-2019", "FOYA");
	
		// 履歷表2跟1有許多相似的地方，因此直接複製履歷表1做修改
		Resume resume2  = (Resume) resume.clone();
		resume2.setWorkExperience("2011-2016", "IISI");
		
		// 履歷表3跟1有許多相似的地方，因此直接複製履歷表1做修改
		Resume resume3  = (Resume) resume.clone();
		resume3.setProfile("21", "bachelor");
		resume3.setWorkExperience("2005-2011", "TMD");
		
		
		
		resume.display();
		System.out.println();
		resume2.display();
		System.out.println();
		resume3.display();

	}
}
