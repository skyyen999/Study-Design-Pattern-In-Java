package c20.prototype;

import org.junit.Test;

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
