package c15.builder;

import org.junit.Test;

import c15.builder.robotBuilder.Director;
import c15.builder.robotBuilder.GundamBuilder;
import c15.builder.robotBuilder.IRobot;

/**
 * 建造者模式-測試
 */
public class RobotBuilderTest {
	
	@Test
	public void test() {
		Director director = new Director(new GundamBuilder());
		IRobot robot = director.builderRobot();
		robot.display();
	}
	
}
