package c1.simpleFactory.village;

import org.junit.Test;

import org.junit.Assert;
/**
 * 冒險者新手村測試
 * @author Yan
 *
 */
public class VillageTest {
	@Test
	public void test(){
		//新手村訓練冒險者
		Adventurer memberA = Village.trainAdventurer("archer");
		Adventurer memberB = Village.trainAdventurer("warrior");
	
		Assert.assertEquals(memberA.getType(), "Archer");
		Assert.assertEquals(memberB.getType(), "Warrior");
	}
}
