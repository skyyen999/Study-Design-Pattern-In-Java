package c2.factory;

import org.junit.Test;

import c2.factory.village.Adventurer;
import c2.factory.village.ArcherTrainingCamp;
import c2.factory.village.TrainingCamp;
import c2.factory.village.WarriorTrainingCamp;

import org.junit.Assert;
/**
 * 冒險者訓練營測試
 */
public class TrainingCampTest {
	@Test
	public void test(){
		System.out.println("==========工廠模式測試==========");
		
		//訓練營訓練冒險者
		//先用弓箭手訓練營訓練弓箭手
		TrainingCamp trainingCamp = new ArcherTrainingCamp();
		Adventurer memberA = trainingCamp.trainAdventurer();
		
		//用鬥士訓練營訓練鬥士
		trainingCamp = new WarriorTrainingCamp();
		Adventurer memberB = trainingCamp.trainAdventurer();
		
		//看看是不是真的訓練出我們想要的冒險者
		Assert.assertEquals(memberA.getType(), "Archer");
		Assert.assertEquals(memberB.getType(), "Warrior");
		//memberB應該是Warrior不是Knight，因此這邊會報錯
		// Assert.assertEquals(memberB.getType(), "Knight");
	}
}
