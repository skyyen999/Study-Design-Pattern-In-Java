package c15.builder.RobotBuilder;
/**
 * 機器人組件-外型
 */
public class Form {
	private String formName; 
	
	public Form(String formName){
		this.formName = formName;
	}
	
	public String toString(){
		return this.formName;
	}
	
}
