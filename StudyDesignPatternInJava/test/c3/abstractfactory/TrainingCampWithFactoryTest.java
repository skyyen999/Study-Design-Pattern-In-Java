package c3.abstractfactory;

import org.junit.Test;

import c3.abstractfactory.village.Adventurer;
import c3.abstractfactory.village.ArcherTrainingCamp;
import c3.abstractfactory.village.TrainingCamp;
import c3.abstractfactory.village.WarriorTrainingCamp;
/**
 * 冒險者訓練營加上裝備工廠測試
 */
public class TrainingCampWithFactoryTest {
	@Test
	public void test(){
		// 弓箭手訓練營
		TrainingCamp camp = new ArcherTrainingCamp();
		// 訓練弓箭手
		Adventurer archer = camp.trainAdventurer();
		
		// 鬥士訓練營
		camp = new WarriorTrainingCamp();
		// 訓練鬥士
		Adventurer warrior = camp.trainAdventurer();
		
		archer.display();
		warrior.display();
	}
}
